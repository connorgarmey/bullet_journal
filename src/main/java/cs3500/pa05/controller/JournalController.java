package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JournalController implements Controller {
  private Model model;
  private View view;

  // Fields for MainPageHandler
  @FXML
  private MenuBar menuBar;
  @FXML
  private HBox notesBox;

  // Fields for StartHandler
  @FXML
  private TextField inputFileLoad;
  @FXML
  private TextField inputFileCreate;
  @FXML
  private Button loadFile;
  @FXML
  private Button createFile;

  // Fields for CreateEventHAndler
  @FXML
  private TextField inputEventName;
  @FXML
  private TextField inputEventDescription;
  @FXML
  private ChoiceBox<String> eventDayDropDown;
  @FXML
  private ChoiceBox<String>  startHours;
  @FXML
  private ChoiceBox<String>  startMinutes;
  @FXML
  private TextField duration;
  @FXML
  private Button createEventButton;

  // Fields for CreateTaskHandler
  @FXML
  private TextField inputTaskName;
  @FXML
  private TextField inputTaskDescription;
  @FXML
  private ChoiceBox<String> taskDayDropDown;
  @FXML
  private Button createTaskButton;

  private MainPageHandler mainPageHandler;

  /**
   * Controller constructor
   *
   * @param model the main model class
   */
  public JournalController(Model model) {
    this.model = model;
    this.mainPageHandler = new MainPageHandler(this);
  }


  @Override
  public void run() {
    loadScene("welcome.fxml");

  }

  @Override
  public void loadScene(String url) {
    if (url.equals("welcome.fxml")) {
      view.loadScene(url);
      StartHandler startHandler = new StartHandler(model, this, inputFileLoad, inputFileCreate);
      loadFile.setOnAction(startHandler);
      createFile.setOnAction(startHandler);
    }
    else if (url.equals("main_page.fxml")) {
      view.setControllerHelper(this);
      view.loadScene(url);
      changeStage();

      for (Menu menu : menuBar.getMenus()) {
        menu.setOnAction(mainPageHandler);
        for (MenuItem item : menu.getItems()) {
          item.setOnAction(mainPageHandler);
        }
      }
      refreshData();

    }

  }

  @Override
  public void setView(View view) {
    this.view = view;
  }

  @Override
  public void changeStage() {
    view.changeStage();
  }


  @Override @FXML
  public void popupHandler(String url, Stage popupStage) {
    switch (url) {
      case "create_event.fxml" -> createEventButton.setOnAction(
          new CreateEventHandler(model, inputEventName, inputEventDescription, startHours,
              startMinutes, duration, eventDayDropDown, popupStage, this));
      case "create_task.fxml" ->
          createTaskButton.setOnAction(new CreateTaskHandler(this, model, inputTaskName,
              inputTaskDescription, taskDayDropDown, popupStage));
    }
  }

  @Override
  public void refreshData() {
    for (Node box : notesBox.getChildren()) {
      if (box instanceof VBox vbox) {
        for (Node node : vbox.getChildren()) {
          if (node instanceof Label label) {
            String id = label.getId();
            if (id != null) {
              switch (id) {
                case "mondayStuff" -> label.setText(model.getDaysAgenda(0));
                case "tuesdayStuff" -> label.setText(model.getDaysAgenda(1));
                case "wednesdayStuff" -> label.setText(model.getDaysAgenda(2));
                case "thursdayStuff" -> label.setText(model.getDaysAgenda(3));
                case "fridayStuff" -> label.setText(model.getDaysAgenda(4));
                case "saturdayStuff" -> label.setText(model.getDaysAgenda(5));
                case "sundayStuff" -> label.setText(model.getDaysAgenda(6));
              }
            }
          }
        }
      }
    }

  }





}
