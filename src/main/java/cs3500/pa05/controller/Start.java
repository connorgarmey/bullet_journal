package cs3500.pa05.controller;

import cs3500.pa05.model.ModelImpl;
import cs3500.pa05.model.json.WeekJson;
import java.io.File;
import java.nio.file.Path;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Start implements EventHandler<Event> {
  @FXML
  private TextField inputFileLoad;
  @FXML
  private Button loadFile;
  @FXML
  private TextField inputFileCreate;
  @FXML
  private Button createFile;

  private ModelImpl model;
  

  /**
   * Directs the controller to the correct event handler given the button clicked
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    if (event.getSource() == createFile) {
      handleCreateFile();
    } else if (event.getSource() == loadFile) {
      handleLoadFile();
    }
  }

  /**
   * Handles the creation of a file if the user wants to create a new file
   */
  @FXML
  private void handleCreateFile() {
    // Load the name for the user input
    String name =  inputFileLoad.getText();
    String pathName = "pa05-dawgpawtrol3-license-to-journal/src/main/resources/";
    String file = pathName + name + ".bujo";
    File fileToCreate = new File(file);

    // Check if it is null
    if (name.isEmpty()) {
      showAlert("Error", "No Input Received", "Please enter a file name.");
    } else if (isInvalid(name)) {
      showAlert("Error", "Invalid Input", "Please enter a valid file name.");
    }

    // Creating Model and Writer objects to create our WeekJson and allow us to write
    ModelImpl model = new ModelImpl();
    WeekJson newWeek = model.newWeek();
    WriteFile writer = new WriteFile();

    // If it is not null, create the file
    writer.writeToFile(fileToCreate, newWeek); // replace with whatever we use to create the file
  }

  /**
   * Handles the loading of a file if the user wants to load a pre-existing file
   */
  @FXML
  private void handleLoadFile() {
    // Load the name for the user input
    String name =  inputFileLoad.getText();
    String pathName = "pa05-dawgpawtrol3-license-to-journal/src/main/resources/";
    String file = pathName + name + ".bujo";
    File fileToLoad = new File(file);

    // Check if it is null
    if (name.isEmpty()) {
      showAlert("Error", "Invalid Input", "Please enter a valid file name.");
    } else if (!fileToLoad.exists()) { // does not exist -- not invalid
      showAlert("Error", "Invalid Input", "File does not exist.");
    }

    // Creating Model and Writer objects to create our WeekJson and allow us to write
    ModelImpl model = new ModelImpl();
    WriteFile writer = new WriteFile();

    // If all checks pass, load the file
    writer.writeToFile(fileToLoad, );
  }

  /**
   * Provides the error alert if a user needs to provide better input
   *
   * @param title The title of the alert -- should always be Error
   * @param header The reasoning for the error
   * @param content What the user must do to fix the error
   */
  private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  /**
   * @return true if the inputted file name is INVALID
   */
  private boolean isInvalid(String fileName) {
    // Check the name only utilizes valid characters
    if (!fileName.matches("^[a-zA-Z0-9 ._-]+$")) {
      return true;
    }

    // Check the name is a valid length
    if (fileName.length() > 255) {
      return true;
    }

    return false;
  }


}
