package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Model;
import cs3500.pa05.model.ModelImpl;
import cs3500.pa05.view.View;
import cs3500.pa05.view.ViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Entry point of program
 */
public class Driver extends Application {

  /**
   * Main method
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Starts the JavaFX application
   *
   * @param primaryStage the primary stage for
   *                     this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed,
   *                     but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    Model model = new ModelImpl();
    Controller controller = new JournalController(model);
    loader.setController(controller);
    View view = new ViewImpl(loader, primaryStage);
    controller.setView(view);

    try {
      // load and place the view's scene onto the stage
      primaryStage.setScene(view.load());
      // run the controller
      controller.run();
      // render the stage
      primaryStage.show();

    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

}
