package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles changing of the maximum allowed occasions
 *
 */
public class SetMaxHandler implements EventHandler<ActionEvent> {
  Controller controller;
  Model model;
  TextField textField;
  Label theLabel;
  Stage popup;

  /**
   * Instantiates a new SetMaxHandler
   *
   * @param control the controller
   * @param model the model implementation
   * @param field text field to input new max
   * @param label the label
   * @param popup the stage to popup
   */
  public SetMaxHandler(Controller control, Model model, TextField field, Label label, Stage popup) {
    this.controller = control;
    this.model = model;
    this.textField = field;
    this.theLabel = label;
    this.popup = popup;
  }

  /**
   * Overriding handle to handle when user interacts with setting max occasions
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    String max = textField.getText();

    if (max.isBlank()) {
      showAlert("Error", "Null Values", "Maximum cannot be empty");
    } else {
      try {
        int i = Integer.parseInt(max);
        model.updateMax(i, whichMax());
        popup.close();
      } catch (NumberFormatException e) {
        textField.clear();
      }
    }
  }

  /**
   * Returns whether they are trying to set the max for tasks or events
   *
   * @return string of either "tasks" or "events"
   */
  private String whichMax() {
    if (theLabel.getId().equals("maxTasks")) {
      return "tasks";
    } else {
      return "events";
    }
  }
}
