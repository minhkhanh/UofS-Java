/**
 * 
 */
package jsql.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tmkhanh
 *
 */
public class Row implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private List<Object> data;

	public Row() {
		data = new ArrayList<Object>();
//		for (int i = 0; i < column.length; i++) {
//			data[i] = column[i].getType().newInstance();
//		}
	}
	public Object getDataAt(int index) {
		return data.get(index);
	}
	public void setDataAt(int index, Object obj) {
		data.set(index, obj);
	}
	public void addData(Object obj) {
		data.add(obj);
	}
	public List<Object> getData() {
		return data;
	}
}
