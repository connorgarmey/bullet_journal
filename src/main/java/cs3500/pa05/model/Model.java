package cs3500.pa05.model;

import cs3500.pa05.model.json.WeekJson;
import java.security.Timestamp;

public interface Model {

  void addTask(String name, String description, String day);

  void addEvent(String n, String des, String d, int hour, int minute, int dur);

  void changeTheme(Theme theme);

  void updateNumbers();

  WeekJson newWeek();

  void makeWeek(String week);

  WeekJson currentData();

  String getDaysAgenda(int day);
}
