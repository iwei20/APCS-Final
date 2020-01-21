import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class EditorMenuBar extends JMenuBar {

  public EditorMenuBar(EditableTableModel targetOfEvents) {

    // Components of menu
    JMenu m1 = new JMenu("File");

    JMenuItem mi1 = new JMenuItem("New");
    JMenuItem mi2 = new JMenuItem("Open");
    JMenuItem mi3 = new JMenuItem("Save as");
    JMenuItem mi4 = new JMenuItem("Save");

    JMenu m2 = new JMenu("Add");

    JMenuItem mi5 = new JMenuItem("Add rows");
    JMenuItem mi6 = new JMenuItem("Add columns");

    // Targets table to complete these actions
    mi1.addActionListener(targetOfEvents);
    mi2.addActionListener(targetOfEvents);
    mi3.addActionListener(targetOfEvents);
    mi4.addActionListener(targetOfEvents);
    mi5.addActionListener(targetOfEvents);
    mi6.addActionListener(targetOfEvents);

    m1.add(mi1);
    m1.add(mi2);
    m1.add(mi3);
    m1.add(mi4);
    m2.add(mi5);
    m2.add(mi6);

    add(m1);
    add(m2);
  }

}
