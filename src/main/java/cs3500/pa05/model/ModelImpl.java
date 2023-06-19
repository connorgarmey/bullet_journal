package cs3500.pa05.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.StatsJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ModelImpl implements Model {
  private List<Day> week;
  private Theme theme;
  private int numEvents;
  private int numTasks;
  private int completionPercent;
  private ObjectMapper mapper;
  private Path bujoFile;

  public ModelImpl() {
    this.week = new ArrayList<>();
    this.numEvents = 0;
    this.numTasks = 0;
    this.completionPercent = 0;
    this.theme = Theme.LIGHT;
    this.mapper = new ObjectMapper();
  }

  @Override
  public void makeWeek(String week) {
    try {
      WeekJson weekJson = mapper.readValue(week, WeekJson.class);
      makeDays(weekJson.days());
      this.numEvents = weekJson.stats().event();
      this.numTasks = weekJson.stats().task();
      this.completionPercent = weekJson.stats().percent();
      this.theme = weekJson.theme().theme();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

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
  public void changeTheme(Theme theme) {
    this.theme = theme;
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

  public WeekJson currentData() {
    List<DayJson> dayJsons = new ArrayList<>();
    for (Day day : week) {
      DayJson dayJson = day.makeDayJson();
      dayJsons.add(dayJson);
    }
    StatsJson statsJson = new StatsJson(this.numEvents, this.numTasks, this.completionPercent);
    ThemeJson themeJson = new ThemeJson(this.theme);
    return new WeekJson(dayJsons, themeJson, statsJson);
  }

  @Override
  public String getDaysAgenda(int day) {
    return week.get(day).getAgenda();
  }

  public void updateBujoFile(Path path) {
    this.bujoFile = path;
  }
}
