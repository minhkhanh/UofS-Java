package jsql.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private List<Row> rows;

	private List<Column> columns;

	public Table() {
		columns = new ArrayList<Column>();
		rows = new ArrayList<Row>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		if (rows == null)
			return;
		this.rows = rows;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		if (columns == null)
			return;
		this.columns = columns;
		rows.clear();
	}

	// added by khuong
	public Column getColumn(int idx) {
		return columns.get(idx);
	}

	public Row getRow(int idx) {
		return rows.get(idx);
	}

	public Object getValue(int row, int col) {
		// ??
		return null;
	}

}
