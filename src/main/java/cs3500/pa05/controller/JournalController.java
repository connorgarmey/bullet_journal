package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class JournalController implements Controller {
  private Model model;
  private View view;

  @FXML
  private MenuBar menuBar;

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
    Week week = new Week(view, model);

    for (MenuItem menuItem : menuBar.getMenus()) {
      menuItem.setOnAction(week);
    }




  }
}
