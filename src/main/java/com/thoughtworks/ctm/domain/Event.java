package com.thoughtworks.ctm.domain;

import static com.thoughtworks.ctm.common.util.DateTimeUtils.formatDuration;
import static com.thoughtworks.ctm.domain.EventType.LUNCH;

import java.util.Objects;
import java.util.StringJoiner;

public class Event {

  private final String title;
  private final int duration;
  private final EventType eventType;
  private String startTime;

  public Event(String title, int duration, EventType eventType) {
    this.title = title;
    this.duration = duration;
    this.eventType = eventType;
  }

  public String getTitle() {
    return title;
  }

  public int getDuration() {
    return duration;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public EventType getEventType() {
    return eventType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Event event = (Event) o;
    return getTitle().equals(event.getTitle());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTitle());
  }

  @Override
  public String toString() {
    return new StringJoiner(" ", "", "")
        .add(startTime)
        .add(title)
        .add(LUNCH.name().equalsIgnoreCase(title) ? "" : formatDuration(duration))
        .toString();
  }

}
