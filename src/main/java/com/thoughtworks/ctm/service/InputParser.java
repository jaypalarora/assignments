package com.thoughtworks.ctm.service;

import static com.thoughtworks.ctm.common.AppConstants.MIN;
import static com.thoughtworks.ctm.common.AppConstants.SPACE;
import static com.thoughtworks.ctm.common.util.DateTimeUtils.parseDuration;
import static com.thoughtworks.ctm.domain.EventType.TALK;
import static java.util.stream.Collectors.toList;

import com.thoughtworks.ctm.common.AppConstants;
import com.thoughtworks.ctm.domain.Event;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class InputParser {

  public List<Event> parseEvents(String filePath) throws IOException {
    Objects.requireNonNull(filePath, "File path is required");

    return Files.lines(Paths.get(filePath))
        .map(
            line -> {
//              System.out.println(format("Parsing Line - %s", line));
              Event result = null;
              if (isValidLine(line)) {
                int lastSpaceIndex = line.lastIndexOf(SPACE);
                String titlePart = line.substring(0, lastSpaceIndex);
                String durationPart = line.substring(lastSpaceIndex).trim();
                Event event = new Event(titlePart, parseDuration(durationPart), TALK);
                result = event;
              }
              return result;
            })
        .collect(toList());
  }

  private boolean isValidLine(String line) {
    int lastSpaceIndex = line.lastIndexOf(SPACE);
    String titlePart = line.substring(0, lastSpaceIndex);
    // todo add check on the length of the title
    if (hasNonAlphabeticCharacters(titlePart)) {
      throw new IllegalArgumentException(
          "Invalid title provided. A title can only have alphabets.");
    }

    String durationPart = line.substring(lastSpaceIndex).trim();
    // todo add check on the talk duration of the title. It shouldn't be more than an 2 hours. Each session can have multiple talks.
    if (hasNoDuration(durationPart)) {
      throw new IllegalArgumentException(
          "Invalid talk duration provided. It should be in mins or lightening.");
    }

    return true;
  }

  private boolean hasNoDuration(String durationPart) {
    boolean invalid = true;
    if (isDurationNotLighteningOrEndingWithMin(durationPart)) {
      try {
        parseDuration(durationPart);
        invalid = false;
      } catch (NumberFormatException nfe) {
      }
    }

    return invalid;
  }

  private boolean isDurationNotLighteningOrEndingWithMin(String durationPart) {
    return !Objects.equals(AppConstants.LIGHTNING, durationPart) || !durationPart.endsWith(MIN);
  }

  private boolean hasNonAlphabeticCharacters(String titlePart) {
    return titlePart.matches("\\d");
  }
}
