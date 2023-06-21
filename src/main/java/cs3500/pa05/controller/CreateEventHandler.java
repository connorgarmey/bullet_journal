package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The handler class for creating a new event
 *
 */
public class CreateEventHandler implements EventHandler<ActionEvent> {
  private Model model;

  private TextField inputEventName;

  private TextField inputEventDescription;

  private ChoiceBox<String> eventDayDropDown;

  private ChoiceBox<String> startHours;

  private ChoiceBox<String>  startMinutes;

  private TextField duration;
  private Stage popup;
  private Controller controller;

  /**
   * Instantiates a new CreateEventHandler
   *
   * @param model the model object
   * @param name the name of the event
   * @param des description of the event
   * @param hours start hour of the event
   * @param minutes start minute of the hour
   * @param dur duration of the event
   * @param day day of the week the event is
   * @param popup the popup
   * @param controller the controller user
   */
  public CreateEventHandler(Model model, TextField name, TextField des, ChoiceBox<String>  hours,
                            ChoiceBox<String>  minutes, TextField dur, ChoiceBox<String> day,
                            Stage popup, Controller controller) {
    this.model = model;
    this.inputEventDescription = des;
    this.inputEventName = name;
    this.eventDayDropDown = day;
    this.startHours = hours;
    this.startMinutes = minutes;
    this.duration = dur;
    this.popup = popup;
    this.controller = controller;
  }

  /**
   * Overrides the handle method to handle user interactions when creating the event
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {

    if (badData()) {
      showAlert("Error", "Null Values", "Event and Day cannot be null");
    } else if (badTime()) {
      showAlert("Error", "Null Values", "Hour and Minutes cannot be null");
    } else if (badDuration()) {
      showAlert("Error", "Invalid Duration", "Duration must be a positive integer");
      duration.clear();
    } else if (!model.canAdd(false, eventDayDropDown.getValue())) {
      showAlert("Error", "No More Events", "You have hit your maximum number of tasks");
      popup.close();
    } else {
      String name = inputEventName.getText();
      String description = inputEventDescription.getText();
      String day = eventDayDropDown.getValue();
      int hour = Integer.parseInt(startHours.getValue());
      int minute = Integer.parseInt(startMinutes.getValue());
      String dur = duration.getText();
      int length = Integer.parseInt(dur);

      model.addEvent(name, description, day, hour, minute, length);
      popup.close();
      controller.refreshData();
    }

  }

  /**
   * Checks if the name and day fields are invalid
   *
   * @return true if the name is blank or the day is null
   */
  private boolean badData() {
    String name = inputEventName.getText();
    String day = eventDayDropDown.getValue();
    return name.isBlank() || day == null;
  }

  /**
   * Checks that the start time is valid
   *
   * @return true if the hour or minutes field is null
   */
  private boolean badTime() {
    String hour = startHours.getValue();
    String minute = startMinutes.getValue();
    return hour == null || minute == null;
  }

  /**
   * Checks that the inputted duration is a valid length
   *
   * @return true if the duration is invalid
   */
  private boolean badDuration() {
    String dur = duration.getText();
    try {
      int i = Integer.parseInt(dur);
      return i <= 0;
    } catch (Exception e) {
      return true;
    }
  }


}