/*
 * MyTableModel.java
 *
 * Created on 17. June 2007, 11:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package samples.table;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pavel Ponec
 */
public class MyTableModel extends AbstractTableModel {
    
    /** Creates a new instance of MyTableModel */
    public MyTableModel() {
    }
    
    public int getRowCount() {
        return 0;
    }
    
    public int getColumnCount() {
        return 0;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }
    
    @Override
    public String getColumnName(int column) {
        return null;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
