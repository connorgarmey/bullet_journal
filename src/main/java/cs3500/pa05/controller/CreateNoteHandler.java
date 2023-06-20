package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateNoteHandler implements EventHandler<ActionEvent> {
  Model model;
  Stage popup;
  String text;
  public CreateNoteHandler(Model model, TextField notesText, Stage popupStage) {
    this.model = model;
    String text = notesText.getText();
    this.popup = popupStage;
  }

  /**
   * Handles a click of the add button
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {

    if (text.isEmpty()) {
      showAlert("Error", "Null Values", "Text cannot be null");
    } else {
     // model.addNote(text);
      popup.close();
      // Refresh data? or add a call to the handlePopup method might be cleaner
    }

  }
}
