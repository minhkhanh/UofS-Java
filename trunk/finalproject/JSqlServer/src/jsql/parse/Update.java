/**
 * 
 */
package jsql.parse;

import java.util.Vector;

/**
 * @author tmkhanh
 *
 */
public class Update extends Statement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String table;
	private ExpressionTree where;
	private Vector<UpdateChange> change;
	private TableConstant tableConstant;
	
	private Update(String tableName) {
		setTable(new String(tableName));
		change = new Vector<UpdateChange>();
	}
	
	public static Update createStatement(String sqlStatement) throws Exception {
		if (!Utils.checkStringPrefix(sqlStatement, "UPDATE")) throw new Exception("do'nt have UPDATE");
		try {			
			sqlStatement = sqlStatement.substring(6).trim();
			int iTable = sqlStatement.indexOf(' ');
			String tableName = sqlStatement.substring(0, iTable);
			Update update = new Update(tableName);
			update.tableConstant = (TableConstant) TableConstant.create(new StringBuilder(tableName));
			sqlStatement = sqlStatement.substring(iTable).trim();
			if (!Utils.checkStringPrefix(sqlStatement, "SET")) throw new Exception("do'nt have SET");
			sqlStatement = sqlStatement.substring(3).trim();						
			int iWhere = Utils.indexOfString(sqlStatement, "WHERE");
			String set;
			if (iWhere<0) {
				set = sqlStatement.trim();
			} else {
				set = sqlStatement.substring(0, iWhere);
				sqlStatement = sqlStatement.substring(iWhere).substring(5).trim();
				ExpressionTree exp = (ExpressionTree) ExpressionTree.createWhere(sqlStatement);
				update.setWhere(exp);
			}
			int iP = -1;
			while (set.length()>0 && (iP = set.indexOf(','))>-1) {
				if (iP>-1) {
					String sub = set.substring(0, iP);
					update.change.add(UpdateChange.create(sub));
					set = set.substring(iP+1).trim();
				}
			}
			if (set.length()>0) update.change.add(UpdateChange.create(set));
			if (update.change.size()==0) throw new Exception("update syntax error!");
			return  update;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public ExpressionTree getWhere() {
		return where;
	}

	public void setWhere(ExpressionTree where) {
		this.where = where;
	}

	public Vector<UpdateChange> getChange() {
		return change;
	}

	public void setChange(Vector<UpdateChange> change) {
		this.change = change;
	}
	public TableConstant getTableConstant() {
		return tableConstant;
	}
}
