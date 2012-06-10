/**
 * 
 */
package jsql.parse;

import java.util.Hashtable;
import java.util.Vector;

import jsql.data.Column;
import jsql.data.QueryRow;
import jsql.data.QueryTable;
import jsql.data.Result;
import jsql.data.Row;
import jsql.data.Table;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class Select extends Statement implements Exp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExpressionTree where;
	private Vector<SelectItem> select;	
	private Exp from;
	private Vector<OrderBy> orderBy;
	private Vector<GroupByItem> groupBy;
	private ExpressionTree having;
	
	private QueryTable queryTable;
	private QueryRow currentRow;
	private Select parent;
	private Vector<QueryTable> queryGroup;
	private Vector<String> columnNames;
	
	private Select()  {		
	}
	
//	public QueryTable executeQuery() {
//		
//	}	

	public static Select createStatement(String sqlStatement) throws Exception {
		if (!Utils.checkStringPrefix(sqlStatement, "SELECT")) throw new Exception("do'nt have SELECT");
		try {
			String[] listKey = Utils.splitString(sqlStatement, new String[]{"SELECT", "FROM", "WHERE", "ORDER BY", "GROUP BY", "HAVING"});
			Select sel = new Select();
			for (String key : listKey) {
				if (key==null) continue;
				if (Utils.indexOfString(key, "SELECT")==0) {
					if (sel.select!=null) throw new Exception("statement have 2 select");
					sel.select = new Vector<SelectItem>();	
					key = key.substring(6).trim();
					String[] listCol = key.split(",");//Utils.splitString(key, new String[]{","});
					for (String string : listCol) {
						SelectItem item = new SelectItem(string.trim());
						sel.select.add(item);
					}
					continue;
				}
				if (Utils.indexOfString(key, "FROM")==0) {
					if (sel.from!=null) throw new Exception("statement have 2 FROM");
					key = key.substring(4).trim();
					sel.from = FromTree.createFrom(key);
					continue;
				}
				if (Utils.indexOfString(key, "WHERE")==0) {
					if (sel.where!=null) throw new Exception("statement have 2 WHERE");
					key = key.substring(5).trim();
					sel.where = ExpressionTree.createWhere(key);
					continue;
				}
				if (Utils.indexOfString(key, "ORDER BY")==0) {
					if (sel.orderBy!=null) throw new Exception("statement have 2 ORDER BY");
					key = key.substring(8).trim();
					String[] listOrder = key.split(",");
					sel.orderBy = new Vector<OrderBy>();
					for (String order : listOrder) {
						sel.orderBy.add(new OrderBy(order));
					}
					continue;
				}
				if (Utils.indexOfString(key, "GROUP BY")==0) {
					if (sel.groupBy!=null) throw new Exception("statement have 2 GROUP BY");
					key = key.substring(8).trim();
					String[] listCol = key.split(",");
					sel.groupBy = new Vector<GroupByItem>();
					for (String string : listCol) {
						GroupByItem item = new GroupByItem(string.trim());
						sel.groupBy.add(item);
					}
					continue;
				}
				if (Utils.indexOfString(key, "HAVING")==0) {
					if (sel.having!=null) throw new Exception("statement have 2 select");
					key = key.substring(6).trim();
					sel.having = (ExpressionTree) ExpressionTree.createExp(key);
					continue;
				}
			}
			return  sel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Type getParentQueryData(ColumnConstant col) throws Exception {
		if (currentRow==null) return null;
		Type type = currentRow.getData(col);
		if (type!=null) return type;
		if (parent==null) return null;
		return parent.getParentQueryData(col);
	}

	public ExpressionTree getWhere() {
		return where;
	}

	public void setWhere(ExpressionTree where) {
		this.where = where;
	}

	public Vector<SelectItem> getSelect() {
		return select;
	}
	
	public Exp getFrom() {
		return from;
	}

	public Vector<OrderBy> getOrderBy() {
		return orderBy;
	}

	public Vector<GroupByItem> getGroupBy() {
		return groupBy;
	}

	public ExpressionTree getHaving() {
		return having;
	}
	
	public Result executeSelect(Select parent) throws Exception {
		Table table = executeQuery(parent);
		Result r = new Result(null);
		r.setTable(table);
		return r;
	}
	
	private Table executeQuery(Select parent) throws Exception {
		this.parent = parent;
		executeFrom();
		executeWhere();
		executeGroupBy();
		executeSelect();
		executeOrderBy();
		Vector<Column> listCol = new Vector<Column>();
		for (String col : columnNames) {
			listCol.add(new Column(col, null));
		}
		Table t = new Table(listCol, queryTable.getRows());
		return t;
	}
	
	private void executeFrom() throws Exception {
		if (from==null) throw new Exception("menh de select ko co from");
		if (from instanceof TableConstant) queryTable = new QueryTable((TableConstant) from, database);
		else queryTable = ((FromTree)from).executeFrom(database); 
	}
	
	private void executeWhere() throws Exception {
		if (where==null) return;
		for (int i = 0; i < queryTable.getRows().size(); ) {
			Row row = queryTable.getRows().elementAt(i);
			QueryRow queryRow = new QueryRow(row, queryTable.getColumns());
			if (where!=null && !where.filterByExpression(queryRow)) {
				queryTable.removeRow(i);
			} else ++i;
		}
	}
	
	private void executeGroupBy() throws Exception {
		if (groupBy==null && having==null) return;
		if (groupBy==null && having!=null) throw new Exception("menh de select co having ma ko co group by");
		queryGroup = new Vector<QueryTable>();
		for (int i = 0; i < queryTable.getRows().size(); ++i) {
			Row row = queryTable.getRows().elementAt(i);
			QueryTable table = null;
			for (QueryTable t : queryGroup) {
				if (t.isRowOfGroup(row)) {
					table = t;
					break;
				}
			}
			if (table==null) {
				table = new QueryTable(queryTable.getColumns(), groupBy);
				queryGroup.add(table);
			}
			table.addRow(row);
		}
		if (having!=null) {
			for (int i = 0; i < queryGroup.size(); ) {
				if (!having.filterByExpression(queryGroup.get(i))) {
					queryGroup.remove(i);
				} else ++i;
			}
		}
	}
	
	private void executeSelect() throws Exception {		
		columnNames = new Vector<String>();
		Vector<Exp> listSelect = new Vector<Exp>();
		for (int i = 0; i < select.size(); i++) {
			Exp exp = select.get(i).getValue();
			if (exp instanceof ExpressionTree) {
				listSelect.add(exp);
				if (select.get(i).getAlias()!=null) columnNames.add(select.get(i).getAlias());
				else columnNames.add(exp.toString());
			}
			if (exp instanceof ColumnConstant) {
				ColumnConstant col = (ColumnConstant)exp;
				if (col.isWildcard()) {
					for (ColumnConstant colCostant : queryTable.getListColumn()) {
						listSelect.add(colCostant);
						columnNames.add(colCostant.toString());
					}
				} else {
					listSelect.add(col);
					if (select.get(i).getAlias()!=null) columnNames.add(select.get(i).getAlias());
					else columnNames.add(exp.toString());
				}
			}
		}
		Vector<Row> listRow = new Vector<Row>();
		if (groupBy != null) {
			//check cac select item
			for (int i = 0; i < listSelect.size(); i++) {
				if (!(listSelect.get(i) instanceof ColumnConstant)) continue;
				boolean bFound = false;
				Integer iCol = queryTable.getColumnIndex((ColumnConstant) listSelect.get(i));
				for (GroupByItem groupItem : groupBy) {
					if (iCol==queryTable.getColumnIndex(groupItem.getColumn())) {
						bFound = true;
						break;
					}
				}
				if (!bFound) throw new Exception("loi cau lenh select - lay ve column khong dc group by");
			}
			for (int i = 0; i < queryGroup.size(); i++) {
				QueryTable table = queryGroup.get(i);			
				Row newRow = new Row();
				for (int j = 0; j < listSelect.size(); j++) {					
					Exp exp = listSelect.get(j);	
					if (exp instanceof ExpressionTree) {
						Constant c = ((ExpressionTree)exp).evaluate(table);
						newRow.addData((Type) c.getValue());
						continue;
					}
					if (exp instanceof ColumnConstant) {
						ColumnConstant col = (ColumnConstant)exp;
						Integer iCol = table.getColumnIndex(col);
						Type t = table.getRows().firstElement().getDataAt(iCol);
						newRow.addData(t);
						continue;
					}
					throw new Exception("loi cau lenh select");
				}
				listRow.add(newRow);
			}
		} else {
			for (int i = 0; i < queryTable.getRows().size(); i++) {
				Row oldRow = queryTable.getRows().get(i);
				Row newRow = new Row();
				for (int j = 0; j < listSelect.size(); j++) {					
					Exp exp = listSelect.get(j);	
					if (exp instanceof ExpressionTree) {
						Constant c = ((ExpressionTree)exp).evaluate(queryTable);
						newRow.addData((Type) c.getValue());
						continue;
					}
					if (exp instanceof ColumnConstant) {
						ColumnConstant col = (ColumnConstant)exp;
						Integer iCol = queryTable.getColumnIndex(col);
						Type t = oldRow.getDataAt(iCol);
						newRow.addData(t);
						continue;
					}
					throw new Exception("loi cau lenh select");
				}
				listRow.add(newRow);
			}
		}
		//loc lai kq neu co distanct		
		for (int i = 0; i < listSelect.size(); i++) {					
			Exp exp = listSelect.get(i);	
			if (exp instanceof ColumnConstant) {
				ColumnConstant col = (ColumnConstant)exp;
				if (col.isDistinct()) {
					Vector<Row> listRowNew = new Vector<Row>();
					for (int j = 0; j < listRow.size(); j++) {
						boolean bFound = false;
						Row rowA = listRow.get(j);
						for (int k = j+1; k < listRow.size(); k++) {
							Row rowB = listRow.get(k);
							if (rowA.getDataAt(i).equals(rowB.getDataAt(i))) {
								bFound = true;
								continue;
							}
						}
						if (!bFound) listRowNew.add(rowA);
					}	
					listRow = listRowNew;
				}
			}
		}
		//tai tao hash column
		Hashtable<ColumnConstant, Integer> columns = new Hashtable<ColumnConstant, Integer>();
		for (int i = 0; i < listSelect.size(); i++) {
			Exp exp = listSelect.get(i);
			if (exp instanceof ColumnConstant) {
				Integer iCol = queryTable.getColumnIndex((ColumnConstant) exp);
				for (ColumnConstant col : queryTable.getListColumn(iCol)) {
					columns.put(col, i);
				}
				continue;
			}
			if (exp instanceof ExpressionTree) {
				SelectItem item = null;
				for (SelectItem selectItem : select) {
					if (selectItem.getValue()==exp) {
						item = selectItem;
						break;
					}
				}
				if (item!=null && item.getAlias()!=null) {
					columns.put(new ColumnConstant(item.getAlias(), null), i);
				} else columns.put(new ColumnConstant(null, exp.toString()), i);
			}
		}
		//tai tao querytable
		queryTable = new QueryTable(columns, listRow, null); 
	}
	
	private void executeOrderBy() throws Exception {
		if (orderBy==null) return;
		Vector<Row> listRow = queryTable.getRows();
		for (int i = 0; i < listRow.size()-1; i++) {
			for (int j = i+1; j < listRow.size(); j++) {
				if (!compare(listRow.get(i), listRow.get(j))) {
					Row t = listRow.get(i);
					listRow.set(i, listRow.get(j));
					listRow.set(j, t);
				}
			}
		}

	}
	
	//thoa trat tu
	private boolean compare(Row rowA, Row rowB) throws Exception {
		for (OrderBy order : orderBy) {
			Integer iCol = queryTable.getColumnIndex(order.getColumnConstant());
			int iCompare = rowA.getDataAt(iCol).compareTo(rowB.getDataAt(iCol));
			if ((iCompare>0 && order.isAsc())||(iCompare<0 && !order.isAsc())) return false;
			if (iCompare==0) continue;
			break;
		}
		return true;
	}
}
