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
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }

// Called by controller
  public void loadStart() {
    this.loader.setLocation(getClass().getClassLoader().getResource("welcome.fxml"));
  }

  public void loadHome() {
    this.loader.setLocation(getClass().getClassLoader().getResource("main_page.fxml"));
  }

  public void loadTaskPopup() {
    this.loader.setLocation(getClass().getClassLoader().getResource("create_task.fxml"));
  }

  public void loadEventPopup() {
    this.loader.setLocation(getClass().getClassLoader().getResource("create_event.fxml"));
  }




}
