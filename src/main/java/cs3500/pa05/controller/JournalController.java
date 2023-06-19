package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.View;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

  //Main Page Fields
  @FXML
  private Label title;
  @FXML
  private Label mondayStuff;
  @FXML
  private Label tuesdayStuff;
  @FXML
  private Label wednesdayStuff;
  @FXML
  private Label thursdayStuff;
  @FXML
  private Label fridayStuff;
  @FXML
  private Label saturdayStuff;
  @FXML
  private Label sundayStuff;
  @FXML
  private Label mondayHeader;
  @FXML
  private Label tuesdayHeader;
  @FXML
  private Label wednesdayHeader;
  @FXML
  private Label thursdayHeader;
  @FXML
  private Label fridayHeader;
  @FXML
  private Label saturdayHeader;
  @FXML
  private Label sundayHeader;
  @FXML
  private Label notesHeader;
  @FXML
  private Label notesStuff;
  @FXML
  private Label stats;

  //Fields for Setting Max tasks
  @FXML
  private TextField numMaxTasks;
  @FXML
  private Button buttonMaxTasks;
  @FXML
  private Label maxTasks;

  //Fields for Setting Max tasks
  @FXML
  private TextField numMaxEvents;
  @FXML
  private Button buttonMaxEvents;
  @FXML
  private Label maxEvents;






  private MainPageHandler mainPageHandler;

  /**
   * Controller constructor
   *
   * @param model the main model class
   */
  public JournalController(Model model) {
    this.model = model;
  }


  @Override
  public void run() {
    loadScene("welcome.fxml");

  }

  public void setMainPageHandler(Scene scene) {
    this.mainPageHandler = new MainPageHandler(this, scene, model);
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
      Scene scene = changeStage();
      this.setMainPageHandler(scene);

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
  public Scene changeStage() {
    return view.changeStage();
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
      case "max_tasks_stage.fxml" ->
          buttonMaxTasks.setOnAction(new SetMaxHandler(this, model, this.numMaxTasks, this.maxTasks));
      case "max_events_stage.fxml" ->
          buttonMaxEvents.setOnAction(new SetMaxHandler(this, model, this.numMaxEvents, this.maxEvents));
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

  @Override
  public List<Label> getAllLabels() {
    List<Label> labels = new ArrayList<>();
    labels.add(title);
    labels.add(mondayStuff);
    labels.add(mondayHeader);
    labels.add(tuesdayStuff);
    labels.add(tuesdayHeader);
    labels.add(wednesdayStuff);
    labels.add(wednesdayHeader);
    labels.add(thursdayStuff);
    labels.add(thursdayHeader);
    labels.add(fridayStuff);
    labels.add(fridayHeader);
    labels.add(saturdayStuff);
    labels.add(saturdayHeader);
    labels.add(sundayStuff);
    labels.add(sundayHeader);
    labels.add(notesHeader);
    labels.add(notesStuff);
    labels.add(stats);
    return labels;
  }





}
