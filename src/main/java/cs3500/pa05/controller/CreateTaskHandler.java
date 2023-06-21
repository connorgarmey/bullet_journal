package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handler for creating a new task
 *
 */
public class CreateTaskHandler implements EventHandler<ActionEvent> {
  private Controller controller;
  private Model model;
  private TextField inputTaskName;
  private TextField inputTaskDescription;
  private ChoiceBox<String> taskDayDropDown;
  private Stage popup;


  /**
   * Instantiates a new CreateTaskHandler
   *
   * @param controller the controller
   * @param model the model
   * @param name the name of the task
   * @param des description of the taslo
   * @param days the day of the week
   * @param popupStage the popup stage
   */
  public CreateTaskHandler(Controller controller, Model model, TextField name,
                           TextField des, ChoiceBox<String> days, Stage popupStage) {
    this.controller = controller;
    this.model = model;
    this.inputTaskName = name;
    this.inputTaskDescription = des;
    this.taskDayDropDown = days;
    this.popup = popupStage;
  }

  /**
   * Overrides the handle method to handle user interactions when creating a new task
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    String task = inputTaskName.getText();
    String description = inputTaskDescription.getText();
    String day = taskDayDropDown.getValue();

    if (task.isBlank() || day == null) {
      showAlert("Error", "Null Values", "Task and Day cannot be null");
    } else if (!model.canAdd(true, taskDayDropDown.getValue())) {
      showAlert("Error", "No More Tasks", "You have hit your maximum number of events");
      popup.close();
    } else {
      model.addTask(task, description, day);
      popup.close();
      controller.refreshData();
    }
  }


}
