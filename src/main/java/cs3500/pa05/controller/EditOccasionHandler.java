package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles the user editing the occasion
 */
public class EditOccasionHandler implements EventHandler<ActionEvent> {
  private TextField eventToChange;

  private ChoiceBox<String> dayOfTheEvent;

  private Controller controller;
  private Model model;
  private boolean isTask;
  private Stage popup;
  private MenuItem newOccasion;

  /**
   * Instantiates a new EditOccasionHandler
   *
   * @param event textfield holding the event
   * @param day choicebox of which day of the week the event is on
   * @param controller the controller
   * @param model the model
   * @param isTask whether or not the occasion is a task
   * @param popup the popup to load
   * @param button the button to select
   */
  public EditOccasionHandler(TextField event, ChoiceBox<String> day, Controller controller,
                             Model model, boolean isTask, Stage popup, MenuItem button) {
    this.eventToChange = event;
    this.dayOfTheEvent = day;
    this.controller = controller;
    this.model = model;
    this.isTask = isTask;
    this.popup = popup;
    this.newOccasion = button;
  }

  /**
   * Overrides the handle method to handle user interactions when trying to update the occasion
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    String occasion = eventToChange.getText();
    String day = dayOfTheEvent.getValue();
    if (badData(occasion, day)) {
      showAlert("Error", "Null Values", "Day and Task cannot be empty");
    } else if (!eventExists(occasion, day)) {
      showAlert("Error", "No Occasion", "You do not have occasion with this name");
      eventToChange.clear();
    } else {
      model.deleteOccasion(day, occasion, isTask);
      popup.close();
      newOccasion.fire();
    }

  }

  /**
   * Returns if the inputted day or occasion name is invalid
   *
   * @param occasion name of the occasion
   * @param day day it is on
   * @return true if the input is invalid
   */
  private boolean badData(String occasion, String day) {
    return day == null || occasion.isBlank();
  }

  /**
   * Returns if the event already exists
   *
   * @param occasion name of the occasion
   * @param day day it is on
   * @return true of the occasion already exists
   */
  private boolean eventExists(String occasion, String day) {
    return model.occasionExists(day, occasion, isTask);
  }
}
