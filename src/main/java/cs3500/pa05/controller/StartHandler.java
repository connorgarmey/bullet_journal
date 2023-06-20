package cs3500.pa05.controller;

import static cs3500.pa05.controller.ShowAlert.showAlert;

import cs3500.pa05.model.Model;
import cs3500.pa05.model.json.WeekJson;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartHandler implements EventHandler<ActionEvent> {

  private Model model;
  private Controller controller;
  TextField inputFileLoad;
  TextField inputFileCreate;

  public StartHandler(Model model, Controller controller, TextField inputFileLoad, TextField inputFileCreate) {
    this.model = model;
    this.controller = controller;
    this.inputFileLoad = inputFileLoad;
    this.inputFileCreate = inputFileCreate;
  }


  /**
   * Directs the controller to the correct event handler given the button clicked
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    if (event.getSource() instanceof Button) {
      Button button = (Button) event.getSource();
      if (button.getId().equals("createFile")) {
        handleCreateFile();
      } else if (button.getId().equals("loadFile")) {
        handleLoadFile();
      }
    }
  }

  /**
   * Handles the creation of a file if the user wants to create a new file
   */
  @FXML
  private void handleCreateFile() {
    // Load the name for the user input
    String name =  spacesToDashes(inputFileCreate.getText());

    // Check if the name is null
    if (name.isEmpty()) {
      showAlert("Error", "No Input Received", "Please enter a file name.");
    } else if (isInvalid(name)) {
      showAlert("Error", "Invalid Input", "Please enter a valid file name.");
      inputFileCreate.clear();
    }

    // If the name is valid, create the file
    String pathName = "src/main/resources/";
    Path path = Path.of(pathName + name + ".bujo");

    // Creating Model and Writer objects to create our WeekJson and allow us to write
    WeekJson newWeek = model.newWeek();
    model.updateBujoFile(path);
    WriteFile writer = new WriteFile();
    // If it is not null, create the file
    writer.writeToFile(path, newWeek);
    controller.loadScene("main_page.fxml");

  }

  /**
   * changes all the spaces to underscores
   *
   * @param original given name
   * @return name without any spaces
   */
  private String spacesToDashes(String original) {
    return original.replaceAll(" ", "_");
  }

  /**
   * Handles the loading of a file if the user wants to load a pre-existing file
   */
  @FXML
  private void handleLoadFile() {
    // Load the name for the user input
    String name = spacesToDashes(inputFileLoad.getText());

    // Check if it is null
    if (name.isEmpty()) {
      showAlert("Error", "Invalid Input", "Please enter a valid file name.");
      inputFileLoad.clear();
    }

    // If the name is not null, try to create the loaded file
    String pathName = "src/main/resources/";
    String fileName = pathName + name + ".bujo";



    try {
      // Load the file and create a scanner to read the file
      Path filePath = Path.of(fileName);
      Scanner scanner = new Scanner(filePath);
      StringBuilder stringBuilder = new StringBuilder();

      // While the file has more lines, continue to add to string builder
      while (scanner.hasNextLine()) {
        stringBuilder.append(scanner.nextLine());
      }

      // Translate the final string builder to a string
      String fileString = stringBuilder.toString();

      // If all checks pass, read the file and update the model
      model.makeWeek(fileString);
      model.updateBujoFile(filePath);
      controller.loadScene("main_page.fxml");
    } catch (Exception e) {
      e.printStackTrace();
      showAlert("Error", "Invalid Input", "File does not exist.");
    }
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
