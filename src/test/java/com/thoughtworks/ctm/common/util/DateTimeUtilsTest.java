package com.thoughtworks.ctm.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {


  @Test
  void formatTime() {
    assertEquals("09:00AM", DateTimeUtils.formatTime(LocalTime.of(9, 0)));
  }

  @Test
  void addDurationToTime() {
    assertEquals(LocalTime.of(10, 0), DateTimeUtils.addDurationToTime("09:00AM", 60));
  }
}