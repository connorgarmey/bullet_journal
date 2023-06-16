package cs3500.pa05.controller;

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
    WriteFile fileToCreate = new WriteFile(name);

    // Check if it is null
    if (fileToCreate.fileName.isEmpty()) {
      showAlert("Error", "No Input Received", "Please enter a file name.");
    } else if (fileToCreate.isInvalid()) {
      showAlert("Error", "Invalid Input", "Please enter a valid file name.");
    }

    // If it is not null, create the file
    fileToCreate.createFile(); // replace with whatever we use to create the file
  }

  /**
   * Handles the loading of a file if the user wants to load a pre-existing file
   */
  @FXML
  private void handleLoadFile() {
    // Load the name for the user input
    String name =  inputFileLoad.getText();
    WriteFile fileToLoad = new WriteFile(name);

    // Check if it is null
    if (fileToLoad.fileName.isEmpty()) {
      showAlert("Error", "Invalid Input", "Please enter a valid file name.");
    } else if (fileToLoad.isInvalid()) { // does not exist -- not invalid

    }

    // If it succeeds, we need to load the file

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



}
