package jsql.data;

import java.io.Serializable;
import java.util.Vector;

import jsql.parse.Delete;
import jsql.parse.Insert;
import jsql.parse.Update;
import jsql.parse.UpdateChange;

public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private Vector<Row> rows;

	private Vector<Column> columns;

	public Table() {
		columns = new Vector<Column>();
		rows = new Vector<Row>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<Row> getRows() {
		return rows;
	}

	public void setRows(Vector<Row> rows) {
		if (rows == null)
			return;
		this.rows = rows;
	}

	public Vector<Column> getColumns() {
		return columns;
	}

	public void setColumns(Vector<Column> columns) {
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

	public Type getValue(int row, int col) {
		if (col>columns.size() || row>rows.size()) return null;
		if (col<0 || row<0) return null;
		return rows.get(row).getDataAt(col);
	}
	
	@SuppressWarnings("rawtypes")
	public Class getColumnType(String name) {
		for (Column col : columns) {
			if (col.getName().equals(name)) return col.getClassType();
		}
		return null;
	}
	
	public int getColumnIndex(String name) {
		for (int i = 0; i < columns.size(); i++) {
			Column col = columns.get(i);
			if (col.getName().equals(name)) return i;
		}
		return -1;
	}
	
	private void addRow(Row row) throws Exception {
		if (row==null) return;
		//check key, data
		for (int i = 0; i < columns.size(); i++) {
			Column col = columns.get(i);
			if (col.isPrimary()) {
				if (row.getDataAt(i)==null) throw new Exception("key is null");
				for (Row r : rows) {
					if (r.getDataAt(i).compareTo(row.getDataAt(i))==0) throw new Exception("key is exist");
				}
			}	
		}			
		rows.add(row);
	}
	
	private void removeRow(Row row) {
		rows.remove(row);
	}
	
	private boolean checkInsertInput(Vector<String> columnsName, Vector<Type> values) {
		if (columns.size() < values.size()) return false;
		if (columnsName.size() == 0) {
			// ko chi ra column
			if (columns.size() != values.size()) return false;

			// check type
			for (int i = 0; i < columns.size(); ++i) {
				if (columns.get(i).getClassType() != values.get(i).getClass()) return false;
			}
		}
		if (columnsName.size() > columns.size()) return false;
		for (int i=0; i<columnsName.size(); ++i) {
			if (getColumnType(columnsName.get(i))!=values.get(i).getClass()) return false;
		}
		return true;
	}

	void executeInsert(Insert insert) throws Exception {
		Vector<String> columnsName = insert.getColumns();
		Vector<Type> values = insert.getValues();
		if (!checkInsertInput(columnsName, values)) throw new Exception("check input insert false");
		if (columnsName.size()==0) {
			addRow(new Row(values));
			return;
		}
		Row row = new Row(columns.size());
		for (int i = 0; i < columnsName.size(); i++) {
			int index = getColumnIndex(columnsName.get(i));
			if (index==-1) throw new Exception("column not exist");
			row.setDataAt(index, values.get(i));
		}
		addRow(row);
	}
	int executeDelete(Delete del) throws Exception {
		int iCount = 0;
		if (del.getWhere()==null) {
			iCount = rows.size();
			rows.clear();
			return iCount;
		}
		RowInfo rowInfo = new RowInfo(columns);
		for (int i = 0; i < rows.size(); ) {
			Row row = rows.get(i);
			rowInfo.setRow(row);
			if (del.getWhere().filterByExpression(rowInfo, del.getDatabase())) {
				removeRow(row);
				++iCount;
			} else ++i;
		}
		return iCount;
	}

	public int executeUpdate(Update update) throws Exception {
		int iCount = 0;
		RowInfo rowInfo = new RowInfo(columns);
		for (int i = 0; i < rows.size(); ++i) {
			rowInfo.setRow(rows.get(i));
			if (update.getWhere() == null
					|| update.getWhere().filterByExpression(rowInfo,
							update.getDatabase())) {
				Row row = new Row(rows.get(i));
				for (UpdateChange change : update.getChange()) {
					row.setDataAt(rowInfo.getColumnIndex((String) change
							.getColumn().getBaseValue()), change.getValue());
				}
				if (updateRow(i, row))
					++iCount;
			}
			;
		}
		return iCount;
	}

	private boolean updateRow(int index, Row row) {
		//check key
		rows.set(index, row);
		return true;
	}
}