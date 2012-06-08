/**
 * 
 */
package jsql.data;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author tmkhanh
 * 
 */
public class Row implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Type> data;

	public Row() {
		data = new Vector<Type>();
	}
	
	public Row(Row row) throws Exception {
		data = new Vector<Type>();
		for (Type type : row.data) {
			data.add(type);
		}
	}

	public Row(int numCol) {
		for (int i = 0; i < numCol; i++) {
			data.add(null);
		}
	}

	public Row(Vector<Type> data) {
		this.data = data;
	}

	public Type getDataAt(int index) {
		return data.get(index);
	}

	public void setDataAt(int index, Type obj) {
		data.set(index, obj);
	}

	public void addData(Type obj) {
		data.add(obj);
	}

	public Vector<Type> getData() {
		return data;
	}

	public int numCol() {
		return data.size();
	}
	
	private void addRow(Row row) {
		for (Type type : row.getData()) {
			addData(type);
		}
	}
	
	public static Row createNewRow(Row rowA, Row rowB) {
		Row t = new Row();
		t.addRow(rowA);
		t.addRow(rowB);
		return t;
	}
	
	public int getNumCol() {
		return data.size();
	}

	public static Row createNewRowRightNull(Row rowA, int numCol) {
		Row t = new Row();
		t.addRow(rowA);
		for (int i = 0; i < numCol; i++) {
			t.addData(new NullType());
		}
		return t;
	}

	public static Row createNewRowLeftNull(Row row, int numCol) {
		Row t = new Row();		
		for (int i = 0; i < numCol; i++) {
			t.addData(new NullType());
		}
		t.addRow(row);
		return t;
	}
}
