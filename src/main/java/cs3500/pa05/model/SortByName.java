package cs3500.pa05.model;

import java.util.Comparator;

/**
 * Sorts by name
 */
public class SortByName implements Comparator<Occasion> {
  /**
   * Compares ignoring case
   *
   * @param occasion1 the first object to be compared.
   * @param occasion2 the second object to be compared.
   *
   * @return int, negative, positive, or zero
   */
  @Override
  public int compare(Occasion occasion1, Occasion occasion2) {
    return occasion1.getName().compareToIgnoreCase(occasion2.getName());
  }
}
