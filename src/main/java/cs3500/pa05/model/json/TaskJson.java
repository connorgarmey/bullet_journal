package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-week") String dayOfWeek,
    @JsonProperty("completed") boolean isCompleted) {
}
