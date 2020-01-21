import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {
  BufferedReader reader;

  public CSVReader() {
  }

  // Opens the file
  public void open(File file) {
    try{
      reader = new BufferedReader(new FileReader(file));
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  public void open(String path) {
    open(new File(path));
  }

  // Reads through the csv row by row, eliminating spaces at ends.
  public ArrayList<ArrayList<String>> readRows() {
    ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
    String line;

    try{
      while((line = reader.readLine()) != null){
        String[] lineSplit = line.split(",");

        for(int i = 0; i < lineSplit.length; i++){
          lineSplit[i] = lineSplit[i].strip();
        }
        rows.add(new ArrayList<String>(Arrays.asList(lineSplit)));
      }
    } catch(IOException e){
      e.printStackTrace();
    }

    return rows;
  }

  public void close() {
    try {
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
