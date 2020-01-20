import javax.swing.*;

public class Main {
  public static void main(String[] args){
    CSVReader read = new CSVReader("bruh.csv");
    // Reader should only be called on the opening of a new file.
    JFrame frame = new JFrame("CSV Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTable table = new JTable(new EditableTable(read.readRows()));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    JScrollPane tablePane = new JScrollPane(table);

    frame.getContentPane().add(tablePane);
    frame.pack();
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    frame.setVisible(true);
  }
}
