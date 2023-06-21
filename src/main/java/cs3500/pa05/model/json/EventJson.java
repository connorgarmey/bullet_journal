package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Event;

/**
 * JSON record for an Event
 *
 * @param name the event's name
 * @param description the description
 * @param dayOfWeek the day of week
 * @param hour the hour it starts
 * @param minutes the minutes of the hour it starts
 * @param duration the length of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-week") String dayOfWeek,
    @JsonProperty("start-hour") int hour,
    @JsonProperty("start-minute") int minutes,
    @JsonProperty("duration") int duration)
{

  /**
   * Constructs a new event
   *
   * @return the Event
   */
  public Event makeEvent() {
    return new Event(name, description, dayOfWeek, hour, minutes, duration);
  }

  /**
   * Override toString for an Event JSON
   *
   * @return the String representation
   */
  @Override
  public String toString() {
    return "{\n"
        + "  \"name\": \"" + name + "\",\n"
        + "  \"description\": \"" + description + "\",\n"
        + "  \"day-of-week\": \"" + dayOfWeek + "\",\n"
        + "  \"start-hour\": " + hour + ",\n"
        + "  \"start-minute\": " + minutes + ",\n"
        + "  \"duration\": " + duration + "\n"
        + "}";
  }
}
