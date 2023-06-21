package cs3500.pa05.model;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * represents a single day of the week
 */
public class Day {
  private String dayOfWeek;
  private List<Occasion> tasks;
  private List<Occasion> events;

  /**
   * constructors a new day without any tasks or events
   *
   * @param day string rep of the day
   */
  public Day(String day) {
    this.dayOfWeek = day;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  /**
   * Constructor to use when reading from a file
   *
   * @param day
   * @param tasks
   * @param events
   */
  public Day(String day, List<Occasion> tasks, List<Occasion> events) {
    this.dayOfWeek = day;
    this.tasks = tasks;
    this.events = events;
  }

  private void addOccasion(Occasion occasion, List<Occasion> occasionList) {
    occasionList.add(occasion);
  }

  public void addEvent(Occasion occasion) {
    addOccasion(occasion, this.events);
  }

  public void addTask(Occasion occasion) {
    addOccasion(occasion, this.tasks);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Day that) {
      return this.dayOfWeek.equals(that.dayOfWeek);
    } else {
      return false;
    }
  }

  public boolean isSameDay(String day) {
    return this.dayOfWeek.equals(day);
  }

  /**
   * sorts the events list based on given comparator
   *
   * @param comparator how to sort the list
   */
  public void sortListEvents(Comparator<Occasion> comparator) {
    this.events.sort(comparator);
  }

  /**
   * sorts the tasks list based on given comparator
   *
   * @param comparator how to sort the list
   */
  public void sortListTasks(Comparator<Occasion> comparator) {
    this.tasks.sort(comparator);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    stringOccasions(events, sb, "Events: ");
    stringOccasions(tasks, sb, "Tasks: ");
    return sb.toString();
  }

  /**
   * appends to stringbuilder to make a string representation of the given occasions list
   *
   * @param occasions all of the occasions to write
   * @param sb stringbuilder that is being appended to
   * @param title header for that section
   */
  private void stringOccasions(List<Occasion> occasions, StringBuilder sb, String title) {
    sb.append(title + System.lineSeparator());
    for (Occasion o : occasions) {
      sb.append(o.toString());
      sb.append(System.lineSeparator());
    }
  }

  /**
   * Gets the amount of tasks
   *
   * @return the number of tasks
   */
  public int getNumTasks() {
    return tasks.size();
  }


  public int getNumCompletedTasks() {
    int completedTasks = 0;
    for (Occasion occ : tasks) {
      if (occ instanceof Task task) {
        if (task.getCompletionStatus()) {
          completedTasks++;
        }
      }
    }
    return completedTasks;
  }

  /**
   * Gets the amount of events
   *
   * @return the number of events
   */
  public int getNumEvents() {
    return events.size();
  }

  /**
   * Makes the JSON representation of a day
   *
   * @return a DayJson
   */
  public DayJson makeDayJson() {
    List<EventJson> eventsJson = new ArrayList<>();
    for (Occasion o : events) {
      if (o instanceof Event event) {
        EventJson json = event.makeJson();
        eventsJson.add(json);
      }
    }
    List<TaskJson> tasksJson = new ArrayList<>();
    for (Occasion o : tasks) {
      if (o instanceof Task task) {
        TaskJson json = task.makeJson();
        tasksJson.add(json);
      }
    }
    return new DayJson(this.dayOfWeek, eventsJson, tasksJson);
  }

  public String getAgenda() {
    StringBuilder sb = new StringBuilder();
    stringRep(sb, "Tasks: ", tasks);
    stringRep(sb, "Events: ", events);
    return sb.toString();
  }

  private void stringRep(StringBuilder sb, String header, List<Occasion> list) {
    sb.append(header);
    sb.append(System.lineSeparator());
    sb.append(System.lineSeparator());
    for (Occasion o : list) {
      sb.append(o.toString());
      sb.append(System.lineSeparator());
    }
  }

  public boolean canAdd(boolean isTask, int max) {
    if (isTask) {
      return this.tasks.size() < max;
    } else {
      return this.events.size() < max;
    }
  }

  /**
   * Updates the dya
   *
   * @param json the Day JSON
   */
  public void update(DayJson json) {
    List<EventJson> eventJsons = json.events();
    for (EventJson theEvent : eventJsons) {
      this.events.add(theEvent.makeEvent());
    }
    List<TaskJson> taskJsons = json.tasks();
    for (TaskJson theTask : taskJsons) {
      this.tasks.add(theTask.makeTask());
    }
  }

  public boolean taskExists(String task) {
    boolean exists = false;
    for (Occasion occ : tasks) {
      if (occ.sameOccasion(task)) {
        exists = true;
      }
    }
    return exists;
  }

  public boolean taskComplete(String task) {
    boolean complete = false;
    for (Occasion occ : tasks) {
      if (occ.sameOccasion(task)) {
        if (occ instanceof Task) {
          Task theTask = (Task) occ;
          complete = theTask.getCompletion();
        }
      }
    }
    return complete;
  }

  public void updateCompletion(String task) {
    for (Occasion occ : tasks) {
      if (occ.sameOccasion(task)) {
        if (occ instanceof Task) {
          Task theTask = (Task) occ;
          theTask.updateCompletion();
        }
      }
    }
  }



}
