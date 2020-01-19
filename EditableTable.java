import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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

    return data.size();

  }

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

  @Override
  public Object getValueAt(int row, int col) {

    ArrayList<String> rowToCheck = data.get(row);

    if (col > rowToCheck.size() - 1) {
      return "";
    } else {
      return data.get(row).get(col);
    }

  }

  @Override
  public boolean isCellEditable(int row, int col) {

    return true;

  }

  @Override
  public void setValueAt(Object value, int row, int col) {

    data.get(row).set(col, value.toString());
    fireTableCellUpdated(row, col);

  }
}
