/**
 * 
 */
package jsql.data;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jsql.data.Row;
import jsql.parse.ColumnConstant;
import jsql.parse.Constant;
import jsql.parse.ExpressionTree;
import jsql.parse.FloatConstant;
import jsql.parse.GroupByItem;
import jsql.parse.IntConstant;
import jsql.parse.OperatorAggregate;
import jsql.parse.OperatorAvg;
import jsql.parse.OperatorCount;
import jsql.parse.OperatorFirst;
import jsql.parse.OperatorFullJoin;
import jsql.parse.OperatorInnerJoin;
import jsql.parse.OperatorJoin;
import jsql.parse.OperatorLast;
import jsql.parse.OperatorLeftJoin;
import jsql.parse.OperatorMax;
import jsql.parse.OperatorMin;
import jsql.parse.OperatorRightJoin;
import jsql.parse.OperatorSum;
import jsql.parse.Select;
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
	private Vector<GroupByItem> groupBys;
	
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
	
	public QueryTable(Hashtable<ColumnConstant, Integer> columns) {
		this.columns = columns;
		rows = new Vector<Row>();
	}
	
	public QueryTable(Hashtable<ColumnConstant, Integer> columns, Vector<Row> rows, Integer v) {
		this.columns = columns;
		this.rows = rows;
	}
	
	public QueryTable(Hashtable<ColumnConstant, Integer> columns, Vector<GroupByItem> groupBys) {
		this.columns = columns;
		rows = new Vector<Row>();
		this.groupBys = groupBys;
	}
	
	public void addRow(Row row) {
		rows.add(row);
	}
	
	public Vector<ColumnConstant> getListColumn() {
		Enumeration<ColumnConstant> listCol = columns.keys();
		Vector<ColumnConstant> vecCol = new Vector<ColumnConstant>();
		while (listCol.hasMoreElements()) {
			ColumnConstant columnConstant = (ColumnConstant) listCol
					.nextElement();
			Integer i = columns.get(columnConstant);
			boolean fFound = false;
			for (ColumnConstant constant : vecCol) {
				Integer j = columns.get(constant);
				if (i==j) {
					fFound = true;
					break;
				}
			}
			if (!fFound) vecCol.add(columnConstant);
		}
		return vecCol;
	}
	
	public Vector<ColumnConstant> getListColumn(Integer index) {
		Enumeration<ColumnConstant> listCol = columns.keys();
		Vector<ColumnConstant> vecCol = new Vector<ColumnConstant>();
		while (listCol.hasMoreElements()) {
			ColumnConstant columnConstant = (ColumnConstant) listCol.nextElement();
			Integer i = columns.get(columnConstant);
			if (index==i) vecCol.add(columnConstant);
		}
		return vecCol;
	}
	
	public boolean isRowOfGroup(Row row) throws Exception {
		for (Row r : rows) {
			for (GroupByItem groupByItem : groupBys) {
				Integer i = getColumnIndex(groupByItem.getColumn());
				if (i==null) throw new Exception("group by loi input - khong co column can group");
				if (!r.getDataAt(i).equals(row.getDataAt(i))) return false;
			}
			break;
		}
		return true;
	}
	public Integer getColumnIndex(ColumnConstant col) throws Exception {
		Enumeration<ColumnConstant> listCol  = columns.keys();
		Integer index = null;
		while (listCol.hasMoreElements()) {
			ColumnConstant columnConstant = (ColumnConstant) listCol
					.nextElement();
			if (columnConstant.equals(col)) {
				Integer i = columns.get(columnConstant);
				if(index!=null && i !=null) 
					throw new Exception("loi column khong dam bao duy nhat, thu dung alias");
				index = i;
			}
		}
		return index;
	}
	public Vector<Row> getRows() {
		return rows;
	}
	public Row removeRow(int index) {
		return rows.remove(index);
	}
	public void setRows(Vector<Row> rows) {
		this.rows = rows;
	}
	public Hashtable<ColumnConstant, Integer> getColumns() {
		return columns;
	}
	public Vector<GroupByItem> getGroupBys() {
		return groupBys;
	}

