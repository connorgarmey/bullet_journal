package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Model;
import cs3500.pa05.model.ModelImpl;
import cs3500.pa05.view.LightTheme;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of program
 */
public class Driver extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) {

    View view = new LightTheme();
    Model model = new ModelImpl();
    Controller controller = new JournalController(model, view);

    try {
      System.out.println(1);
      // load and place the view's scene onto the stage
      primaryStage.setScene(view.load());
      System.out.println(2);
      // run the controller
      controller.run();
      System.out.println(3);
      // render the stage
      primaryStage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }

  }
}
