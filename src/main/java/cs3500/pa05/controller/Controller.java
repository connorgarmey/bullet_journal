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

  void loadScene(String url);

  void setView(View view);

  void popupHandler(String url, Stage popupStage, Scene scene);

  /**
   * Refreshes the week page to contain updated data
   *
   */
  void refreshData();

  List<Label> getAllLabels();
}
