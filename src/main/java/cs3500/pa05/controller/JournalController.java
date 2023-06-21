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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Overarching controller class that creates the FXML fields and assigns handlers
 *
 */
public class JournalController implements Controller {
  private Model model;
  private View view;


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
  private MenuBar menuBar;
  @FXML
  private HBox notesBox;
  // Icons
  @FXML
  private Label leftIcon;
  @FXML
  private Label rightIcon;
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

  // Fields for Adding Note
  @FXML
  private Button buttonNotes;
  @FXML
  private TextArea notesText;

  //fields for custom theme
  @FXML
  private Button createCustomTheme;
  @FXML
  private ChoiceBox<String> chooseFont;
  @FXML
  private ChoiceBox<String> chooseFontColor;
  @FXML
  private ChoiceBox<String> chooseBackground;
  @FXML
  private ChoiceBox<String> chooseIcon;
  @FXML
  private TextField customThemeName;

  //fields for changing task completion and editing
  @FXML
  private Button changeCompletion;
  @FXML
  private TextField taskToChange;
  @FXML
  private ChoiceBox<String> dayOfTheTask;
  @FXML
  private Button editTaskDetails;
  @FXML
  private MenuItem task;

  //fields for changing event
  @FXML
  private TextField eventToChange;
  @FXML
  private ChoiceBox<String> dayOfTheEvent;
  @FXML
  private Button editEventDetails;
  @FXML
  private MenuItem event;

  private MainPageHandler mainPageHandler;


  /**
   * Controller constructor
   *
   * @param model the main model class
   */
  public JournalController(Model model) {
    this.model = model;
  }


  /**
   * Runs the controller for the application
   *
   */
  @Override
  public void run() {
    loadScene("welcome.fxml");

  }


  /**
   * Loads a scene
   *
   * @param url the type of scene to load
   */
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
      Scene scene = view.changeStage();
      this.mainPageHandler = new MainPageHandler(this, scene, model);

      // Set title
      title.setText(model.getTitle());
      // Set actions for menu bar items
      for (Menu menu : menuBar.getMenus()) {
        menu.setOnAction(mainPageHandler);
        for (MenuItem item : menu.getItems()) {
          item.setOnAction(mainPageHandler);
        }
      }
      // Refresh the data
      refreshData();
      updateTheme(scene);
    }
  }

  /**
   * Updates the theme of the scene
   *
   * @param theScene the scene to modify
   */
  private void updateTheme(Scene theScene) {
    ChangeTheme change = new ChangeTheme(model.getTheme(), theScene.getRoot(), this);
    change.modifyLabels();
    change.modifyBackground();
  }

  /**
   * Sets the view of this controller
   *
   * @param view the given view
   */
  @Override
  public void setView(View view) {
    this.view = view;
  }


  /**
   * Delegates diff popups when needed
   *
   * @param url the popup's url
   * @param popupStage the new Stage object
   */
  @Override
  @FXML
  public void popupHandler(String url, Stage popupStage, Scene scene) {
    switch (url) {
      case "create_event.fxml" -> createEventButton.setOnAction(
          new CreateEventHandler(model, inputEventName, inputEventDescription, startHours,
              startMinutes, duration, eventDayDropDown, popupStage, this));
      case "create_task.fxml" ->
          createTaskButton.setOnAction(new CreateTaskHandler(this, model,
              inputTaskName,
              inputTaskDescription, taskDayDropDown, popupStage));
      case "max_tasks_stage.fxml" ->
          buttonMaxTasks.setOnAction(new SetMaxHandler(this, model, numMaxTasks,
              maxTasks, popupStage));
      case "max_events_stage.fxml" ->
          buttonMaxEvents.setOnAction(new SetMaxHandler(this, model, numMaxEvents,
              maxEvents, popupStage));
      case "add_note.fxml" ->
          buttonNotes.setOnAction(new CreateNoteHandler(this, model, notesText,
              popupStage));
      case "custom_theme.fxml" ->
          createCustomTheme.setOnAction(new CreateCustomTheme(chooseFont,
              chooseFontColor, chooseBackground,
              chooseIcon, customThemeName, model, popupStage, this, scene));
      case "edit_task.fxml" -> {
        changeCompletion.setOnAction(new ChangeCompletionHandler(taskToChange, dayOfTheTask,
            this, model, popupStage));
        editTaskDetails.setOnAction(new EditOccasionHandler(taskToChange, dayOfTheTask,
            this,
            model, true, popupStage, task));
      }
      case "edit_event.fxml" ->
        editEventDetails.setOnAction(new EditOccasionHandler(eventToChange, dayOfTheEvent,
            this,
            model, false, popupStage, event));
      case "welcome.fxml" ->
          loadScene("welcome.fxml");
      default -> { }
    }
  }

  /**
   * Refreshes the week page to contain updated data
   *
   */
  @Override
  public void refreshData() {
    updateCalendar();
    stats.setText(model.getCurrentStats());
    notesStuff.setText(model.getNotes());
  }


  /**
   * Updates the tasks and events for each day
   *
   */
  private void updateCalendar() {
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
                default -> { }
              }
            }
          }
        }
      }
    }

  }


  /**
   * Gets all labels in the main screen
   *
   * @return a list of Label
   */
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
    labels.add(leftIcon);
    labels.add(rightIcon);
    return labels;
  }





}
