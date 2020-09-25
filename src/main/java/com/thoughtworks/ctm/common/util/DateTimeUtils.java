package com.thoughtworks.ctm.common.util;


import static com.thoughtworks.ctm.common.AppConstants.LIGHTNING;
import static com.thoughtworks.ctm.common.AppConstants.MIN;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

  private static final DateTimeFormatter FRMT_HH_MM_A = DateTimeFormatter.ofPattern("hh:mma");

  private DateTimeUtils() {
  }

  public static String formatTime(LocalTime localTime) {
    return FRMT_HH_MM_A.format(localTime);
  }

  public static LocalTime addDurationToTime(String time, int duration) {
    return LocalTime.parse(time, FRMT_HH_MM_A).plusMinutes(duration);
  }

  public static String parseMinutes(String rawDuration) {
    return rawDuration.substring(0, rawDuration.indexOf(MIN));
  }

  public static int parseDuration(String rawDuration) {
    int duration = 0;
    if (rawDuration.endsWith(MIN)) {
      duration = Integer.parseInt(parseMinutes(rawDuration));
    } else if (rawDuration.endsWith(LIGHTNING)) {
      duration = 5;
    } else {
      throw new IllegalArgumentException("Invalid duration provided");
    }

    return duration;
  }

  public static String formatDuration(int duration) {
    if (duration > 5) {
      return duration + MIN;
    } else {
      return LIGHTNING;
    }
  }
}
