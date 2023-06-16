package cs3500.pa05.model;

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

  public Day(String day, List<Occasion> tasks, List<Occasion> events) {
    this.dayOfWeek = day;
    this.tasks = tasks;
    this.events = events;
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
}
