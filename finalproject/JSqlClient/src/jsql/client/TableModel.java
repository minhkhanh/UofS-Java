/**
 * 
 */
package jsql.client;

import javax.swing.table.AbstractTableModel;

import jsql.data.Table;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class TableModel extends AbstractTableModel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Table table = new Table();

    public TableModel(Table table) {
    	this.setTable(table);
	}

	public TableModel() {
	}

	public void setTable(Table table) {
		this.table = table;
		fireTableRowsInserted(0, table.getRows().size());
	}

	public void clear() {
		table.getRows().clear();
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }    
    
    @Override
    public int getRowCount() { 
        return table.getRows().size(); 
    }

    @Override
    public String getColumnName(int column) { 
        return table.getColumName(column); 
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        return ((Type)table.getRow(row).getDataAt(column)).getValue();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

//    @Override
//    public void setValueAt(Object value, int row, int column) {
//        if (column!=COLUMN_NAME) return;
//        if (value!=null) {
//            JFile jFile = (JFile)rowData.get(row);
//            if (jFile.canRename()) {
//                JFile jTemp = new JFile(jFile.getParent() + "\\" + value);
//                if(jFile.renameTo(jTemp)) {
//                    rowData.set(row, jTemp);
//                    fireTableCellUpdated(row, column);
//                }                
//            }            
//        }        
//    }
    
    @Override
    public int getColumnCount() { 
        return table.getColumns().size(); 
    }
    
}
