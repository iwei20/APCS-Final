import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;

public class CSVWriter {
  PrintWriter out;

  public CSVWriter(){
  }

  public void open(File file){
    try{
      out = new PrintWriter(file);
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public void open(String path) {
    open(new File(path));
  }

  public void close(){
    out.close();
  }

  public <E> void println(ArrayList<E> row){
    out.println(row.toString().substring(1, row.toString().length() - 1));
  }

  public void printAll(ArrayList<ArrayList<String>> data) {
    for (ArrayList<String> row : data) {
      println(row);
    }
  }

}
