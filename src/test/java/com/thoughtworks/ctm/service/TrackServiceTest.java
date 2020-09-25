package com.thoughtworks.ctm.service;

import com.thoughtworks.ctm.domain.Event;
import com.thoughtworks.ctm.domain.Track;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class TrackServiceTest {

  private InputParser inputParser = new InputParser();
  private TrackService trackService = new TrackService();
  private String filePath = "src/test/resources/input-trackService.txt";

  @Test
  void buildTracks_fillsUpMorningSession() throws IOException {
    List<Event> inputEvents = inputParser.parseEvents(filePath);
    List<Track> tracks = trackService.buildTracks(inputEvents);
    trackService.printTracks(tracks);
  }
}