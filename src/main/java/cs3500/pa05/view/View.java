package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import javafx.scene.Node;
import javafx.scene.Scene;

public interface View {

  Scene load();

  void loadScene(String url);

  void setControllerHelper(Controller controller);

  Scene changeStage();

}
