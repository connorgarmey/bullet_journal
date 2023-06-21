package cs3500.pa05.controller;

import cs3500.pa05.view.CustomTheme;
import cs3500.pa05.model.Theme;
import java.util.List;
import java.util.Objects;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * Handles the changing of themes
 *
 */
public class ChangeTheme {
  private CustomTheme theme;
  private Parent node;
  //private Controller controller;
  private List<Label> labels;

  /**
   * Instantiates a new ChangeTheme
   *
   * @param theme a CustomTheme
   * @param node the parent node
   * @param controller the controller
   */
  public ChangeTheme(CustomTheme theme, Parent node, Controller controller) {
    this.theme = theme;
    this.node = node;
    this.labels = controller.getAllLabels();
  }


  /**
   * Instantiates a new ChangeTheme
   *
   * @param theme a base theme
   * @param node parent node
   * @param controller the controller
   */
  public ChangeTheme(Theme theme, Parent node, Controller controller) {
    this.theme = theme.getTheTheme();
    this.node = node;
    this.labels = controller.getAllLabels();
  }

  /**
   * Modifies the look of the headers and content
   *
   */
  public void modifyLabels() {
    ClassLoader classLoader = getClass().getClassLoader();
    Image image = new Image(Objects.requireNonNull(classLoader.getResource(theme.getImage())).toExternalForm());
    for (Label label : labels) {
      if (label.getId().endsWith("Header")) {
        label.setFont(theme.getHeaderFont());
        label.setTextFill(theme.getFontColor());
      } else if (label.getId().endsWith("Stuff")) {
        label.setFont(theme.getStuffFont());
        label.setTextFill(theme.getFontColor());
      } else if (label.getId().endsWith("Icon")) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);  // Adjust the width as needed
        imageView.setFitHeight(70); // Adjust the height as needed
        imageView.setPreserveRatio(true);
        label.setGraphic(imageView);
      }
      else {
        label.setFont(theme.getOtherFont());
        label.setTextFill(theme.getFontColor());
      }
    }
  }

  /**
   * Modifies the background color
   *
   */
  public void modifyBackground() {
    if (node instanceof Region region) {
      region.setStyle("-fx-background-color: " + theme.getBackgroundColor() + ";");
    }
  }
}
