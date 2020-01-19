import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {
  BufferedReader reader;

  public CSVReader(String path) {
    try{
      reader = new BufferedReader(new FileReader(path));
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  public ArrayList<ArrayList<String>> readRows() {
    ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
    String line;

    try{
      while((line = reader.readLine()) != null){
        String[] lineSplit = line.split(",");
        for(int i = 0; i < lineSplit.length; i++){
          lineSplit[i] = lineSplit[i].strip();
        }
        rows.add(new ArrayList<String>(Arrays.asList(line)));
      }
    } catch(IOException e){
      e.printStackTrace();
    }

    return rows;
  }

}
