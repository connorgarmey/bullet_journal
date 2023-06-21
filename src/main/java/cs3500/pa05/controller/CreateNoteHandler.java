package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles the creation of notes
 *
 */
public class CreateNoteHandler implements EventHandler<ActionEvent> {
  private Controller controller;
  private Model model;
  private Stage popup;
  private TextArea text;


  /**
   * Instantiates a new CreateNoteHandler
   *
   * @param c the controller
   * @param model the model
   * @param notesText the TextArea to input the note in
   * @param popupStage the stage it exists on
   */
  public CreateNoteHandler(Controller c, Model model, TextArea notesText, Stage popupStage) {
    this.controller = c;
    this.model = model;
    this.text = notesText;
    this.popup = popupStage;
  }

  /**
   * Handles a click of the add button
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    if (text.getText().isBlank()) {
      showAlert("Error", "Null Values", "Text cannot be null");
    } else {
      model.addNote(text.getText());
      popup.close();
    }
    controller.refreshData();
  }
}
