//package cs3500.pa05.controller;
//
//import static cs3500.pa05.controller.ShowAlert.showAlert;
//
//import cs3500.pa05.model.Model;
//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import org.controlsfx.control.action.Action;
//
//public class CreateTaskHandler implements EventHandler<ActionEvent> {
//  private Controller controller;
//
//  public CreateTaskHandler(Controller controller) {
//    this.controller = controller;
//  }
//
//  @Override
//  public void handle(ActionEvent event) {
//    String task = inputTask.getText();
//    String description = inputDescription.getText();
//    String day = dayOfWeek.getValue();
//
//    if (task.isBlank() || day.isEmpty()) {
//      showAlert("Error", "Null Values", "Task and Day cannot be null");
//    }
//
//    controller.getModel().addTask(task, description, day);
//    // after adding task need to move back to main journal screen
//
//    // check if we should clear input fields after or not necesary
//  }
//
//
//}
