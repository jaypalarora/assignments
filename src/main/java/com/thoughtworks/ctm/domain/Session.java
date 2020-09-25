package com.thoughtworks.ctm.domain;

public enum Session {
  MORNING {
    public String startTime() {
      return "09:00AM";
    }

    public String endTime() {
      return "12:00PM";
    }

    private static final int MAX_DURATION = 3 * 60;

    @Override
    public int maxSessionDuration() {
      return MAX_DURATION;
    }
  },
  AFTERNOON {
    public String startTime() {
      return "01:00PM";
    }

    public String endTime() {
      return "05:00PM";
    }

    private static final int MAX_DURATION = 4 * 60;

    @Override
    public int maxSessionDuration() {
      return MAX_DURATION;
    }
  };

  public abstract String startTime();

  public abstract String endTime();

  public abstract int maxSessionDuration();

}
