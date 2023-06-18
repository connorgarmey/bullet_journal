package cs3500.pa05.model;

import cs3500.pa05.model.json.DayJson;
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
    if (o instanceof Day) {
      Day that = (Day) o;
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

  /**
   * Gets the amount of events
   *
   * @return the number of events
   */
  public int getNumEvents() {
    return events.size();
  }

  public DayJson makeDayJson() {
    //DayJson dayJson = new DayJson(this.dayOfWeek,this.events)
    return null;
  }

  public String getAgenda() {
    return tasks.toString() + events.toString();
  }


}
