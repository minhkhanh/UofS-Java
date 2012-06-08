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
	public Type getData(ColumnConstant col) {
		Enumeration<ColumnConstant> listCol  = columns.keys();
		while (listCol.hasMoreElements()) {
			ColumnConstant columnConstant = (ColumnConstant) listCol
					.nextElement();
			if (columnConstant.equals(col)) {
				int iIndex = columns.get(columnConstant);
				return row.getDataAt(iIndex);
			}
		}
		return null;
	}
}
