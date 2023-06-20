package cs3500.pa05.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.controller.WriteFile;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.StatsJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ModelImpl implements Model {
  private List<Day> week;
  private Theme theme;
  private int numEvents;
  private int numTasks;
  private double completionPercent;
  private ObjectMapper mapper;
  private Path bujoFile;
  private int maxTasks;
  private int maxEvents;
  private String notes;

  public ModelImpl() {
    this.week = makeEmptyDays();
    this.numEvents = 0;
    this.numTasks = 0;
    this.completionPercent = 0.00;
    this.theme = Theme.LIGHT;
    this.mapper = new ObjectMapper();
    this.notes = "";
    this.maxTasks = 5;
    this.maxEvents = 5;
  }

  @Override
  public void makeWeek(String week) {
    try {
      WeekJson weekJson = mapper.readValue(week, WeekJson.class);
      updateDays(weekJson.days());
      this.numEvents = weekJson.stats().event();
      this.numTasks = weekJson.stats().task();
      this.completionPercent = weekJson.stats().percent();
      this.theme = weekJson.theme().theme();
      this.notes = weekJson.stats().notes();
      this.maxTasks = weekJson.stats().maxTasks();
      this.maxEvents = weekJson.stats().maxEvents();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }
  
  

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

  private void updateDays(List<DayJson> dayJsons) {
    for (DayJson dayJson : dayJsons) {
      for (Day day : week) {
        if (day.isSameDay(dayJson.day())) {
          day.update(dayJson);
        }
      }
    }
  }

  private List<Day> makeEmptyDays() {
    List<String> stringRep = makeDaysOfWeek(new ArrayList<>());
    List<Day> days = new ArrayList<>();
    for (String s : stringRep) {
      Day day = new Day(s);
      days.add(day);
    }
    return days;
  }



  @Override
  public void addTask(String n, String des, String d) {
    Occasion taskToAdd = new Task(n,des,d);
    for (Day day : this.week) {
      if (day.isSameDay(d)) {
        day.addTask(taskToAdd);
      }
    }
  }

  @Override
  public void addEvent(String n, String des, String d, int hour, int minute, int dur) {
    Occasion eventToAdd = new Event(n, des, d, hour, minute, dur);
    for (Day day : this.week) {
      if (day.isSameDay(d)) {
        day.addEvent(eventToAdd);
      }
    }
  }


  @Override
  public void changeTheme(Theme newTheme) {
    this.theme = newTheme;
  }


  @Override
  public WeekJson newWeek() {
    List<String> dayString = makeDaysOfWeek(new ArrayList<>());
    List<DayJson> days = new ArrayList<>();
    for (String s : dayString) {
      DayJson day = new DayJson(s,new ArrayList<>(),new ArrayList<>());
      days.add(day);
    }
    ThemeJson theme = new ThemeJson(Theme.LIGHT);
    StatsJson stats = new StatsJson(0, 0, 5, 5, "", 100);
    return new WeekJson(days, theme, stats);
  }

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

  private WeekJson currentData() {
    List<DayJson> dayJsons = new ArrayList<>();
    System.out.println(week);
    for (Day day : week) {
      DayJson dayJson = day.makeDayJson();
      dayJsons.add(dayJson);
    }
    StatsJson statsJson = new StatsJson(this.numEvents, this.numTasks, this.maxEvents,
        this.maxTasks, this.notes, this.completionPercent);
    ThemeJson themeJson = new ThemeJson(this.theme);
    System.out.println(this.theme);
    return new WeekJson(dayJsons, themeJson, statsJson);
  }

  public void updateBujoFile(Path path) {
    this.bujoFile = path;
  }

  public void saveData() {
    WriteFile writer = new WriteFile();
    writer.writeToFile(this.bujoFile, currentData());
  }

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

}