//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getAlias() {
//		return alias;
//	}
//	public void setAlias(String alias) {
//		this.alias = alias;
//	}
	public QueryTable executeOperator(OperatorJoin join, ExpressionTree on, QueryTable queryTable, Select parent) throws Exception {
		if (join instanceof OperatorInnerJoin) {
			return innerJoin(on, queryTable, parent);
		}
		if (join instanceof OperatorLeftJoin) {
			return leftJoin(on, queryTable, parent);
		}
		if (join instanceof OperatorRightJoin) {
			return rightJoin(on, queryTable, parent);
		}
		if (join instanceof OperatorFullJoin) {
			return fullJoin(on, queryTable, parent);
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
	
	private QueryTable fullJoin(ExpressionTree on, QueryTable queryTable, Select parent) throws Exception {
		joinColumn(queryTable.columns);
		Vector<Row> nRows = new Vector<Row>();
		Vector<Row> rowBNoJoin = new Vector<Row>();
		for (Row rowA : rows) {
			boolean bHave = false;
			for (Row rowB : queryTable.rows) {
				Row newRow = Row.createNewRow(rowA, rowB);
				QueryRow queryRow = new QueryRow(newRow, columns);
				if (on!=null) {
					if (on.filterByExpression(queryRow, parent)) {
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
	
	private QueryTable innerJoin(ExpressionTree on, QueryTable queryTable, Select parent) throws Exception {
		joinColumn(queryTable.columns);
		Vector<Row> nRows = new Vector<Row>();
		for (Row rowA : rows) {
			for (Row rowB : queryTable.rows) {
				Row newRow = Row.createNewRow(rowA, rowB);
				QueryRow queryRow = new QueryRow(newRow, columns);
				if (on!=null) {
					if (on.filterByExpression(queryRow, parent)) {
						nRows.add(newRow);
					}
				} else nRows.add(newRow);
			}
		}
		rows = nRows;
		return this;
	}
	
	private QueryTable leftJoin(ExpressionTree on, QueryTable queryTable, Select parent) throws Exception {
		joinColumn(queryTable.columns);
		Vector<Row> nRows = new Vector<Row>();
		for (Row rowA : rows) {
			boolean bHave = false;
			for (Row rowB : queryTable.rows) {
				Row newRow = Row.createNewRow(rowA, rowB);
				QueryRow queryRow = new QueryRow(newRow, columns);
				if (on!=null) {
					if (on.filterByExpression(queryRow, parent)) {
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
	private QueryTable rightJoin(ExpressionTree on, QueryTable queryTable, Select parent) throws Exception {
		return queryTable.leftJoin(on, this, parent);
	}
	
	public Constant executeAggregate(OperatorAggregate operator, ColumnConstant col) throws Exception {		
		if (operator instanceof OperatorAvg) {
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			NumberType sum = new FloatType(0f);
			for (Row row : rows) {
				//IntType t = (IntType) row.getDataAt(i);
				//sum += (Integer)t.getValue();
				sum = sum.cong((NumberType) row.getDataAt(i));
			}
			return new FloatConstant(new FloatType((Float) (sum.getValue()) / rows.size()));
		}
		if (operator instanceof OperatorCount) {
			if (col.isWildcard()) return new IntConstant(new IntType(rows.size()));
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			Vector<Type> list = new Vector<Type>();
			for (Row row : rows) {
				Type t = row.getDataAt(i);
				if (t instanceof NullType) continue;
				list.add(t);
			}
			if (!col.isDistinct()) return new IntConstant(new IntType(list.size()));
			int iCount = 0;
			for (int j = 0; j < list.size(); j++) {
				boolean bFound = false;
				for (int k = j+1; k < list.size(); k++) {
					Type t1 = list.get(j);
					Type t2 = list.get(k);
					if (t1.equals(t2)) bFound = true;
				}
				if (!bFound) ++iCount;
			}
			return new IntConstant(new IntType(iCount));
		}
		if (operator instanceof OperatorFirst) {
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			Row row = rows.firstElement();
			return Constant.create(row.getDataAt(i));
		}
		if (operator instanceof OperatorLast) {
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			Row row = rows.lastElement();
			return Constant.create(row.getDataAt(i));
		}
		if (operator instanceof OperatorMax) {
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			Type max = rows.firstElement().getDataAt(i);
			for (Row row : rows) {
				Type t = row.getDataAt(i);				
				if (t.compareTo(max)==1) max = t;
			}
			return Constant.create(max);
		}
		if (operator instanceof OperatorMin) {
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			Type min = rows.firstElement().getDataAt(i);
			for (Row row : rows) {
				Type t = row.getDataAt(i);				
				if (t.compareTo(min)==-1) min = t;
			}
			return Constant.create(min);
		}
		if (operator instanceof OperatorSum) {
			Integer i = getColumnIndex(col);
			if (i==null) throw new Exception("column Aggregate error");
			NumberType sum = new IntType(0);
			for (Row row : rows) {
				sum = sum.cong((NumberType) row.getDataAt(i));
			}
			return Constant.create(sum);
		}
		return null;
	}
}
