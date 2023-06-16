package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StatsJson(
    @JsonProperty("eventsCount")
    int event,
    @JsonProperty("taskCount")
    int task,
    @JsonProperty("percent")
    int percent) {

}
