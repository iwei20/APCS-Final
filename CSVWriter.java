import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class CSVWriter {
  PrintWriter out;
  String path;

  public CSVWriter(String path){
      this.path = path;
      open();
  }

  public void open(){
    try{
      out = new PrintWriter(path);
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public void save(){
    out.close();
    open();
  }

  public <E> void println(E[] row){
    out.println(Arrays.toString(row).substring(1, row.length - 1));
  }

}
