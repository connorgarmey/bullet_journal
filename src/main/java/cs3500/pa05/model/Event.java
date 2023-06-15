package cs3500.pa05.model;

import java.security.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Event extends Occasion{
  private Timestamp start;
  private int duration;

  public Event(String n, String des, Day d, Timestamp time, int dur) {
    super(n, des, d);
    this.start = time;
    this.duration = dur;
  }
}
