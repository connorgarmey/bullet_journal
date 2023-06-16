package cs3500.pa05.model;

import java.security.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import cs3500.pa05.model.json.EventJson;


public class Event extends Occasion {
  private Timestamp start;
  private int duration;

  public Event(String n, String des, String d, Timestamp time, int dur) {
    super(n, des, d);
    this.start = time;
    this.duration = dur;
  }

  public Event(EventJson eventJson) {
    super(eventJson.name(), eventJson.description(), eventJson.dayOfWeek());
    this.start = eventJson.startTime();
    this.duration = eventJson.duration();
  }

  public int getDuration() {
    return this.duration;
  }

  @Override
  public String toString() {
    String string = "Event: "
        + name
        + System.lineSeparator()
        + makeDescription()
        + "Start Time: "
        + start.toString()
        + System.lineSeparator()
        + "Duration: "
        + makeTime()
        + System.lineSeparator();
    return string;
  }

  private String makeTime() {
    if (duration < 60) {
      return "" + duration + " minutes";
    } else {
      int minutes = duration % 60;
      int hours = (duration - minutes) / 60;
      return "" + hours + " hours" + minutes + " minutes";
    }
  }


}
