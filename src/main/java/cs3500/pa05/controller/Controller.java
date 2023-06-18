package cs3500.pa05.controller;

import cs3500.pa05.view.View;

public interface Controller {

  void run();

  void loadScene(String url);

  void setView(View view);
}
