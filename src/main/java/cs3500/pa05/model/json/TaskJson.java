package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Task;

/**
 * JSON record for a Task
 *
 * @param name name of the task
 * @param description description of the task
 * @param dayOfWeek the day of the week the task must be completed
 * @param isCompleted if it is completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-week") String dayOfWeek,
    @JsonProperty("completed") boolean isCompleted) {


  /**
   * Constructs a task from this TaskJson
   *
   * @return the constructed Task
   */
  public Task makeTask() {
    return new Task(name, description, dayOfWeek);
  }


  /**
   * Overrides toString to write the TaskJson as a string
   *
   * @return the TaskJson as a string
   */
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
