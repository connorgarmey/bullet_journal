package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Occasion;
import java.util.List;

public record DayJson(
    @JsonProperty("day")
    String day,
    @JsonProperty("events")
    List<EventJson> events,
    @JsonProperty("tasks")
    List<TaskJson> tasks) {
}
