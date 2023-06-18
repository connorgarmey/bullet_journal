package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AbstractView implements View {
  FXMLLoader loader;

  public AbstractView() {
    // look up and store the layout
    this.loader = new FXMLLoader();
    loadHome();

  }


  @Override
  public Scene load() throws IllegalStateException {
    // load the layout
    try {
      System.out.println(loader.getLocation());
      System.out.println(loader.getController().toString());
      return this.loader.load();
    } catch (IOException exc) {
      exc.printStackTrace();
      throw new IllegalStateException("Unable to load layout.");
    }
  }

  public void loadScene(String url) {
    this.loader.setLocation(getClass().getClassLoader().getResource(url));

  }

  public void setControllerHelper(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
  }





}
