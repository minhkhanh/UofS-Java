package jsql.server;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class SimpleModel extends AbstractTableModel{
    @SuppressWarnings("rawtypes")
	protected Vector textData = new Vector();

    @SuppressWarnings("unchecked")
	public void addText(String text){
        textData.addElement(text);
        fireTableDataChanged();
    }

    public int getRowCount(){
        return textData.size();
    }

    public int getColumnCount(){
        return 1;
    }

    public Object getValueAt(int row, int column){
        return textData.elementAt(row);
    }
}