package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeCompletionHandler implements EventHandler<ActionEvent> {
  private TextField taskToChange;
  private ChoiceBox<String> dayOfTheTask;
  private Controller controller;
  private Model model;
  private Stage popup;
  public ChangeCompletionHandler(TextField task, ChoiceBox<String> day, Controller controller, Model model, Stage popup) {
    this.taskToChange = task;
    this.dayOfTheTask = day;
    this.controller = controller;
    this.model = model;
    this.popup = popup;
  }
  @Override
  public void handle(ActionEvent event) {
    String task = taskToChange.getText();
    String day = dayOfTheTask.getValue();
    if (badData(task, day)) {
      showAlert("Error", "Null Values", "Day and Task cannot be empty");
    } else if (!taskExists(task ,day)) {
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

  private boolean badData(String task, String day) {
    return day == null || task.isBlank();
  }

  private boolean taskExists(String task, String day) {
    return model.occasionExists(day, task, true);
  }

  private boolean alreadyComplete(String task, String day) {
    return model.taskAlreadyComplete(day, task);
  }
}
