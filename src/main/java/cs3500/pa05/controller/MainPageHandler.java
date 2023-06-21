package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.model.Theme;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Controller for the central page of the journal
 */
public class MainPageHandler implements EventHandler<ActionEvent> {
  Controller controller;
  Scene scene;
  Model model;

  /**
   * Instantiates a new MainPageHandler
   *
   * @param controller the controller to be used
   * @param scene      the Scene to load
   * @param model      the Model being used
   */
  MainPageHandler(Controller controller, Scene scene, Model model) {
    this.controller = controller;
    this.scene = scene;
    this.model = model;
  }

  /**
   * Overrides the handle method to handle the action event from the main page
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    handleActionEvent(event);

  }

  /**
   * Assigns the correct popup or action given the action event taken by the user
   *
   * @param event the action event taken by the user that requires action
   */
  @FXML
  private void handleActionEvent(ActionEvent event) {
    if (event.getSource() instanceof MenuItem menuItem) {
      String id = menuItem.getId();
      switch (id) {
        case "open", "week" -> openPopup("welcome.fxml");
        case "save" -> model.saveData();
        case "setMaxTasks" -> openPopup("max_tasks_stage.fxml");
        case "setMaxEvents" -> openPopup("max_events_stage.fxml");
        case "editTask" -> openPopup("edit_task.fxml");
        case "editEvent" -> openPopup("edit_event.fxml");
        case "task" -> openPopup("create_task.fxml");
        case "event" -> openPopup("create_event.fxml");
        case "note" -> openPopup("add_note.fxml");
        case "light" -> {
          Parent nodeLight = scene.getRoot();
          ChangeTheme change = new ChangeTheme(Theme.LIGHT, nodeLight, controller);
          change.modifyLabels();
          change.modifyBackground();
          model.changeTheme(Theme.LIGHT.getTheTheme());
        }
        case "dark" -> {
          Parent nodeDark = scene.getRoot();
          ChangeTheme change = new ChangeTheme(Theme.DARK, nodeDark, controller);
          change.modifyLabels();
          change.modifyBackground();
          model.changeTheme(Theme.DARK.getTheTheme());
        }
        case "blue" -> {
          Parent nodeBlue = scene.getRoot();
          ChangeTheme change = new ChangeTheme(Theme.BLUE, nodeBlue, controller);
          change.modifyLabels();
          change.modifyBackground();
          model.changeTheme(Theme.BLUE.getTheTheme());
        }
        case "custom" -> {
          openPopup("custom_theme.fxml");
        }
        case "sortByDuration" -> {
          model.sortEvents(1);
          controller.refreshData();
        }
        case "sortByEventName" -> {
          model.sortEvents(2);
          controller.refreshData();
        }
        case "sortByTaskName" -> {
          model.sortEvents(3);
          controller.refreshData();
        }
        default -> { }
      }
    }
  }

  /**
   * Opens a popup window
   *
   * @param url the .fxml file name
   */
  @FXML
  private void openPopup(String url) {
    try {

      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setController(controller);
      fxmlLoader.setLocation(getClass().getClassLoader().getResource(url));
      Scene popupScene = fxmlLoader.load();

      Stage popupStage = new Stage();
      popupStage.setScene(popupScene);

      popupStage.show();
      controller.popupHandler(url, popupStage, scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
