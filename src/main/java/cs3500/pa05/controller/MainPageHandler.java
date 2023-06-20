package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.model.Theme;
import cs3500.pa05.view.View;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Controller for the central page of the journal
 */
public class MainPageHandler implements EventHandler<ActionEvent> {
  Controller controller;
  Scene scene;
  Model model;

  MainPageHandler(Controller controller, Scene scene, Model model) {
    this.controller = controller;
    this.scene = scene;
    this.model = model;
  }

  @Override
  public void handle(ActionEvent event) {
    handleActionEvent(event);

  }

  @FXML
  private void handleActionEvent(ActionEvent event) {
    if (event.getSource() instanceof MenuItem menuItem) {
      String id = menuItem.getId();
      ClassLoader classLoader = getClass().getClassLoader();
      switch (id) {
        case "open", "week" -> {
          controller.loadScene("welcome.fxml");
          controller.changeStage();
        }
        case "save" -> model.saveData();
        case "setMaxTasks" -> openPopup("max_tasks_stage.fxml");
        case "setMaxEvents" -> openPopup("max_events_stage.fxml");
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
        controller.popupHandler(url, popupStage);
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

}
