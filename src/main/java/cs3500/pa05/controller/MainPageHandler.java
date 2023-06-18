package cs3500.pa05.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
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
    if (event.getSource() instanceof MenuBar menuItem) {
      String id = menuItem.getId();
      if (id.equals("open") || id.equals("week")) {
        controller.loadScene("welcome.fxml");
      } else if (id.equals("save")) {
        //implement later
      } else if (id.equals("setMax")) {
        openPopup("max_tasks_stage.fxml");
      } else if (id.equals("task")) {
        openPopup("create_task");
      } else if (id.equals("event")) {
        openPopup("create_event");

      } else if (id.equals("light")) {
        //implement later
      } else if (id.equals("dark")) {
        //implement later
      } else if (id.equals("blue")) {
        //implement later
      }
    }
  }


    @FXML
    private void openPopup(String url) {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent popupContent = fxmlLoader.load();

        Stage popupStage = new Stage();
        popupStage.setScene(new Scene(popupContent));

        popupStage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }







}
