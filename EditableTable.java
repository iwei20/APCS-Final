import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class EditableTable extends AbstractTableModel {

  /**
   * This table model is meant to allow for the easy
   * insertion of data with the swing table.
  **/

  ArrayList<ArrayList<String>> data;

  public EditableTable(ArrayList<ArrayList<String>> data){

    this.data = data;

  }

  @Override
  public int getRowCount() {

    return 100;

  }

  @Override
  public int getColumnCount() {

    return 20;

  }

  /*
  @Override
  public int getColumnCount() {

    int size = 1;

    for (ArrayList<String> row : data) {
      if (row.size() > size) {
        size = row.size();
      }
    }

    return size;
  }
  */

  @Override
  public Object getValueAt(int row, int col) {
    if (row < data.size()) {

      ArrayList<String> rowToCheck = data.get(row);

      if (col > rowToCheck.size()) {
        return "";
      } else if (col == 0) {
        return Integer.toString(row + 1);
      } else {
        return rowToCheck.get(col - 1);
      }

    } else {
      if (col == 0) {
        return Integer.toString(row + 1);
      } else {
        return "";
      }
    }

  }

  @Override
  public String getColumnName(int column) {

    if (column == 0) {
      return "";
    } else {
      return super.getColumnName(column - 1);
    }

  }

  @Override
  public boolean isCellEditable(int row, int col) {

    if (col > 0) {
      return true;
    }
    return false;

  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    while (data.size() < row) {
      data.add(new ArrayList<String>());
    }

    while (data.get(row - 1).size() < col) {
      data.get(row).add("");
    }

    data.get(row - 1).set(col - 1, value.toString());
    fireTableCellUpdated(row, col);
  }
}
