import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class EditorFrame extends JFrame {

  // All the parts of the CSV editor
  public JTable table;
  public JScrollPane tablePane;
  public EditableTableModel model;

  public EditorFrame() {
    super("CSV Editor");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Table and editor itself
    model = new EditableTableModel();
    table = new JTable(model);

    // To prevent cells from being too small
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tablePane = new JScrollPane(table);

    setJMenuBar(new EditorMenuBar(model));

    getContentPane().add(tablePane);

    // Set popped out and maximized sizes
    pack();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    revalidate();
    repaint();

    setVisible(true);
  }

}
