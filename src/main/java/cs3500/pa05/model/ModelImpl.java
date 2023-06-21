package cs3500.pa05.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.controller.WriteFile;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.StatsJson;
import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.CustomTheme;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents the Implementation of the Model, extending the Model interface
 */
public class ModelImpl implements Model {
  private List<Day> week;
  private CustomTheme theme;
  private int numEvents;
  private int numTasks;
  private double completionPercent;
  private ObjectMapper mapper;
  private Path bujoFile;
  private int maxTasks;
  private int maxEvents;
  private String notes;

  /**
   * Instantiates an initial, default Model
   */
  public ModelImpl() {
    this.week = makeEmptyDays();
    this.numEvents = 0;
    this.numTasks = 0;
    this.completionPercent = 0.00;
    this.theme = Theme.LIGHT.getTheTheme();
    this.mapper = new ObjectMapper();
    this.notes = "";
    this.maxTasks = 5;
    this.maxEvents = 5;
  }

  /**
   * Makes the default week given the default Model fields
   *
   * @param week an entire week written out as a String
   */
  @Override
  public void makeWeek(String week) {
    try {
      WeekJson weekJson = mapper.readValue(week, WeekJson.class);
      updateDays(weekJson.days());
      this.numEvents = weekJson.stats().event();
      this.numTasks = weekJson.stats().task();
      this.completionPercent = weekJson.stats().percent();
      this.theme = CustomTheme.createCustomTheme(weekJson.theme());
      this.notes = weekJson.stats().notes();
      this.maxTasks = weekJson.stats().maxTasks();
      this.maxEvents = weekJson.stats().maxEvents();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Gets the current theme
   *
   * @return the current theme
   */
  @Override
  public CustomTheme getTheme() {
    return this.theme;
  }


  /**
   * Returns the Day given the name of the Day
   *
   * @param dayRep name of the day as a string
   * @return the Day object
   */
  private Day getDayFromRep(String dayRep) {
    Day dayToReturn = null;
    for (Day day : week) {
      if (day.isSameDay(dayRep)) {
        dayToReturn = day;
      }
    }
    return dayToReturn;
  }

  /**
   * Given the max, can the user add another task or event to a day?
   *
   * @param isTask is the occasion a task
   * @param dayRep the day of the week
   *
   * @return whether it can be add
   */
  public boolean canAdd(Boolean isTask, String dayRep) {
    Day day = getDayFromRep(dayRep);
    if (isTask) {
      return day.canAdd(isTask, maxTasks);
    } else {
      return day.canAdd(isTask, maxEvents);
    }
  }

  /**
   * Goes through each Day in the week and updates their DayJson
   *
   * @param dayJsons JSON representation of each Day
   */
  private void updateDays(List<DayJson> dayJsons) {
    for (DayJson dayJson : dayJsons) {
      for (Day day : week) {
        if (day.isSameDay(dayJson.day())) {
          day.update(dayJson);
        }
      }
    }
  }

  /**
   * Constructs a list of empty Days
   *
   * @return a list of Empty Days
   */
  private List<Day> makeEmptyDays() {
    List<String> stringRep = makeDaysOfWeek(new ArrayList<>());
    List<Day> days = new ArrayList<>();
    for (String s : stringRep) {
      Day day = new Day(s);
      days.add(day);
    }
    return days;
  }


  /**
   * Creates a new task given inputted task details
   *
   * @param n name of the task
   * @param des description of the task
   * @param d day the task takes place
   */
  @Override
  public void addTask(String n, String des, String d) {
    Occasion taskToAdd = new Task(n, des, d);
    for (Day day : this.week) {
      if (day.isSameDay(d)) {
        day.addTask(taskToAdd);
      }
    }
  }


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
  @Override
  public void addEvent(String n, String des, String d, int hour, int minute, int dur) {
    Occasion eventToAdd = new Event(n, des, d, hour, minute, dur);
    for (Day day : this.week) {
      if (day.isSameDay(d)) {
        day.addEvent(eventToAdd);
      }
    }
  }


  /**
   * Sets a new theme
   *
   * @param newTheme the theme to switch it to
   */
  @Override
  public void changeTheme(CustomTheme newTheme) {
    this.theme = newTheme;
  }


  /**
   * Creates a new, default WeekJson
   *
   * @return
   */
  @Override
  public WeekJson newWeek() {
    List<String> dayString = makeDaysOfWeek(new ArrayList<>());
    List<DayJson> days = new ArrayList<>();
    for (String s : dayString) {
      DayJson day = new DayJson(s,new ArrayList<>(),new ArrayList<>());
      days.add(day);
    }
    ThemeJson theme = new ThemeJson("Georgia", "Pink", "Light Pink", "Light", "LIGHT");
    StatsJson stats = new StatsJson(0, 0, 5, 5, "", 100);
    return new WeekJson(days, theme, stats);
  }

  /**
   * Constructs a list of Days of the week as String
   *
   * @param days empty list of strings to add the day names to
   * @return the list with each day string added
   */
  private List<String> makeDaysOfWeek(List<String> days) {
    days.add("Monday");
    days.add("Tuesday");
    days.add("Wednesday");
    days.add("Thursday");
    days.add("Friday");
    days.add("Saturday");
    days.add("Sunday");
    return days;
  }

  /**
   * Constructs a WeekJson with the current data
   *
   * @return an updated WeekJson
   */
  private WeekJson currentData() {
    List<DayJson> dayJsons = new ArrayList<>();
    System.out.println(week);
    for (Day day : week) {
      DayJson dayJson = day.makeDayJson();
      dayJsons.add(dayJson);
    }
    StatsJson statsJson = new StatsJson(this.numEvents, this.numTasks, this.maxEvents,
        this.maxTasks, this.notes, this.completionPercent);
    ThemeJson themeJson = this.theme.createJson();
    System.out.println(this.theme);
    return new WeekJson(dayJsons, themeJson, statsJson);
  }

  /**
   * Updates the path to the Bujo file
   *
   * @param path desired path for the file
   */
  public void updateBujoFile(Path path) {
    this.bujoFile = path;
  }

  /**
   * Saves the data by writing the current data to the Bujo file
   */
  public void saveData() {
    WriteFile writer = new WriteFile();
    writer.writeToFile(this.bujoFile, currentData());
  }

  /**
   * Updates the max allowed number of events or tasks
   *
   * @param max the new max to set
   * @param which which occasion to set the max for (event or task)
   */
  public void updateMax(int max, String which) {
    if (which.equals("tasks")) {
      this.maxTasks = max;
    } else {
      this.maxEvents = max;
    }
  }

  /**
   * Gets the events and tasks for a day
   *
   * @param day the day of the week
   *
   * @return a String representation of the day's agenda
   */
  @Override
  public String getDaysAgenda(int day) {
    return week.get(day).getAgenda();
  }


  /**
   * Gets updated statistics for the week
   *
   * @return a String representation of statistics
   */
  @Override
  public String getCurrentStats() {
    updateNumbers();
    int total = 0;
    int completed = 0;
    for (Day day : week) {
      total += day.getNumTasks();
      completed += day.getNumCompletedTasks();

    }
    if (completed == 0 && total == 0) {
      completionPercent = 100;
    } else {
      completionPercent = (double) completed / total * 100;
    }

    return "Events: " + numEvents + ",   Tasks: " + numTasks
        + "   " + completionPercent +"% completed";
  }


  /**
   * Updates the number of tasks and events for a week
   */
  private void updateNumbers() {
    numTasks = 0;
    numEvents = 0;
    for (Day day : week) {
      numTasks += day.getNumTasks();
      numEvents += day.getNumEvents();
    }
  }

  /**
   * Sorts the events
   *
   * @param sortType the type of sorting
   */
  @Override
  public void sortEvents(int sortType) {
    switch (sortType) {
      case 1 -> {
        for (Day day : week) {
          day.sortListEvents(new SortByDuration());
        }
      }
      case 2 -> {
        for (Day day : week) {
          day.sortListEvents(new SortByName());
        }
      }
      case 3 -> {
        for (Day day : week) {
          day.sortListTasks(new SortByName());
        }
      }
    }
  }

  /**
   * Gets the title of the file
   *
   * @return the .bujo file path name to String
   */
  @Override
  public String getTitle() {
    return bujoFile.getFileName().toString().replaceAll("_", " ").
        replaceAll(".bujo", "");
  }

  /**
   * Adds a note to the notes section
   *
   * @param text text to be added
   */
  @Override
  public void addNote(String text) {
    notes = notes + "- " + text;
  }

  /**
   * Gets the notes and returns it properly formatted
   *
   * @return string representation of all notes
   */
  @Override
  public String getNotes() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < notes.length(); i++) {
      if (notes.charAt(i) == '-') {
        sb.append("\n-");
      } else {
        sb.append(notes.charAt(i));
      }
    }
    return sb.toString();
  }

