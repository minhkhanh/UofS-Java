/**
 * 
 */
package jsql.data;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jsql.data.Row;
import jsql.parse.ColumnConstant;
import jsql.parse.ExpressionTree;
import jsql.parse.OperatorFullJoin;
import jsql.parse.OperatorInnerJoin;
import jsql.parse.OperatorJoin;
import jsql.parse.OperatorLeftJoin;
import jsql.parse.OperatorRightJoin;
import jsql.parse.TableConstant;

/**
 * @author tmkhanh
 *
 */
public class QueryTable {
	//private String name;
	//private String alias;
	private Vector<Row> rows;
	private Hashtable<ColumnConstant, Integer> columns;
	
	public QueryTable(TableConstant tableConstant, Database data) throws Exception {
		String tableName = tableConstant.getName();
		if (tableName==null) throw new Exception("error table name");
		if (!data.haveTable(tableName)) throw new Exception("khong ton tai table");
		//this.name = tableName;
		Table table = data.getTable(tableName);
		columns = new Hashtable<ColumnConstant, Integer>();
		String alias = tableConstant.getAlias();
		for (int i = 0; i < table.getColumns().size(); i++) {
			Column column = table.getColumn(i);
			ColumnConstant col = new ColumnConstant(tableName, column.getName());
			columns.put(col, i);
			if (alias!=null) {
				col = new ColumnConstant(alias, column.getName());
				columns.put(col, i);
			}
		}
		rows = new Vector<Row>();
		for (int i = 0; i < table.getRows().size(); i++) {
			rows.add(table.getRow(i));
		}
	}
	
	public Vector<Row> getRows() {
		return rows;
	}
	public void setRows(Vector<Row> rows) {
		this.rows = rows;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getAlias() {
//		return alias;
//	}
//	public void setAlias(String alias) {
//		this.alias = alias;
//	}
	public QueryTable executeOperator(OperatorJoin join, ExpressionTree on, QueryTable queryTable) throws Exception {
		if (join instanceof OperatorInnerJoin) {
			return innerJoin(on, queryTable);
		}
		if (join instanceof OperatorLeftJoin) {
			return leftJoin(on, queryTable);
		}
		if (join instanceof OperatorRightJoin) {
			return rightJoin(on, queryTable);
		}
		if (join instanceof OperatorFullJoin) {
			return fullJoin(on, queryTable);
		}
		return this;
	}
	private void joinColumn(Hashtable<ColumnConstant, Integer> columnsB) {
		//gop column
		int oldSize = -1;
		Enumeration<ColumnConstant> listTmp  = columns.keys();
		while (listTmp.hasMoreElements()) {
			ColumnConstant constant = (ColumnConstant) listTmp.nextElement();
			Integer j = columns.get(constant);
			if (j!=null && j>oldSize) oldSize = j;
		}
		++oldSize;
		
		Enumeration<ColumnConstant> listColB  = columnsB.keys();
		while (listColB.hasMoreElements()) {
			ColumnConstant columnConstantB = (ColumnConstant) listColB.nextElement();
			Enumeration<ColumnConstant> listColA  = columns.keys();
			ColumnConstant col = null;
			while (listColA.hasMoreElements()) {
				ColumnConstant columnConstantA = (ColumnConstant) listColA.nextElement();
				if (columnConstantB.equals(columnConstantA)) {
					col = columnConstantA;
					break;
				}
			}
			if (col!=null) {
				columns.remove(col);
			} else {
				int iVal = columnsB.get(columnConstantB);
				iVal += oldSize;
				columns.put(columnConstantB, iVal);
			}
		}
	}
	
	private QueryTable fullJoin(ExpressionTree on, QueryTable queryTable) throws Exception {
		joinColumn(queryTable.columns);
		Vector<Row> nRows = new Vector<Row>();
		Vector<Row> rowBNoJoin = new Vector<Row>();
		for (Row rowA : rows) {
			boolean bHave = false;
			for (Row rowB : queryTable.rows) {
				Row newRow = Row.createNewRow(rowA, rowB);
				QueryRow queryRow = new QueryRow(newRow, columns);
				if (on!=null) {
					if (on.filterByExpression(queryRow)) {
						nRows.add(newRow);
						bHave = true;
					} else rowBNoJoin.add(rowB);
				} else nRows.add(newRow);
			}
			if (bHave) {
				Row newRow = Row.createNewRowRightNull(rowA, queryTable.rows.firstElement().getNumCol());
				nRows.add(newRow);
			}
		}
		for (Row row : rowBNoJoin) {
			Row newRow = Row.createNewRowLeftNull(row, rows.firstElement().getNumCol());
			nRows.add(newRow);
		}
		rows = nRows;
		return this;
	}
	
	private QueryTable innerJoin(ExpressionTree on, QueryTable queryTable) throws Exception {
		joinColumn(queryTable.columns);
		Vector<Row> nRows = new Vector<Row>();
		for (Row rowA : rows) {
			for (Row rowB : queryTable.rows) {
				Row newRow = Row.createNewRow(rowA, rowB);
				QueryRow queryRow = new QueryRow(newRow, columns);
				if (on!=null) {
					if (on.filterByExpression(queryRow)) {
						nRows.add(newRow);
					}
				} else nRows.add(newRow);
			}
		}
		rows = nRows;
		return this;
	}
	
	private QueryTable leftJoin(ExpressionTree on, QueryTable queryTable) throws Exception {
		joinColumn(queryTable.columns);
		Vector<Row> nRows = new Vector<Row>();
		for (Row rowA : rows) {
			boolean bHave = false;
			for (Row rowB : queryTable.rows) {
				Row newRow = Row.createNewRow(rowA, rowB);
				QueryRow queryRow = new QueryRow(newRow, columns);
				if (on!=null) {
					if (on.filterByExpression(queryRow)) {
						nRows.add(newRow);
						bHave = true;
					}
				} else nRows.add(newRow);
			}
			if (bHave) {
				Row newRow = Row.createNewRowRightNull(rowA, queryTable.rows.firstElement().getNumCol());
				nRows.add(newRow);
			}
		}
		rows = nRows;
		return this;
	}
	private QueryTable rightJoin(ExpressionTree on, QueryTable queryTable) throws Exception {
		return queryTable.leftJoin(on, this);
	}
}
