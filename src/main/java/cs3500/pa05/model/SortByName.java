package cs3500.pa05.model;

import java.util.Comparator;

public class SortByName implements Comparator<Occasion> {
  @Override
  public int compare(Occasion occasion1, Occasion occasion2) {
    return occasion1.getName().compareTo(occasion2.getName());
  }
}
