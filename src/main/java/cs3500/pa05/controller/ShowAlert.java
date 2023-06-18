package cs3500.pa05.controller;

import javafx.scene.control.Alert;

/**
 * Class for an alert
 */
public class ShowAlert {

  /**
   * Provides the error alert if a user needs to provide better input
   *
   * @param title The title of the alert -- should always be Error
   * @param header The reasoning for the error
   * @param content What the user must do to fix the error
   */
  public static void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

}
