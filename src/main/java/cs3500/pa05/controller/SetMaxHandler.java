package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetMaxHandler implements EventHandler<ActionEvent> {
  Controller controller;
  Model model;
  TextField textField;
  Label theLabel;
  Stage popup;

  public SetMaxHandler(Controller control, Model model, TextField field, Label label, Stage popup) {
    this.controller = control;
    this.model = model;
    this.textField = field;
    this.theLabel = label;
    this.popup = popup;
  }

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

  private String whichMax() {
    if (theLabel.getId().equals("maxTasks")) {
      return "tasks";
    } else {
      return "events";
    }
  }
}
