package cs3500.pa05.model;

import cs3500.pa05.model.json.EventJson;


/**
 * Represents a single event
 */
public class Event extends Occasion {
  private int startHour;
  private int startMinute;
  private int duration;

  /**
   * Instantiates a new Event with the user inputted details
   *
   * @param n name of the event
   * @param des description of the event
   * @param d day the event takes place
   * @param hour hour of the day the event starts
   * @param minute minute of the hour the event starts
   * @param dur duration of the event
   */
  public Event(String n, String des, String d, int hour, int minute, int dur) {
    super(n, des, d);
    this.startHour = hour;
    this.startMinute = minute;
    this.duration = dur;
  }

  /**
   * Instantiates a new Event given the EventJson representation
   *
   * @param eventJson
   */
  public Event(EventJson eventJson) {
    super(eventJson.name(), eventJson.description(), eventJson.dayOfWeek());
    this.startHour = eventJson.hour();
    this.startMinute = eventJson.minutes();
    this.duration = eventJson.duration();
  }

  /**
   * Returns the duration of the event
   *
   * @return the duration
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Overrides toString to write the event information as a string
   *
   * @return the string representation of the event
   */
  @Override
  public String toString() {
    String string = "What: "
        + name
        + System.lineSeparator()
        + makeDescription()
        + "Start Time: "
        + startHour
        + ":"
        + makeMiutes()
        + System.lineSeparator()
        + "Duration: "
        + makeTime()
        + System.lineSeparator();
    return string;
  }

  /**
   * Formats the start minute
   *
   * @return formatted minutes
   */
  private String makeMiutes() {
    if (startMinute < 10) {
      return "0" + startMinute;
    } else {
      return String.valueOf(startMinute);
    }
  }

  /**
   * Formats the start time
   *
   * @return formatted time
   */
  private String makeTime() {
    if (duration < 60) {
      return duration + " minutes";
    } else if (duration == 60) {
      return "1 hour";
    } else if (duration % 60 == 0) {
      int hours = duration / 60;
      return hours + " hours";
    } else if (duration > 60 && duration < 120){
      int minutes = duration - 60;
      return "1 hour " + minutes + " minutes";
    } else {
      int minutes = duration % 60;
      int hours = (duration - minutes) / 60;
      return hours + " hours " + minutes + " minutes";
    }
  }

  /**
   * Constructs an EventJson using this event
   *
   * @return the constructed EventJson
   */
  public EventJson makeJson() {
    return new EventJson(this.name, this.description, this.day,
        this.startHour, this.startMinute, this.duration);
  }


}
