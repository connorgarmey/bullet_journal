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
      File file;
      if (!Files.exists(path)) {
        file = new File(path.toString());//Files.createFile(path).toFile();
        file.createNewFile();
      } else {
        file = path.toFile();
      }

      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(week.toString());
      fileWriter.close();

      System.out.println("Data successfully written to the file: " + path);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("An error occurred while writing the file: " + e.getMessage());
    }
  }


}
