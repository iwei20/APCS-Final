import javax.swing.*;

public class Main {
  public static void main(String[] args){
    CSVReader read = new CSVReader("bruh.csv");
    // Reader should only be called on the opening of a new file.
    JFrame frame = new JFrame("CSV Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 900);
    JTable table = new JTable(new EditableTable(read.readRows()));
    frame.getContentPane().add(table);
    frame.setVisible(true);

  }
}
