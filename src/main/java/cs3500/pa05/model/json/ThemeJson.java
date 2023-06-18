package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Theme;

public record ThemeJson(
    @JsonProperty("theme") Theme theme) {

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{\n");
      sb.append("  \"theme\": ").append(theme.toString()).append("\n");
      sb.append("}");
      return sb.toString();
    }

}
