package cs3500.pa05.view;

import cs3500.pa05.model.json.ThemeJson;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomTheme {
  private String name;
  private String font;
  private String color;
  private String background;
  private String icon;
  private Font stuffFont;
  private Font headerFont;
  private Font otherFont;
  private Color fontColor;
  private String image;
  private String backgroundColor;


  public CustomTheme(String font, String color, String background, String icon, String name) {
    this.font = font;
    this.color = color;
    this.background = background;
    this.icon = icon;
    makeFontHeader();
    setFontColor();
    setBackgroundColor();
    setImage();
    this.name = name;
  }

  private void makeFontHeader() {
    String boldFont = font + " Bold";
    String boldItalicFont = font + " Bold Italic";
    this.headerFont = new Font(boldFont, 20);
    this.stuffFont = new Font(font, 18);
    this.otherFont = new Font(boldItalicFont, 48);
  }

  private void setFontColor() {
    switch (color) {
      case "Black" -> this.fontColor = Color.BLACK;
      case "Blue" -> this.fontColor = Color.DARKSLATEBLUE;
      case "Pink" -> this.fontColor = Color.DEEPPINK;
      case "Green" -> this.fontColor = Color.DARKGREEN;
    }
  }

  private void setBackgroundColor() {
    switch (background) {
      case "Light Pink" -> this.backgroundColor = "#fff8ff";
      case "Dark Grey" -> this.backgroundColor = "#A9A9A9";
      case "Blue" -> this.backgroundColor = "#6495ED";
      case "White" -> this.backgroundColor = "#FFFFFF";
    }
  }

  private void setImage() {
    switch (icon) {
      case "Light" -> this.image = "1.png";
      case "Dark" -> this.image = "2.png";
      case "Blue" -> this.image = "3.png";
      case "Book" -> this.image = "4.png";
      case "Chocolate" -> this.image = "5.png";
      case "Checkers Board" -> this.image = "6.png";
      case "Flower Painting" -> this.image = "7.png";
    }
  }


  /**
   * creates a new CustomTheme object using a json
   *
   * @param themeJson json representation
   * @return customTheme object with the fields
   */
  public static CustomTheme createCustomTheme(ThemeJson themeJson) {
    return new CustomTheme(themeJson.font(), themeJson.fontColor(), themeJson.background(),
        themeJson.icon(), themeJson.name());
  }

  public ThemeJson createJson() {
    return new ThemeJson(font, color, background, icon, name);
  }

  public Font getStuffFont() {
    return stuffFont;
  }


  public Font getHeaderFont() {
    return headerFont;
  }

  public Font getOtherFont() {
    return otherFont;
  }


  public Color getFontColor() {
    return fontColor;
  }


  public String getImage() {
    return image;
  }


  public String getBackgroundColor() {
    return backgroundColor;
  }


}
