package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record WeekJson(
    @JsonProperty("days")
    List<DayJson> days,
    @JsonProperty("theme")
    ThemeJson theme) {

}
