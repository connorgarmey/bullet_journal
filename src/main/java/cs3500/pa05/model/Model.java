package cs3500.pa05.model;

import cs3500.pa05.model.json.WeekJson;

public interface Model {

  void addTask(Task task);

  void addEvent(Event event);

  void changeTheme(Theme theme);

  void updateNumbers();

  WeekJson newWeek();
}
