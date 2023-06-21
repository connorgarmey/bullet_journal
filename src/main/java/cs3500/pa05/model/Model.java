package cs3500.pa05.model;

import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.CustomTheme;
import java.nio.file.Path;

public interface Model {

  void addTask(String name, String description, String day);

  void addEvent(String n, String des, String d, int hour, int minute, int dur);

  void changeTheme(CustomTheme theme);

  WeekJson newWeek();

  void makeWeek(String week);

  void saveData();

  String getDaysAgenda(int day);

  void updateBujoFile(Path path);

  void updateMax(int max, String which);

  String getCurrentStats();

  boolean canAdd(Boolean isTask, String dayRep);

  void sortEvents(int sortType);

  String getTitle();

  CustomTheme getTheme();

  void addNote(String text);

  String getNotes();

  boolean taskExists(String day, String task);
  boolean taskAlreadyComplete(String day, String task);
  void updateCompletion(String day, String task);

  void deleteOccasion(String day, String name, boolean isTask);

  //everything above is good
}
