package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.View;

public class JournalController implements Controller {
  Model model;
  View view;

  /**
   * Controller constructor
   *
   * @param model the main model class
   * @param view the initial view class
   */
  public JournalController(Model model, View view) {
    this.model = model;
    this.view = view;
    this.run();
  }

  @Override
  public void run() {



  }
}
