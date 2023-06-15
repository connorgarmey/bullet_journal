package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Theme;

public record ThemeJson(
    @JsonProperty("theme") Theme theme) {
}
