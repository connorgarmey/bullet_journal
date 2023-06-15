package cs3500.pa05.model;

import java.util.Comparator;

public class SortByDuration implements Comparator<Event> {
  @Override
  public int compare(Event event1, Event event2) {
    return event1.getDuration() - event2.getDuration();
  }
}
