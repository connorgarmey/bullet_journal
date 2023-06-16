package cs3500.pa05.controller;



import cs3500.pa05.model.json.WeekJson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class WriteFile {


  public void writeToFile(File file, WeekJson week) {
    try {


      BufferedWriter writer = new BufferedWriter(new FileWriter(file));

      writer.write(week.toString());
      writer.close();
      System.out.println("Data successfully written to the file: " + file.getAbsolutePath());
    } catch (IOException e) {
      System.out.println("An error occurred while writing the file: " + e.getMessage());
    }
  }


}
