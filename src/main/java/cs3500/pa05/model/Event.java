package cs3500.pa05.model;

import java.security.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import cs3500.pa05.model.json.EventJson;


public class Event extends Occasion {
  private int startHour;
  private int startMinute;
  private int duration;

  public Event(String n, String des, String d, int hour, int minute, int dur) {
    super(n, des, d);
    this.startHour = hour;
    this.startMinute = minute;
    this.duration = dur;
  }

  public Event(EventJson eventJson) {
    super(eventJson.name(), eventJson.description(), eventJson.dayOfWeek());
    this.startHour = eventJson.hour();
    this.startMinute = eventJson.minutes();
    this.duration = eventJson.duration();
  }

  public int getDuration() {
    return this.duration;
  }

  @Override
  public String toString() {
    String string = "What: "
        + name
        + System.lineSeparator()
        + makeDescription()
        + "Start Time: "
        + startHour
        + ":"
        + startMinute
        + System.lineSeparator()
        + "Duration: "
        + makeTime()
        + System.lineSeparator();
    return string;
  }

  private String makeTime() {
    if (duration < 60) {
      return "" + duration + " minutes";
    } else if (duration == 60) {
      return "1 hour";
    } else if (duration % 60 == 0) {
      int hours = duration / 60;
      return "" + hours + " hours";
    } else if (duration > 60 && duration < 120){
      int minutes = duration - 60;
      return "1 hour " + minutes + " minutes";
    } else {
      int minutes = duration % 60;
      int hours = (duration - minutes) / 60;
      return "" + hours + " hours " + minutes + " minutes";
    }
  }

  public EventJson makeJson() {
    EventJson json = new EventJson(this.name, this.description, this.day,
        this.startHour, this.startMinute, this.duration);
    return json;
  }


}
