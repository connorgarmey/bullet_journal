package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

public class CreateTaskHandler implements EventHandler<ActionEvent> {
  private Controller controller;
  private Model model;
  private TextField inputTaskName;
  private TextField inputTaskDescription;
  private ChoiceBox<String> taskDayDropDown;
  private Stage popup;

  public CreateTaskHandler(Controller controller, Model model, TextField name,
                           TextField des, ChoiceBox<String > days, Stage popupStage) {
    this.controller = controller;
    this.model = model;
    this.inputTaskName = name;
    this.inputTaskDescription = des;
    this.taskDayDropDown = days;
    this.popup = popupStage;
  }

  @Override
  public void handle(ActionEvent event) {
    String task = inputTaskName.getText();
    String description = inputTaskDescription.getText();
    String day = taskDayDropDown.getValue();

    if (task.isBlank() || day.isEmpty()) {
      showAlert("Error", "Null Values", "Task and Day cannot be null");
    } else {
      model.addTask(task, description, day);
      popup.close();
      controller.refreshData();
    }

    // after adding task need to move back to main journal screen

    // check if we should clear input fields after or not necessary
  }


}
