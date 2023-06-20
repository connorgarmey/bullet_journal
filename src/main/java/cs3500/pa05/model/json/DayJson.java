package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Occasion;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;

public record DayJson(
    @JsonProperty("day")
    String day,
    @JsonProperty("events")
    List<EventJson> events,
    @JsonProperty("tasks")
    List<TaskJson> tasks) {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"day\": \"").append(day).append("\",\n");
        sb.append("  \"events\": [");
        if (!events.isEmpty()) {
            for (EventJson event : events) {
                sb.append("\n    ").append(event.toString()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("\n  ],\n");
        sb.append("  \"tasks\": [");
        if (!tasks.isEmpty()) {
            for (TaskJson task : tasks) {
                sb.append("\n    ").append(task.toString()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("\n  ]\n");
        sb.append("}"); //hey
        return sb.toString();
    }

}
