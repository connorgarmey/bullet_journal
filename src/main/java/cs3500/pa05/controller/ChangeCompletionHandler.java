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
  private String day;
  private String task;

  public ChangeCompletionHandler(TextField task, ChoiceBox<String> day, Controller controller, Model model, Stage popup) {
    this.taskToChange = task;
    this.dayOfTheTask = day;
    this.controller = controller;
    this.model = model;
    this.popup = popup;
  }
  @Override
  public void handle(ActionEvent event) {
    this.task = taskToChange.getText();
    this.day = dayOfTheTask.getValue();
    if (badData()) {
      showAlert("Error", "Null Values", "Day and Task cannot be empty");
    } else if (!taskExists()) {
      showAlert("Error", "No Task", "You do not have task with this name");
      taskToChange.clear();
    } else if (!alreadyComplete()) {
      showAlert("Error", "Already Complete", "This task has already been completed");
    } else {
      model.updateCompletion(day, task);
      popup.close();
      controller.refreshData();
    }
  }

  private boolean badData() {
    return day.isEmpty() || task.isBlank();
  }

  private boolean taskExists() {
    return model.taskExists(day, task);
  }

  private boolean alreadyComplete() {
    return model.taskAlreadyComplete(day, task);
  }
}
