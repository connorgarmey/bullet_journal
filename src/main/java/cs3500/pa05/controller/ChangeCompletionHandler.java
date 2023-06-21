package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The handler for changing the completion of tasks
 *
 */
public class ChangeCompletionHandler implements EventHandler<ActionEvent> {
  private TextField taskToChange;
  private ChoiceBox<String> dayOfTheTask;
  private Controller controller;
  private Model model;
  private Stage popup;


  /**
   * Instantiates a new ChangeCompletionHandler
   *
   * @param task the text field to input the task in
   * @param day the choicebox containing the day of the week
   * @param controller the controller
   * @param model the model
   * @param popup the popup stage
   */
  public ChangeCompletionHandler(TextField task, ChoiceBox<String> day, Controller controller,
                                 Model model, Stage popup) {
    this.taskToChange = task;
    this.dayOfTheTask = day;
    this.controller = controller;
    this.model = model;
    this.popup = popup;
  }


  /**
   * Overrides the handle method to handle user interactions when changing the completion of tasks
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    String task = taskToChange.getText();
    String day = dayOfTheTask.getValue();
    if (badData(task, day)) {
      showAlert("Error", "Null Values", "Day and Task cannot be empty");
    } else if (!taskExists(task, day)) {
      showAlert("Error", "No Task", "You do not have task with this name");
      taskToChange.clear();
    } else if (!alreadyComplete(task, day)) {
      showAlert("Error", "Already Complete", "This task has already been completed");
    } else {
      model.updateCompletion(day, task);
      popup.close();
      controller.refreshData();
    }
  }

  /**
   * Checks if the day is null or the task name is blank
   *
   * @param task the name of the task
   * @param day the day of the week the task takes place
   * @return true if the task name or day of the week is invalid
   */
  private boolean badData(String task, String day) {
    return day == null || task.isBlank();
  }


  /**
   * Checks if a task already exists
   *
   * @param task the name of the task
   * @param day the day of the week for the task
   * @return true if the task already exists
   */
  private boolean taskExists(String task, String day) {
    return model.occasionExists(day, task, true);
  }


  /**
   * Checks if the task is already completed
   *
   * @param task the name of the task
   * @param day the day of the week for the task
   * @return true if the task is already complete
   */
  private boolean alreadyComplete(String task, String day) {
    return model.taskAlreadyComplete(day, task);
  }
}
