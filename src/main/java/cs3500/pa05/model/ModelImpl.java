package cs3500.pa05.model;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.StatsJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  List<Day> week;
  Theme theme;
  int numEvents;
  int numTasks;

  public ModelImpl() {
    this.numEvents = 0;
    this.numTasks = 0;
    this.theme = Theme.LIGHT;
//    updateNumbers();
  }

  public ModelImpl(WeekJson weekJson) {
    this.week = new ArrayList<>();
    makeDays(weekJson.days());
    this.theme = weekJson.theme().theme();
    this.numEvents = weekJson.stats().event();
    this.numTasks = weekJson.stats().task();
    
  }

  private void makeDays(List<DayJson> dayJsons) {
    for (DayJson dayJson : dayJsons) {
      Day day = new Day( dayJson.day(), makeTasks(dayJson.tasks()), makeEvents(dayJson.events()));
      this.week.add(day);
    }
  }

  private List<Occasion> makeTasks(List<TaskJson> taskJsons) {
    List<Occasion> tasks = new ArrayList<>();
    for (TaskJson taskJson : taskJsons) {
      Task task = new Task(taskJson);
      tasks.add(task);
    }
    return tasks;
  }

  private List<Occasion> makeEvents(List<EventJson> taskJsons) {
    List<Occasion> events = new ArrayList<>();
    for (EventJson eventJson : taskJsons) {
      Event event = new Event(eventJson);
      events.add(event);
    }
    return events;
  }



  @Override
  public void addTask(Task task) {
    this.
  }

  @Override
  public void addEvent(Event event) {

  }

  @Override
  public void changeTheme(Theme theme) {

  }

  @Override
  public void updateNumbers() {
    numTasks = 0;
    numEvents = 0;
    for (Day day : week) {
      numTasks += day.getNumTasks();
      numEvents += day.getNumEvents();
    }
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
    StatsJson stats = new StatsJson(0, 0, 100);
    WeekJson week = new WeekJson(days, theme, stats);
    return week;
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
}
