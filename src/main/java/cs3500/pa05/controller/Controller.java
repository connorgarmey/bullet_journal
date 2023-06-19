package cs3500.pa05.controller;

import cs3500.pa05.view.View;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public interface Controller {

  void run();

  void loadScene(String url);

  void setView(View view);

  Scene changeStage();

  void popupHandler(String url, Stage popupStage);

  void refreshData();

  void setMainPageHandler(Scene scene);

  List<Label> getAllLabels();
}