  /**
   * Returns whether or not a given task exists
   *
   * @param day Day of the week to check
   * @param task Name of the task to check
   * @return true if exists, false if it doesn't exist
   */
  public boolean occasionExists(String day, String occ, boolean isTask) {
    Day theDay = this.getDayFromRep(day);
    return theDay.isSameDay(day) && theDay.occasionExists(occ, isTask);
  }

  /**
   * Returns whether or not the task is completed
   *
   * @param day day of the week
   * @param task name of the task
   * @return true if the task is not complete
   */
  public boolean taskAlreadyComplete(String day, String task) {
    Day theDay = this.getDayFromRep(day);
    return theDay.isSameDay(day) && !theDay.taskComplete(task);
  }

  /**
   * Updates the completion of the task
   *
   * @param day Day the task takes place
   * @param task Name of the task
   */
  public void updateCompletion(String day, String task) {
    Day theDay = this.getDayFromRep(day);
    theDay.updateCompletion(task);
  }

  /**
   * Deletes a given occasion from the list
   *
   * @param day day the occasion takes place
   * @param name name of the occasion
   * @param isTask whether or not the occasion is a task
   */
  public void deleteOccasion(String day, String name, boolean isTask) {
    Day theDay = this.getDayFromRep(day);
      theDay.deleteOccasionFromList(name, isTask);
  }


}
