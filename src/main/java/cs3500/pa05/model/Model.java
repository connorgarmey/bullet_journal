package cs3500.pa05.model;

import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.CustomTheme;
import java.nio.file.Path;

/**
 * Model interface to be implemented by the Model Implementation class
 */
public interface Model {

  /**
   * Creates a new task given inputted task details
   *
   * @param name name of the task
   * @param description description of the task
   * @param day day the task takes place
   */
  void addTask(String name, String description, String day);

  /**
   * Adds an event given inputted event details
   *
   * @param n name of the event
   * @param des description of the event
   * @param d day the event takes place
   * @param hour hour of the day the event starts
   * @param minute minute of the hour that it starts
   * @param dur duration of the event
   */
  void addEvent(String n, String des, String d, int hour, int minute, int dur);

  /**
   * Sets a new theme
   *
   * @param theme the theme to switch it to
   */
  void changeTheme(CustomTheme theme);

  /**
   * Creates a new, default WeekJson
   *
   * @return a WeekJson representing the new week
   */
  WeekJson newWeek();

  /**
   * Makes the default week given the default Model fields
   *
   * @param week an entire week written out as a String
   */
  void makeWeek(String week);

  /**
   * Saves the data by writing the current data to the Bujo file
   */
  void saveData();

  /**
   * Gets the events and tasks for a day
   *
   * @param day the day of the week
   *
   * @return a String representation of the day's agenda
   */
  String getDaysAgenda(int day);

  /**
   * Updates the path to the Bujo file
   *
   * @param path desired path for the file
   */
  void updateBujoFile(Path path);

  /**
   * Updates the max allowed number of events or tasks
   *
   * @param max the new max to set
   * @param which which occasion to set the max for (event or task)
   */
  void updateMax(int max, String which);

  /**
   * Gets updated statistics for the week
   *
   * @return a String representation of statistics
   */
  String getCurrentStats();

  /**
   * Given the max, can the user add another task or event to a day?
   *
   * @param isTask is the occasion a task
   * @param dayRep the day of the week
   *
   * @return whether it can be add
   */
  boolean canAdd(Boolean isTask, String dayRep);

  /**
   * Sorts the events
   *
   * @param sortType the type of sorting
   */
  void sortEvents(int sortType);

  /**
   * Gets the title of the file
   *
   * @return the .bujo file path name to String
   */
  String getTitle();

  /**
   * Gets the current theme
   *
   * @return the current theme
   */
  CustomTheme getTheme();

  /**
   * Adds a note to the notes section
   *
   * @param text text to be added
   */
  void addNote(String text);

  /**
   * Gets the notes and returns it properly formatted
   *
   * @return string representation of all notes
   */
  String getNotes();

  /**
   * Returns whether or not a given task exists
   *
   * @param day Day of the week to check
   * @param occ Name of the occasion to check
   * @return true if exists, false if it doesn't exist
   */
  boolean occasionExists(String day, String occ, boolean isTask);

  /**
   * Returns whether or not the task is completed
   *
   * @param day day of the week
   * @param task name of the task
   * @return true if the task is not complete
   */
  boolean taskAlreadyComplete(String day, String task);

  /**
   * Updates the completion of the task
   *
   * @param day Day the task takes place
   * @param task Name of the task
   */
  void updateCompletion(String day, String task);

  /**
   * Deletes the occasion
   *
   * @param day the day of the week the occasion is on
   * @param name the name of the occasion
   * @param isTask if it is a task
   */
  void deleteOccasion(String day, String name, boolean isTask);

}
