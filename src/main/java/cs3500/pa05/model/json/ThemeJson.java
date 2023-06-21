package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON record for a Theme
 *
 * @param font the theme's font
 * @param fontColor the theme's font color
 * @param background the theme's background
 * @param icon the theme's icon
 * @param name the theme's name
 */
public record ThemeJson(
    @JsonProperty("font") String font,
    @JsonProperty("fontColor") String fontColor,
    @JsonProperty("background") String background,
    @JsonProperty("icon") String icon,
    @JsonProperty("name") String name) {

  /**
   * Overriding toString to write the theme as a String
   *
   * @return the String representation of the ThemeJson
   */
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

  /**
   * Hel[per to format the ThemeJson as String
   *
   * @param original the text we want to wrap
   * @return the formatted text
   */
  private String makeCorrectFormat(String original) {
    return "\"" + original + "\"";
  }

}
