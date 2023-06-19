package cs3500.pa05.model;

import java.util.Comparator;

/**
 * Class for sorting by duration
 */
public class SortByDuration implements Comparator<Occasion> {
  /**
   * Overriding compare for two events
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   *
   * @return an int - positive, negative, or zero
   */
  @Override
  public int compare(Occasion o1, Occasion o2) {
    if (o1 instanceof Event event1 && o2 instanceof Event event2) {
      return event1.getDuration() - event2.getDuration();
    } else {
      throw new IllegalArgumentException("Cannot compare Tasks by duration");
    }
  }
}
