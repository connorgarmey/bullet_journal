package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.AbstractView;
import cs3500.pa05.view.LightTheme;
import cs3500.pa05.view.View;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Week implements EventHandler<ActionEvent> {
  View view;
  Model model;

  public Week(View view, Model model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void handle(ActionEvent event) {
    handleActionEvent(event);

  }

  @FXML
  private void handleActionEvent(ActionEvent event) {
    if (event.getSource() instanceof MenuBar menuItem) {
      if (menuItem.getId().equals("open")) {
        view.loadStart();
      } else if (menuItem.getId().equals("save")) {
        //implement later
      } else if (menuItem.getId().equals("setMax")) {
        openPopup();
      } else if (menuItem.getId().equals("task")) {
        view.loadTaskPopup();
      } else if (menuItem.getId().equals("week")) {
        view.loadStart();
      } else if (menuItem.getId().equals("event")) {
        view.loadEventPopup();
      } else if (menuItem.getId().equals("light")) {
        //implement later
      } else if (menuItem.getId().equals("dark")) {
        //implement later
      } else if (menuItem.getId().equals("blue")) {
        //implement later
      }
    }
  }

  @FXML
  private void openPopup() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("max_tasks_stage.fxml"));
      Parent popupContent = fxmlLoader.load();

      Stage popupStage = new Stage();
      popupStage.setScene(new Scene(popupContent));

      // Configure the popup stage properties

      popupStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }







}
