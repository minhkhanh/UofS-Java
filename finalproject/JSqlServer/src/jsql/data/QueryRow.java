/**
 * 
 */
package jsql.data;

import java.util.Enumeration;
import java.util.Hashtable;

import jsql.parse.ColumnConstant;

/**
 * @author tmkhanh
 *
 */
public class QueryRow {
	private Row row;
	private Hashtable<ColumnConstant, Integer> columns;
	public QueryRow(Row row, Hashtable<ColumnConstant, Integer> columns) {
		this.row = row;
		this.columns = columns;
	}
	public Row getRow() {
		return row;
	}
	public Hashtable<ColumnConstant, Integer> getColumns() {
		return columns;
	}
	public Type getData(ColumnConstant col) throws Exception {
		Enumeration<ColumnConstant> listCol  = columns.keys();
		Integer index = null;
		while (listCol.hasMoreElements()) {
			ColumnConstant columnConstant = (ColumnConstant) listCol
					.nextElement();
			if (columnConstant.equals(col)) {
				Integer i = columns.get(columnConstant);
				if(index!=null && i !=null) throw new Exception("loi column khong dam bao duy nhat, thu dung alias");
				index = i;
			}
		}
		if (index!=null) return row.getDataAt(index);
		return null;
	}
	public Integer getColumnIndex(ColumnConstant col) throws Exception {
		Enumeration<ColumnConstant> listCol  = columns.keys();
		Integer index = null;
		while (listCol.hasMoreElements()) {
			ColumnConstant columnConstant = (ColumnConstant) listCol
					.nextElement();
			if (columnConstant.equals(col)) {
				Integer i = columns.get(columnConstant);
				if(index!=null && i !=null) throw new Exception("loi column khong dam bao duy nhat, thu dung alias");
				index = i;
			}
		}
		return index;
	}
}


