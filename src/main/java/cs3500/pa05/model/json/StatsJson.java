package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON record for the week's statistics
 *
 * @param event     number of events that week
 * @param task      number of tasks that week
 * @param maxEvents max allowed events
 * @param maxTasks  max allowed tasks
 * @param notes     user inputted notes
 * @param percent   percent completed
 */
public record StatsJson(
    @JsonProperty("eventsCount")
    int event,
    @JsonProperty("taskCount")
    int task,
    @JsonProperty("maxEvents")
    int maxEvents,
    @JsonProperty("maxTasks")
    int maxTasks,
    @JsonProperty("notes")
    String notes,
    @JsonProperty("percent")
    double percent) {


  /**
   * Overriding toString to write the StatsJson as a string
   *
   * @return the StatsJson as string
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  \"eventsCount\": ").append(event).append(",\n");
    sb.append("  \"taskCount\": ").append(task).append(",\n");
    sb.append("  \"maxEvents\": ").append(maxEvents).append(",\n");
    sb.append("  \"maxTasks\": ").append(maxTasks).append(",\n");
    if (notes.isEmpty()) {
      sb.append("  \"notes\": \"\",\n");
    } else {
      sb.append("  \"notes\": ");
      sb.append("\"").append(notes).append("\"").append(",\n");
    }
    sb.append("  \"percent\": ").append(percent).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
