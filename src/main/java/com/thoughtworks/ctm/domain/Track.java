package com.thoughtworks.ctm.domain;

import static com.thoughtworks.ctm.common.util.DateTimeUtils.addDurationToTime;
import static com.thoughtworks.ctm.common.util.DateTimeUtils.formatTime;
import static com.thoughtworks.ctm.domain.EventType.LUNCH;
import static com.thoughtworks.ctm.domain.EventType.NETWORKING;
import static java.util.Collections.unmodifiableCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class Track {

  private final String name;
  private final SubTrack morningSubTrack = new SubTrack(Session.MORNING);
  private final SubTrack afternoonSubTrack = new SubTrack(Session.AFTERNOON);


  public Track(int trackNumber) {
    this.name = "Track " + trackNumber;
  }

  public String getName() {
    return name;
  }

  public boolean addEvent(Event event) {
    boolean added = false;
    if (morningSubTrack.hasCapacity()) {
      System.out.println("Adding event to morning session");
      added = morningSubTrack.addEvent(event);
    }

    if (!added && afternoonSubTrack.hasCapacity()) {
      System.out.println("Adding event to afternoon session");
      added = afternoonSubTrack.addEvent(event);
    }

    return added;
  }

  public Collection<Event> getEvents() {
    Collection<Event> events = new ArrayList<>();
    events.addAll(morningSubTrack.getEvents());

    Event lunch = new Event(LUNCH.name(), 60, LUNCH);
    lunch.setStartTime(LUNCH.startTime());
    events.add(lunch);

    List<Event> afternoonSubTrackEvents = afternoonSubTrack.getEvents();
    events.addAll(afternoonSubTrackEvents);

    events.add(getNetworkingEvent(afternoonSubTrackEvents));

    return unmodifiableCollection(events);
  }

  private Event getNetworkingEvent(List<Event> afternoonSubTrackEvents) {
    Event networkingEvent = new Event(NETWORKING.name(), 0, NETWORKING);
    Event lastEventOfAfternoon = getLastEventOfAfternoon(afternoonSubTrackEvents);
    networkingEvent.setStartTime(getStartTime(lastEventOfAfternoon));
    return networkingEvent;
  }

  private String getStartTime(Event lastEventOfAfternoon) {
    return formatTime(
        addDurationToTime(lastEventOfAfternoon.getStartTime(), lastEventOfAfternoon.getDuration()));
  }

  private Event getLastEventOfAfternoon(List<Event> afternoonSubTrackEvents) {
    return afternoonSubTrackEvents.get(afternoonSubTrackEvents.size() - 1);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Track.class.getSimpleName() + "[", "]")
        .add("name=" + name)
        .add("morningSubTrack=" + morningSubTrack)
        .add("afternoonSubTrack=" + afternoonSubTrack)
        .toString();
  }

  public boolean hasCapacity() {
    return morningSubTrack.hasCapacity() || afternoonSubTrack.hasCapacity();
  }
}

