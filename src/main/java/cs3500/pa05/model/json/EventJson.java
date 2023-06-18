package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Event;
import java.security.Timestamp;

public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-week") String dayOfWeek,
    @JsonProperty("start-hour") int hour,
    @JsonProperty("start-minute") int minutes,
    @JsonProperty("duration") int duration)
{

  public Event makeEvent() {
    Event event = new Event(name, description, dayOfWeek, hour, minutes, duration);
    return event;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  \"name\": \"").append(name).append("\",\n");
    sb.append("  \"description\": \"").append(description).append("\",\n");
    sb.append("  \"day-of-week\": \"").append(dayOfWeek).append("\",\n");
    sb.append("  \"start-hour\": ").append(hour).append(",\n");
    sb.append("  \"start-minute\": ").append(minutes).append(",\n");
    sb.append("  \"duration\": ").append(duration).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
