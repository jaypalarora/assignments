package com.thoughtworks.ctm.domain;

public enum EventType {
  TALK {
    @Override
    public String startTime() {
      return null;
    }

    @Override
    public String endTime() {
      return null;
    }
  },
  LUNCH {
    @Override
    public String startTime() {
      return "12:00PM";
    }

    @Override
    public String endTime() {
      return "1:00PM";
    }
  },
  NETWORKING {
    @Override
    public String startTime() {
      return null;
    }

    @Override
    public String endTime() {
      return null;
    }
  };

  public abstract String startTime();

  public abstract String endTime();

}
