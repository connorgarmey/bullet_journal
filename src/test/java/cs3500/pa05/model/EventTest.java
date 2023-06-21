package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
  public Occasion event1;
  public Occasion event2;
  public Occasion event3;
  public Occasion event4;
  public Occasion event5;
  public EventJson eventJson1;

  @BeforeEach
  public void setup() {
    event1 = new Event("Haircut", "Pre-scheduled haircut", "Monday", 10, 30, 30);
    event2 = new Event("Meditation", "Hour long meditation session", "Tuesday", 8, 0, 60);
    event3 = new Event("Basketball game", "Club basketball game", "Wednesday", 12, 15, 120);
    event4 = new Event("OOD Exam", "Exam 2 for OOD Summer 1", "Thursday", 11, 40, 100);
    event5 = new Event("Godfather marathon", "Watch all Godfather movies", "Saturday", 10, 0, 599);
    eventJson1 = new EventJson("OOD Exam", "Exam 2 for OOD Summer 1", "Thursday", 11, 40, 100);
  }


  @Test
  public void testJsonToEvent() {
    // Create the expected event utilizing eventJson1 and the Event constructor
    Event jsonEvent = new Event(eventJson1);

    // Check that each of the fields is created as expected
    String outputString = jsonEvent.toString();
    String expected = "What: "
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
  }

  @Test
  // Tests toString() and private method makeTime()
  public void testToString() {
    // Duration < 60
    String expected1 = "What: "
        + "Haircut"
        + System.lineSeparator()
        + "Description: "
        + "Pre-scheduled haircut"
        + System.lineSeparator()
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
        + "Description: "
        + "Hour long meditation session"
        + System.lineSeparator()
        + "Start Time: "
        + 8
        + ":"
        + "00"
        + System.lineSeparator()
        + "Duration: "
        + "1 hour"
        + System.lineSeparator();
    assertEquals(expected2, event2.toString());

    // Duration % 60 == 0
    String expected3 = "What: "
        + "Basketball game"
        + System.lineSeparator()
        + "Description: "
        + "Club basketball game"
        + System.lineSeparator()
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
        + "Description: "
        + "Exam 2 for OOD Summer 1"
        + System.lineSeparator()
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
        + "Description: "
        + "Watch all Godfather movies"
        + System.lineSeparator()
        + "Start Time: "
        + 10
        + ":"
        + "00"
        + System.lineSeparator()
        + "Duration: "
        + "" + 9 + " hours " + 59 + " minutes"
        + System.lineSeparator();
    assertEquals(expected5, event5.toString());
  }

  @Test
  public void testMakeJson() {
    EventJson expected = null;
    if (event4 instanceof Event e) {
      expected = e.makeJson();
    }
    assertEquals(expected, eventJson1);
  }

  @Test
  public void testGetDuration() {
    int duration = 0;
    if (event1 instanceof Event e) {
      duration = e.getDuration();
    }
    assertEquals(30, duration);
  }

  @Test
  public void testGetName() {
    String name = event1.getName();
    assertEquals("Haircut", name);
  }
}