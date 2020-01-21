import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class EditableTableModel extends AbstractTableModel implements ActionListener {

  /**
   * This table model is meant to allow for the easy
   * insertion of data with the swing table.
  **/

  ArrayList<ArrayList<String>> data;

  boolean saved;

  int rowCount;
  int columnCount;

  CSVReader creader;
  CSVWriter out;

  File currFile;

  JFileChooser chooser;

  public EditableTableModel(){
    rowCount = 100;
    columnCount = 20;

    out = new CSVWriter();
    creader = new CSVReader();

    data = new ArrayList<ArrayList<String>>();
    chooser = new JFileChooser();

    // Set file filter
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.addChoosableFileFilter(new FileFilter() {

      public String getDescription() {
        return "CSV files (*.csv)";
      }

      public boolean accept(File f) {
        if (f.isDirectory()) {
          return true;
        } else {
          return f.getName().toLowerCase().endsWith(".csv");
        }
      }

    });

    saved = true;

  }

  @Override
  public int getRowCount() {

    return rowCount;

  }

  @Override
  public int getColumnCount() {

    return columnCount;

  }

  @Override
  public Object getValueAt(int row, int col) {
    if (col == 0) {
      return Integer.toString(row + 1);
    }

    if (row < data.size()) {

      ArrayList<String> rowToCheck = data.get(row);

      if (col <= rowToCheck.size()) {
        return rowToCheck.get(col - 1);
      }

    }

    return "";

  }

  @Override
  public String getColumnName(int column) {

    if (column == 0) {
      return "";
    }
    return super.getColumnName(column - 1);

  }

  @Override
  public boolean isCellEditable(int row, int col) {

    return col > 0;

  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    if (value.toString() != " " && value.toString() != "" && value.toString() != null){

      // Asterick added
      if (saved && !Main.frame.getTitle().equals("CSV Editor")) {
        saved = false;
        Main.frame.setTitle(Main.frame.getTitle() + "*");
      }

      while (data.size() <= row) {
        data.add(new ArrayList<String>());
      }

      while (data.get(row).size() < col) {
        data.get(row).add("");
      }

      data.get(row).set(col - 1, value.toString());
      fireTableCellUpdated(row, col);
    }
  }

  /*
  * Below are all the methods to edit the table
  * from the menu bar.
  **/
  @Override
  public void actionPerformed(ActionEvent e) {
    String event = e.getActionCommand();

    if (event.equals("New")) {
      currFile = null;
      data = new ArrayList<ArrayList<String>>();
      fireTableDataChanged();
      Main.frame.setTitle("CSV Editor");
    } else if (event.equals("Open")) {
      open();
    } else if (event.equals("Save as")) {
      saveAs();
    } else if (event.equals("Save")) {
      save();
    } else if (event.equals("Add rows")) {
      addRows();
    } else if (event.equals("Add columns")) {
      addColumns();
    }
  }

  /*
  * These methods open up a dialog box to choose or save to a new file.
  **/
  public void open() {

    int returnVal = chooser.showOpenDialog(Main.frame);

    if (returnVal == JFileChooser.APPROVE_OPTION) {

        File file = chooser.getSelectedFile();

        if (file.exists()) {

          currFile = file;
          creader.open(file);
          data = creader.readRows();
          Main.frame.setTitle(file.toString());
          creader.close();

        } else {

          JOptionPane.showMessageDialog(Main.frame, "File not found");

        }

    }

    saved = true;

    fireTableDataChanged();
  }

  public void saveAs() {

    int returnVal = chooser.showSaveDialog(Main.frame);

    if (returnVal == JFileChooser.APPROVE_OPTION) {

        File file = chooser.getSelectedFile();

        // Doesn't matter if file is already created, still overwritten.
        try {
          file.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }

        currFile = file;

        // Rewrite file
        out.open(file);
        out.printAll(data);
        out.close();

        Main.frame.setTitle(file.toString());

        // Take away the little asterick
        if (!saved && !Main.frame.getTitle().equals("CSV Editor")) {
          saved = true;
          Main.frame.setTitle(Main.frame.getTitle().substring(0, Main.frame.getTitle().length() - 1));
        }
    }

  }

  public void save() {
    if(currFile == null) {
      saveAs();
      return;
    }

    // Rewrite file
    out.open(currFile);
    out.printAll(data);
    out.close();

    // Take away the asterick
    if (!saved && !Main.frame.getTitle().equals("CSV Editor")) {
      saved = true;
      Main.frame.setTitle(Main.frame.getTitle().substring(0, Main.frame.getTitle().length() - 1));
    }

  }

  // Opens up dialog box asking for number of rows/columns to add
  public void addRows() {
    SpinnerNumberModel numChoice = new SpinnerNumberModel(1, 1, 1000, 1);
    JSpinner spinner = new JSpinner(numChoice);
    int option = JOptionPane.showOptionDialog(Main.frame, spinner, "How many rows to add?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

    if (option == JOptionPane.OK_OPTION) {
        rowCount += numChoice.getNumber().intValue();
    }
    fireTableStructureChanged();
  }

  public void addColumns() {
    SpinnerNumberModel numChoice = new SpinnerNumberModel(1, 1, 1000, 1);
    JSpinner spinner = new JSpinner(numChoice);
    int option = JOptionPane.showOptionDialog(Main.frame, spinner, "How many rows to add?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

    if (option == JOptionPane.OK_OPTION) {
        columnCount += numChoice.getNumber().intValue();
    }
    fireTableStructureChanged();
  }

}
