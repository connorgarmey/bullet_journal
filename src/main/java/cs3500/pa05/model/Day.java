package cs3500.pa05.model;

import java.util.List;

public class Day {
  String dayOfWeek;
  List<Occasion> plans;

  public Day(String day) {
    this.dayOfWeek = day;
  }
}
