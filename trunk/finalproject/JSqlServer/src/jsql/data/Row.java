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
	private Vector<Type> data = new Vector<Type>();

	public Row() {
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
}
