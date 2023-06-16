package cs3500.pa05.model;

public interface Model {

  void addTask();

  void addEvent();

  void changeTheme(Theme theme);

  void updateNumbers();

  WeekJson newWeek();
}
