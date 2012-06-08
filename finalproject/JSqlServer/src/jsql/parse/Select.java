/**
 * 
 */
package jsql.parse;

import java.util.Vector;

import jsql.data.QueryRow;
import jsql.data.QueryTable;
import jsql.data.Result;
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
	private OrderBy orderBy;
	private Vector<GroupByItem> groupBy;
	private ExpressionTree having;
	
	private QueryTable queryTable;
	private QueryRow currentRow;
	private Select parent;
	
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
					sel.orderBy = new OrderBy(key);
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
	
	public Type getParentQueryData(ColumnConstant col) {
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

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public Vector<GroupByItem> getGroupBy() {
		return groupBy;
	}

	public ExpressionTree getHaving() {
		return having;
	}
	
	public Result executeQuery(Select parent) throws Exception {
		this.parent = parent;
		executeFrom();
		return null;
	}
	
	private void executeFrom() throws Exception {
		FromTree fromFree = (FromTree)from;
		queryTable = fromFree.executeFrom(database);
	}
	
	private void executeSelect() throws Exception {
		
	}
}
