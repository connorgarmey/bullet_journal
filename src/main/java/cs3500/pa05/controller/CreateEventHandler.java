package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

  @Override
  public void handle(ActionEvent event) {
    if (badData()) {
      showAlert("Error", "Null Values", "Event and Day cannot be null");
    } else if (badTime()) {
      showAlert("Error", "Null Values", "Hour and Minutes cannot be null");
    } else if (badDuration()) {
      showAlert("Error", "Invalid Duration", "Duration must be a positive integer");
      duration.clear();
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


  private boolean badData() {
    String name = inputEventName.getText();
    String day = eventDayDropDown.getValue();
    return name.isBlank() || day.isEmpty();
  }

  private boolean badTime() {
    String hour = startHours.getValue();
    String minute = startMinutes.getValue();
    return hour.isEmpty() || minute.isEmpty();
  }

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