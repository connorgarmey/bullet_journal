package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.CustomTheme;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Creates a custom theme for the program
 *
 */
public class CreateCustomTheme implements EventHandler<ActionEvent> {
  Model model;

  private ChoiceBox<String> chooseFont;
  private ChoiceBox<String> chooseFontColor;
  private ChoiceBox<String> chooseBackground;
  private ChoiceBox<String> chooseIcon;
  private TextField customFieldName;
  private Stage popup;
  private Controller controller;
  private Scene scene;

  /**
   * Instantiates a new custom theme
   *
   * @param font selected font
   * @param fontColor selected font color
   * @param background selected background
   * @param icon selected icon
   * @param name name of the theme
   * @param model the model implementation
   * @param popup the popup stage
   * @param controller the controller
   * @param scene the scene
   */
  public CreateCustomTheme(ChoiceBox<String> font, ChoiceBox<String> fontColor,
                           ChoiceBox<String> background, ChoiceBox<String> icon,
                           TextField name, Model model, Stage popup, Controller controller,
                           Scene scene) {
    this.chooseFont = font;
    this.chooseFontColor = fontColor;
    this.chooseBackground = background;
    this.chooseIcon = icon;
    this.customFieldName = name;
    this.model = model;
    this.popup = popup;
    this.controller = controller;
    this.scene = scene;
  }

  /**
   * Overriding the handle method to handle user actions when creating a custom theme
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    if (anyBlank()) {
      showAlert("Error", "Null Values", "You must choose an option for all fields");
    } else if (isNameEmpty()) {
      showAlert("Error", "Null Values", "You must choose a name");
    } else {
      String font = chooseFont.getValue();
      String fontColor = chooseFontColor.getValue();
      String background = chooseBackground.getValue();
      String icon = chooseIcon.getValue();
      String name = customFieldName.getText();
      CustomTheme theme = new CustomTheme(font, fontColor, background, icon, name);

      model.changeTheme(theme);
      popup.close();
      Parent node = scene.getRoot();
      ChangeTheme change = new ChangeTheme(model.getTheme(), node, controller);
      change.modifyLabels();
      change.modifyBackground();
      controller.refreshData();
    }
  }

  /**
   * Checks that none of the theme fields were left blank
   *
   * @return true if any of the necessary fields were left blank
   */
  private boolean anyBlank() {
    String font = chooseFont.getValue();
    String fontColor = chooseFontColor.getValue();
    String background = chooseBackground.getValue();
    String icon = chooseIcon.getValue();
    return font == null || fontColor == null
        || background == null || icon == null;
  }

  /**
   * Checks the user provided a name for the theme
   *
   * @return true if the name field is empty
   */
  private boolean isNameEmpty() {
    String name = customFieldName.getText();
    return name.isBlank();
  }
}
