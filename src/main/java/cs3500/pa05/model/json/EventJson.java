package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.security.Timestamp;

public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-week") String dayOfWeek,
    @JsonProperty("start-time") Timestamp startTime,
    @JsonProperty("duration") int duration)
{
}
