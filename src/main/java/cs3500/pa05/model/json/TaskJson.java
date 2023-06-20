package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Task;

public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-week") String dayOfWeek,
    @JsonProperty("completed") boolean isCompleted) {


  public Task makeTask() {
    return new Task(name, description, dayOfWeek);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  \"name\": \"").append(name).append("\",\n");
    sb.append("  \"description\": \"").append(description).append("\",\n");
    sb.append("  \"day-of-week\": \"").append(dayOfWeek).append("\",\n");
    sb.append("  \"completed\": ").append(isCompleted).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
