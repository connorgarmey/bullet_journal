package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.CustomTheme;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTest {
  static Model model;
  static Model emptyModel;
  String jsonString;

  @BeforeEach
  public void setup() {
    // Initialize the model
    model = new ModelImpl();
    emptyModel = new ModelImpl();

    // Add occasions to day
    model.addEvent("Haircut", "Pre-scheduled haircut", "Monday", 10, 30, 30);
    model.addEvent("Meeting", "Group meeting", "Monday", 11, 30, 90);
    model.addEvent("Wake up", "Pre-scheduled haircut", "Monday", 6, 30, 5);
    model.addEvent("Lunch", "Pre-scheduled haircut", "Monday", 12, 30, 60);
    model.addEvent("Dinner", "Pre-scheduled haircut", "Monday", 20, 0, 90);
    model.addTask("PA01", "Need to finish testing PA01", "Monday");

    // Create the weekJson written in string format
    jsonString = "{\n" +
        "  \"days\": [\n" +
        "    {\n" +
        "      \"day\": \"Monday\",\n" +
        "      \"events\": [\n" +
        "        {\n" +
        "      \"name\": \"Haircut\",\n" +
        "      \"description\": \"Pre-scheduled haircut\",\n" +
        "      \"day-of-week\": \"Monday\",\n" +
        "      \"start-hour\": 10,\n" +
        "      \"start-minute\": 30,\n" +
        "      \"duration\": 30\n" +
        "    },\n" +
        "        {\n" +
        "      \"name\": \"Meeting\",\n" +
        "      \"description\": \"Group meeting\",\n" +
        "      \"day-of-week\": \"Monday\",\n" +
        "      \"start-hour\": 11,\n" +
        "      \"start-minute\": 30,\n" +
        "      \"duration\": 90\n" +
        "    },\n" +
        "        {\n" +
        "      \"name\": \"Wake up\",\n" +
        "      \"description\": \"Pre-scheduled haircut\",\n" +
        "      \"day-of-week\": \"Monday\",\n" +
        "      \"start-hour\": 6,\n" +
        "      \"start-minute\": 30,\n" +
        "      \"duration\": 5\n" +
        "    },\n" +
        "        {\n" +
        "      \"name\": \"Lunch\",\n" +
        "      \"description\": \"Pre-scheduled haircut\",\n" +
        "      \"day-of-week\": \"Monday\",\n" +
        "      \"start-hour\": 12,\n" +
        "      \"start-minute\": 30,\n" +
        "      \"duration\": 60\n" +
        "    },\n" +
        "        {\n" +
        "      \"name\": \"Dinner\",\n" +
        "      \"description\": \"Pre-scheduled haircut\",\n" +
        "      \"day-of-week\": \"Monday\",\n" +
        "      \"start-hour\": 20,\n" +
        "      \"start-minute\": 0,\n" +
        "      \"duration\": 90\n" +
        "    }\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "        {\n" +
        "      \"name\": \"PA01\",\n" +
        "      \"description\": \"Need to finish testing PA01\",\n" +
        "      \"day-of-week\": \"Monday\",\n" +
        "      \"completed\": false\n" +
        "    }\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": \"Tuesday\",\n" +
        "      \"events\": [\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": \"Wednesday\",\n" +
        "      \"events\": [\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": \"Thursday\",\n" +
        "      \"events\": [\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": \"Friday\",\n" +
        "      \"events\": [\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": \"Saturday\",\n" +
        "      \"events\": [\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": \"Sunday\",\n" +
        "      \"events\": [\n" +
        "      ],\n" +
        "      \"tasks\": [\n" +
        "      ]\n" +
        "    }\n" +
        "  ],\n" +
        "  \"theme\": {\n" +
        "    \"font\": \"Georgia\",\n" +
        "    \"fontColor\": \"Pink\",\n" +
        "    \"background\": \"Light Pink\",\n" +
        "    \"icon\": \"Light\",\n" +
        "    \"name\": \"LIGHT\"\n" +
        "  },\n" +
        "  \"stats\": {\n" +
        "    \"eventsCount\": 0,\n" +
        "    \"taskCount\": 0,\n" +
        "    \"maxEvents\": 5,\n" +
        "    \"maxTasks\": 5,\n" +
        "    \"notes\": \"\",\n" +
        "    \"percent\": 0.0\n" +
        "  }\n" +
        "}";
  }

  @Test
  public void testMakeWeek() {
    // Check agenda before
    String initOutput = emptyModel.getCurrentStats();
    String expectedInitStats = "Events: 0,   Tasks: 0   100.0% completed";

    assertEquals(expectedInitStats, initOutput);

    // Run the method to be tested
    emptyModel.makeWeek(jsonString);

    // Check agenda after
    String updatedOutput = emptyModel.getCurrentStats();
    String expectedOutput = "Events: 5,   Tasks: 1   0.0% completed";

    // Assert the new output equals the new expected output
    assertEquals(expectedOutput, updatedOutput);
  }

  @Test
  public void testCanAdd() {
    // Check if you can add a task on Monday
    boolean output = model.canAdd(true, "Monday");
    assertEquals(true, output);

    // Check if you can add an event on Monday
    boolean output2 = model.canAdd(false, "Monday");
    assertEquals(false, output2);
  }

  @Test
  public void testNewWeek() {
    // Use method to create actual outputted WeekJson
    WeekJson week = model.newWeek();

    // Check the initialized value of theme is set to LIGHT
    CustomTheme expectedTheme = new CustomTheme("Georgia", "Pink", "Light Pink", "Light", "LIGHT");
    ThemeJson themeJson = expectedTheme.createJson();

    // Assert the expected default
    assertEquals(themeJson, week.theme());
  }

  @Test
  public void testSaveData() throws IOException {
    // Set the file path for saving the data
    Path testFilePath = Paths.get("test.bujo");

    // Update the file and save the data
    model.updateBujoFile(testFilePath);
    model.saveData();

    // Check that the file exists
    assertTrue(Files.exists(testFilePath));

    // Clean up by deleting the test file
    Files.deleteIfExists(testFilePath);
  }

  @Test
  public void testUpdateMax() {
    // Check that you cannot add before updating
    boolean eventFalse = model.canAdd(false, "Monday");
    assertFalse(eventFalse);

    // Use the method to update the max
    model.updateMax(6, "events");

    // Check that you can now add after increasing the max Events
    boolean eventTrue = model.canAdd(false, "Monday");
    assertTrue(eventTrue);

    // Check that you can add a task with the current max
    boolean taskTrue = model.canAdd(true, "Monday");
    assertTrue(taskTrue);

    // Use the method to update the max
    model.updateMax(1, "tasks");

    // Check that you can no longer add after setting the max to 1
    boolean taskFalse = model.canAdd(true, "Monday");
    assertFalse(taskFalse);
  }

  @Test
  public void testGetCurrentStats() {
    // Find the expected stats output
    String currentStats = model.getCurrentStats();

    // Store the expected value
    String expectedStats = "Events: 5,   Tasks: 1   0.0% completed";

    // Assert they are the same
    assertEquals(expectedStats, currentStats);

    // Get the stats for an week with no tasks or events
    String currentStats1 = emptyModel.getCurrentStats();

    // Store the expected
    String expectedStats1 = "Events: 0,   Tasks: 0   100.0% completed";

    // Assert they are equal
    assertEquals(expectedStats1, currentStats1);
  }

  @Test
  public void testSortEvents() {
    // Sort Events by duration
    model.sortEvents(1);

    // Get the expected and actual outputs of the agenda
    String sortedString = model.getDaysAgenda(0);
    String expectedSort = "Tasks: \n" +
        "\n" +
        "What: PA01\n" +
        "Description: Need to finish testing PA01\n" +
        "Completed: No\n" +
        "\n" +
        "Events: \n" +
        "\n" +
        "What: Wake up\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 6:30\n" +
        "Duration: 5 minutes\n" +
        "\n" +
        "What: Haircut\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 10:30\n" +
        "Duration: 30 minutes\n" +
        "\n" +
        "What: Lunch\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 12:30\n" +
        "Duration: 1 hour\n" +
        "\n" +
        "What: Meeting\n" +
        "Description: Group meeting\n" +
        "Start Time: 11:30\n" +
        "Duration: 1 hour 30 minutes\n" +
        "\n" +
        "What: Dinner\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 20:00\n" +
        "Duration: 1 hour 30 minutes\n" +
        "\n";

    // Assert it changed how we expected
    assertEquals(expectedSort, sortedString);

    // Test sorting Events by name
    model.sortEvents(2);
    String expectedSort2 = "Tasks: \n" +
        "\n" +
        "What: PA01\n" +
        "Description: Need to finish testing PA01\n" +
        "Completed: No\n" +
        "\n" +
        "Events: \n" +
        "\n" +
        "What: Dinner\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 20:00\n" +
        "Duration: 1 hour 30 minutes\n" +
        "\n" +
        "What: Haircut\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 10:30\n" +
        "Duration: 30 minutes\n" +
        "\n" +
        "What: Lunch\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 12:30\n" +
        "Duration: 1 hour\n" +
        "\n" +
        "What: Meeting\n" +
        "Description: Group meeting\n" +
        "Start Time: 11:30\n" +
        "Duration: 1 hour 30 minutes\n" +
        "\n" +
        "What: Wake up\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 6:30\n" +
        "Duration: 5 minutes\n" +
        "\n";
    String sortedString2 = model.getDaysAgenda(0);

    // Assert they are equal
    assertEquals(expectedSort2, sortedString2);

    // Test sorting Tasks by name
    model.addTask("Clean my room", "Fold laundry, Make bed, etc.", "Monday");
    model.sortEvents(3);
    String expectedSort3 = "Tasks: \n" +
        "\n" +
        "What: Clean my room\n" +
        "Description: Fold laundry, Make bed, etc.\n" +
        "Completed: No\n" +
        "\n" +
        "What: PA01\n" +
        "Description: Need to finish testing PA01\n" +
        "Completed: No\n" +
        "\n" +
        "Events: \n" +
        "\n" +
        "What: Dinner\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 20:00\n" +
        "Duration: 1 hour 30 minutes\n" +
        "\n" +
        "What: Haircut\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 10:30\n" +
        "Duration: 30 minutes\n" +
        "\n" +
        "What: Lunch\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 12:30\n" +
        "Duration: 1 hour\n" +
        "\n" +
        "What: Meeting\n" +
        "Description: Group meeting\n" +
        "Start Time: 11:30\n" +
        "Duration: 1 hour 30 minutes\n" +
        "\n" +
        "What: Wake up\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 6:30\n" +
        "Duration: 5 minutes\n" +
        "\n";
    String sortedString3 = model.getDaysAgenda(0);
    assertEquals(expectedSort3, sortedString3);
  }

  @Test
  public void testGetTitle() throws IOException {
    // Set the file path for saving the data
    Path testFilePath = Paths.get("test.bujo");

    // Update the file and save the data
    model.updateBujoFile(testFilePath);

    // Get the name and expected name
    String name = model.getTitle();
    String expectedName = "test";

    // Assert they are equal
    assertEquals(expectedName, name);

    // Clean up by deleting the test file
    Files.deleteIfExists(testFilePath);
  }

  @Test
  public void testGetTheme() {
    // Get the expected theme and actual theme
    CustomTheme currentTheme = model.getTheme();
    CustomTheme expectedTheme = new CustomTheme("Georgia", "Pink", "Light Pink", "Light", "LIGHT");

    // Assert they are equal
    assertEquals(expectedTheme.getBackgroundColor(), currentTheme.getBackgroundColor());
  }
}
