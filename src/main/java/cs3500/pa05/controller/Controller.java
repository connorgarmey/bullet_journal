package cs3500.pa05.controller;

import cs3500.pa05.view.View;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Controller interface with necessary methods to be implemented
 *
 */
public interface Controller {

  /**
   * Runs the controller for the application
   *
   */
  void run();

  /**
   * Loads a scene
   *
   * @param url the type of scene to load
   */
  void loadScene(String url);

  /**
   * Sets the view of this controller
   *
   * @param view the given view
   */
  void setView(View view);

  /**
   * Delegates diff popups when needed
   *
   * @param url the popup's url
   * @param popupStage the new Stage object
   */
  void popupHandler(String url, Stage popupStage, Scene scene);

  /**
   * Refreshes the week page to contain updated data
   *
   */
  void refreshData();

  /**
   * Gets all labels in the main screen
   *
   * @return a list of Label
   */
  List<Label> getAllLabels();
}
