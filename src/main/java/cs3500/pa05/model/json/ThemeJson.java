package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ThemeJson(
    @JsonProperty("font") String font,
    @JsonProperty("fontColor") String fontColor,
    @JsonProperty("background") String background,
    @JsonProperty("icon") String icon,
    @JsonProperty("name") String name) {

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  \"font\": ").append(makeCorrectFormat(font)).append(",\n");
    sb.append("  \"fontColor\": ").append(makeCorrectFormat(fontColor)).append(",\n");
    sb.append("  \"background\": ").append(makeCorrectFormat(background)).append(",\n");
    sb.append("  \"icon\": ").append(makeCorrectFormat(icon)).append(",\n");
    sb.append("  \"name\": ").append(makeCorrectFormat(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String makeCorrectFormat(String original) {
    return "\"" + original + "\"";
  }

}
