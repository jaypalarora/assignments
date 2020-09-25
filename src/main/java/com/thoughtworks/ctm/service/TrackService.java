package com.thoughtworks.ctm.service;

import static com.thoughtworks.ctm.domain.EventType.LUNCH;

import com.thoughtworks.ctm.domain.Event;
import com.thoughtworks.ctm.domain.Track;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TrackService {

  public List<Track> buildTracks(List<Event> inputEvents) {

//    int maxSessions = maxSessions(inputEvents);
    System.out.println("Total events for building tracks - " + inputEvents.size());

    /*List<Event> sortedEvents = inputEvents.stream()
        .sorted(Comparator.comparing(Event::getDuration))
        .collect(Collectors.toList());*/

    List<Track> tracks = new ArrayList<>();
    addNewTrack(tracks);

    int currentTrack = 0;

    for (Event event : inputEvents) {
      Track track = tracks.get(currentTrack);
      if (!track.addEvent(event)) {
        currentTrack++;
        addNewTrack(tracks);
        tracks.get(currentTrack).addEvent(event);
      }

    }

    return tracks;
  }

  private int maxSessions(List<Event> inputEvents) {
    AtomicReference<Integer> sum = new AtomicReference<>(0);
    List<Event> sortedEvents = inputEvents.stream()
        .sorted(Comparator.comparing(Event::getDuration))
        .map(event -> {
          sum.updateAndGet(v -> v + event.getDuration());
          return event;
        })
        .collect(Collectors.toList());

    return Math.floorDiv(Double.valueOf(Math.ceil(sum.get() / 180)).intValue(), 2);
  }

  private Track addNewTrack(List<Track> tracks) {
    Track track = new Track(tracks.size() + 1);
    tracks.add(track);
    System.out.println("Added Track - " + track.getName());

    return track;
  }

  private void addLunchEvent(Track track) {
    Event lunch = new Event("Lunch", 60, LUNCH);
    lunch.setStartTime(LUNCH.startTime());
    track.getEvents().add(lunch);
  }

  public void printTracks(List<Track> tracks) {
    tracks.forEach(
        track -> {
          System.out.println(track.getName());
          track.getEvents().forEach(System.out::println);
          System.out.println();
        });
  }
}
