package com.thoughtworks.ctm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thoughtworks.ctm.domain.Event;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputParserTest {

  private InputParser inputParser;
  private String filePath;

  @BeforeEach
  public void setup() {
    inputParser = new InputParser();
    filePath = "src/test/resources/input.txt";
  }

  @Test
  public void parse_whenFilePathIsValid()
      throws IOException {
    List<Event> inputEvents = inputParser.parseEvents(filePath);
    assertEquals(1, inputEvents.size(), "Parsed input events should be equal to 1");
    assertEquals(60, inputEvents.get(0).getDuration());
    assertEquals("Writing Fast Tests Against Enterprise Rails", inputEvents.get(0).getTitle());
  }

  @Test
  public void shouldFailParsing_whenFilePath_isInvalid()
      throws IOException {
    String badInputPath = "src/test/resources/bad-input.txt";
    List<Event> inputEvents = inputParser.parseEvents(badInputPath);
    assertEquals(0, inputEvents.size(), "Parsed input events should be equal to 0");
    assertEquals(60, inputEvents.get(0).getDuration());
    assertEquals("Writing Fast Tests Against Enterprise Rails", inputEvents.get(0).getTitle());
  }

  //TODO add tests for multiple events.

}
