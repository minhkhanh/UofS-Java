/**
 * 
 */
package jsql.execute;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import jsql.data.Column;
import jsql.data.Row;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class RowInfo {
	private Hashtable<String, Integer> hashData;
	private List<String> columnName;
	private Vector<Type> columnVaule;
	
	public RowInfo() {
		hashData = new Hashtable<String, Integer>();
		columnName = new ArrayList<String>();
		columnVaule = new Vector<Type>();
	}
	public RowInfo(List<Column> columns) {
		this();
		for (Column column : columns) {
			setAtt(column.getName(), null);
		}
	}
	public void setAtt(String name, Type value) {
		if (name != null) {
			boolean exist = hashData.containsKey(name);

			if (exist) {
				int i = ((Integer) hashData.get(name)).intValue();
				columnVaule.set(i, value);
			} else {
				int i = columnName.size();
				columnName.add(name);
				columnVaule.add(value);
				hashData.put(name, new Integer(i));
			}
		}
	}
	public void setRow(Row row) {		
		Vector<Type> list = row.getData();
		for (int i = 0; i < list.size(); i++) {
			setAtt(getAttName(i), list.get(i));
		}
	}
	public String getAttName(int index) {
		try {
			return (String) columnName.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	public Object getAttValue(String name) {
		boolean exist = false;

		if (name != null)
			exist = hashData.containsKey(name);

		if (exist) {
			int index = ((Integer) hashData.get(name)).intValue();
			return columnVaule.get(index);
		} else
			return null;
	}
}


