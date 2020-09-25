package com.thoughtworks.ctm.domain;

import static com.thoughtworks.ctm.common.util.DateTimeUtils.addDurationToTime;
import static com.thoughtworks.ctm.common.util.DateTimeUtils.formatDuration;
import static com.thoughtworks.ctm.common.util.DateTimeUtils.formatTime;
import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SubTrack {

  private final Session session;
  private final List<Event> events;
  private int remainingCapacity;

  public SubTrack(Session session) {
    this.session = session;
    events = new ArrayList<>();
    remainingCapacity = session.maxSessionDuration();
  }

  boolean addEvent(Event event) {
    if (getRemainingCapacity() >= event.getDuration()) {
      System.out.println(
          format("Adding event -{0} {1}", event.getTitle(), formatDuration(event.getDuration())));

      setEventStartTime(event);
      remainingCapacity -= event.getDuration();

      return events.add(event);
    } else {
      System.err.println(
          "Couldn't add event. Remaining Capacity - " + getRemainingCapacity()
              + ", new event duration - " + event
              .getDuration());

      return false;
    }
  }

  private void setEventStartTime(Event event) {
    if (getRemainingCapacity() == session.maxSessionDuration()) {
      event.setStartTime(session.startTime());
    } else {
      event.setStartTime(formatTime(addDurationToTime(getLastEventTime(), event.getDuration())));
    }
  }

  private String getLastEventTime() {
    return events.get(events.size() - 1).getStartTime();
  }

  public List<Event> getEvents() {
    return events;
  }

  public boolean hasCapacity() {
    return getRemainingCapacity() >= 5;
  }

  public int getRemainingCapacity() {
    return remainingCapacity;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SubTrack.class.getSimpleName() + "[", "]")
        .add("events=" + events)
        .toString();
  }
}
