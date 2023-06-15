package cs3500.pa05.model;

import java.util.List;

public class Day {
  String dayOfWeek;
  List<Occasion> plans;

  public Day(String day) {
    this.dayOfWeek = day;
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
}
