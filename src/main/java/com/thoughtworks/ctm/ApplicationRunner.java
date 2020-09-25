package com.thoughtworks.ctm;

import com.thoughtworks.ctm.domain.Event;
import com.thoughtworks.ctm.service.InputParser;
import com.thoughtworks.ctm.service.TrackService;
import java.io.IOException;
import java.util.List;

public class ApplicationRunner {

  public static void main(String[] args) throws IOException {
    final InputParser parser = new InputParser();
    List<Event> inputEvents = parser.parseEvents("src/main/resources/input.txt");

    final TrackService trackService = new TrackService();
    trackService.buildTracks(inputEvents);
  }

}
