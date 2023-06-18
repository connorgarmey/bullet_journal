package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class JournalController implements Controller {
  private Model model;
  private View view;

  @FXML
  private MenuBar menuBar;

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
  private ChoiceBox<String> inputEventDay;
  @FXML
  private Spinner<Integer> startHours;
  @FXML
  private Spinner<Integer> startMinutes;
  @FXML
  private Spinner<Integer> duration;
  @FXML
  private Button createEventButton;

  // Fields for CreateTaskHandler
  @FXML
  private TextField inputTaskName;
  @FXML
  private TextField inputTaskDescription;
  @FXML
  private ChoiceBox<String> inputTaskDay;
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
      view.load();

      for (MenuItem menuItem : menuBar.getMenus()) {
        menuItem.setOnAction(mainPageHandler);
      }

    }

  }

  @Override
  public void setView(View view) {
    this.view = view;
  }







}
