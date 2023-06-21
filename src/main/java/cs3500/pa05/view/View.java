package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * Represents the methods to be implemented by the View Implementation
 */
public interface View {

  /**
   * Loads the scene
   *
   * @return scene to be loaded
   */
  Scene load();

  /**
   * Loads a scene
   *
   * @param url image to load
   */
  void loadScene(String url);

  /**
   * Helper to set the controller
   *
   * @param controller controller to assign
   */
  void setControllerHelper(Controller controller);

  /**
   * Loads the scene and sets it on on the stage
   *
   * @return the new scene
   */
  Scene changeStage();

}
