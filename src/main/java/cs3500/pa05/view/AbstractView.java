package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The abstract view class to be extended by the ViewImpl
 */
public abstract class AbstractView implements View {
  private FXMLLoader loader;
  private Stage primaryStage;

  /**
   * Instantiates an abstract view
   *
   * @param loader an FXMLLoader to load in the GUI views
   * @param primaryStage the main stage
   */
  public AbstractView(FXMLLoader loader, Stage primaryStage) {
    // look up and store the layout
    this.primaryStage = primaryStage;
    this.loader = loader;
    this.loader.setLocation(getClass().getClassLoader().getResource("welcome.fxml"));
  }


  /**
   * Loads the scene, and throws an exception if it is unable to load
   *
   * @return the scene to display
   * @throws IllegalStateException when scene cannot load
   */
  @Override
  public Scene load() throws IllegalStateException {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      exc.printStackTrace();
      throw new IllegalStateException("Unable to load layout.");
    }
  }

  /**
   * Loads a scene
   *
   * @param url which scene to load
   */
  public void loadScene(String url) {
    this.loader.setLocation(getClass().getClassLoader().getResource(url));

  }

  /**
   * Helper to set the controller
   *
   * @param controller a controller
   */
  public void setControllerHelper(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
  }

  /**
   * Loads the scene and sets it on on the stage
   *
   * @return the new scene to load
   */
  public Scene changeStage() {
    Scene scene = load();
    primaryStage.setScene(scene);
    primaryStage.show();
    return scene;
  }







}
