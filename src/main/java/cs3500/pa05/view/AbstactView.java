package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AbstactView implements View {
  FXMLLoader loader;

  public AbstactView(JournalController controller) {
    // look up and store the layout
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("startScreen.fxml"));
    // initialization and location setting omitted for brevity
    this.loader.setController(controller);
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
    this.loader.setLocation(getClass().getClassLoader().getResource("startScreen.fxml"));
  }

  public void loadHome() {
    this.loader.setLocation(getClass().getClassLoader().getResource("homeScreen.fxml"));
  }

  public void loadPopup() {
    this.loader.setLocation(getClass().getClassLoader().getResource("popupScreen.fxml"));
  }



}
