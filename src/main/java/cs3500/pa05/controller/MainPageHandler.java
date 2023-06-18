package cs3500.pa05.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Controller for the central page of the journal
 */
public class MainPageHandler implements EventHandler<ActionEvent> {
  Controller controller;

  MainPageHandler(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void handle(ActionEvent event) {
    handleActionEvent(event);

  }

  @FXML
  private void handleActionEvent(ActionEvent event) {
    if (event.getSource() instanceof MenuItem menuItem) {
      String id = menuItem.getId();
      switch (id) {
        case "open":
        case "week":
          controller.loadScene("welcome.fxml");
          controller.changeStage();
          break;
        case "save":
          //implement later
          break;
        case "setMax":
          openPopup("max_tasks_stage.fxml");
          break;
        case "task":
          openPopup("create_task.fxml");
          break;
        case "event":
          openPopup("create_event.fxml");
          break;
        case "light":
          //implement later
          break;
        case "dark":
          //implement later
          break;
        case "blue":
          //implement later
          break;
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
