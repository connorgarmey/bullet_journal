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
        case "light" -> {
          Parent nodeLight = scene.getRoot();
          Image image = new Image(Objects.requireNonNull(classLoader.getResource("1.png")).toExternalForm());
          modifyLabels(new Font("Georgia Bold", 25), new Font("Georgia", 18),
              new Font("Georgia Bold Italic", 48), Color.DEEPPINK,
              image);
          modifyBackground(nodeLight, "#fff8ff");
          model.changeTheme(Theme.LIGHT);
        }
        case "dark" -> {
          Parent nodeDark = scene.getRoot();
          Image image = new Image(Objects.requireNonNull(classLoader.getResource("2.png")).toExternalForm());
          modifyLabels(new Font("Times New Roman Bold", 25), new Font("Times New Roman", 18),
              new Font("Times New Roman Bold Italic", 48), Color.BLACK,
              image);
          modifyBackground(nodeDark, "#A9A9A9");
          model.changeTheme(Theme.DARK);
        }
        case "blue" -> {
          Parent nodeBlue = scene.getRoot();
          Image image = new Image(Objects.requireNonNull(classLoader.getResource("3.png")).toExternalForm());
          modifyLabels(new Font("Arial Bold", 25), new Font("Arial", 18),
              new Font("Arial Bold Italic", 48), Color.DARKSLATEBLUE,
              image);
          modifyBackground(nodeBlue, "#6495ED");
          model.changeTheme(Theme.BLUE);
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

  /**
   * Modifies the look of the headers and content
   *
   * @param headerFont the header font
   * @param stuffFont the content font
   * @param otherFont all other
   * @param fontColor the color
   */
  private void modifyLabels(Font headerFont, Font stuffFont, Font otherFont, Color fontColor, Image image) {
    List<Label> labels = controller.getAllLabels();
    for (Label label : labels) {
      if (label.getId().endsWith("Header")) {
        label.setFont(headerFont);
        label.setTextFill(fontColor);
      } else if (label.getId().endsWith("Stuff")) {
        label.setFont(stuffFont);
        label.setTextFill(fontColor);
      } else if (label.getId().endsWith("Icon")) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);  // Adjust the width as needed
        imageView.setFitHeight(70); // Adjust the height as needed
        imageView.setPreserveRatio(true);
        label.setGraphic(imageView);
      }
      else {
        label.setFont(otherFont);
        label.setTextFill(fontColor);
      }
    }
  }

  /**
   * Modifies the background color
   *
   * @param node the parent node
   * @param backgroundColor the color to change to
   */
  private void modifyBackground(Parent node, String backgroundColor) {
    if (node instanceof Region region) {
      region.setStyle("-fx-background-color: " + backgroundColor + ";");
    }
  }











}
