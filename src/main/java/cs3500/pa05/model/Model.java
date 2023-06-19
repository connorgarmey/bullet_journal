package cs3500.pa05.model;

import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Path;

public interface Model {

  void addTask(String name, String description, String day);

  void addEvent(String n, String des, String d, int hour, int minute, int dur);

  void changeTheme(Theme theme);

  WeekJson newWeek();

  void makeWeek(String week);

  void saveData();

  String getDaysAgenda(int day);

  void updateBujoFile(Path path);

  void updateMax(int max, String which);

  String getCurrentStats();

  boolean canAdd(Boolean isTask, String dayRep);

  void sortEvents(int sortType);
}
