package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.EventJson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EventTest {
  static Event event1;
  static Event event2;
  static Event event3;
  static Event event4;
  static Event event5;
  static EventJson eventJson1;


  @BeforeAll
  public void setup() {
    event1 = new Event("Haircut", "Pre-scheduled haircut", "Monday", 10, 30, 30);
    event2 = new Event("Meditation", "Hour long meditation session", "Tuesday", 8, 0, 60);
    event3 = new Event("Basketball game", "Club basketball game", "Wednesday", 12, 15, 120);
    event4 = new Event("OOD Exam", "Exam 2 for OOD Summer 1", "Thursday", 11, 40, 100);
    event5 = new Event("Godfather marathon", "Watch all Godfather movies", "Saturday", 10, 0, 599);

    eventJson1 = new EventJson("OOD Exam", "Exam 2 for OOD Summer 1", "Thursday", 11, 40, 100);

  }

  @Test
  private void testJsonToEvent() {
    // Create the expected event utilizing eventJson1 and the Event constructor
    Event expected = new Event(eventJson1);

    // Check that each of the fields is created as expected
    assertEquals("OOD Exam", expected.getName());
    assertEquals("Exam 2 for OOD Summer 1", expected.getDescription());
    assertEquals("Thursday", expected.getDay());
    assertEquals(11, expected.getHours());
    assertEquals(40, expected.getMinutes());
    assertEquals(100, expected.getDuration());
  }

  @Test
  // Tests toString() and private method makeTime()
  private void testToString() {
    // Duration < 60
    String expected1 = "What: "
        + "Haircut"
        + System.lineSeparator()
        + "Pre-scheduled haircut"
        + "Start Time: "
        + 10
        + ":"
        + 30
        + System.lineSeparator()
        + "Duration: "
        + "" + 30 + " minutes"
        + System.lineSeparator();
    assertEquals(expected1, event1.toString());

    // Duration = 60
    String expected2 = "What: "
        + "Meditation"
        + System.lineSeparator()
        + "Hour long meditation session"
        + "Start Time: "
        + 8
        + ":"
        + 0
        + System.lineSeparator()
        + "Duration: "
        + "1 hour"
        + System.lineSeparator();
    assertEquals(expected2, event2.toString());

    // Duration % 60 == 0
    String expected3 = "What: "
        + "Basketball game"
        + System.lineSeparator()
        + "Club basketball game"
        + "Start Time: "
        + 12
        + ":"
        + 15
        + System.lineSeparator()
        + "Duration: "
        + "" + 2 + " hours"
        + System.lineSeparator();
    assertEquals(expected3, event3.toString());

    // Duration > 60 && duration < 120
    String expected4 = "What: "
        + "OOD Exam"
        + System.lineSeparator()
        + "Exam 2 for OOD Summer 1"
        + "Start Time: "
        + 11
        + ":"
        + 40
        + System.lineSeparator()
        + "Duration: "
        + "1 hour " + 40 + " minutes"
        + System.lineSeparator();
    assertEquals(expected4, event4.toString());

    // Anything greater than 120 where duration % 60 != 0
    String expected5 = "What: "
        + "Godfather marathon"
        + System.lineSeparator()
        + "Watch all Godfather movies"
        + "Start Time: "
        + 10
        + ":"
        + 0
        + System.lineSeparator()
        + "Duration: "
        + "" + 9 + " hours " + 59 + " minutes"
        + System.lineSeparator();
    assertEquals(expected5, event5.toString());
  }

  @Test
  private void testMakeJson() {
    EventJson expected = event4.makeJson();
    assertEquals(expected, eventJson1);
  }
}