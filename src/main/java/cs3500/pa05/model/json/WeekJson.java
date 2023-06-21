package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * JSON record for a Week
 *
 * @param days list of DayJson
 * @param theme seelcted ThemeJson
 * @param stats StatsJson
 */
public record WeekJson(
    @JsonProperty("days")
    List<DayJson> days,
    @JsonProperty("theme")
    ThemeJson theme,
    @JsonProperty("stats")
    StatsJson stats)
{

    /**
     * Overriding the toString method to write out the week as a String
     *
     * @return the String representation of the Week
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"days\": [\n");
        for (int i = 0; i < days.size(); i++) {
            sb.append("    ").append(days.get(i).toString().replaceAll("\n", "\n    "));
            if (i < days.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("  ],\n");
        sb.append("  \"theme\": ").append(theme.toString().replaceAll("\n", "\n  ")).append(",\n");
        sb.append("  \"stats\": ").append(stats.toString().replaceAll("\n", "\n  ")).append("\n");
        sb.append("}");
        return sb.toString();
    }


}
