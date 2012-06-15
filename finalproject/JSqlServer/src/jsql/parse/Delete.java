/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class Delete extends Statement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String table;
	private ExpressionTree where;
	private TableConstant tableConstant;
	
	private Delete(String tableName) {
		setTable(new String(tableName));
	}
	
	public static Delete createStatement(String sqlStatement) throws Exception {
		if (!Utils.checkStringPrefix(sqlStatement, "DELETE")) throw new Exception("do'nt have DELETE");
		try {			
			sqlStatement = sqlStatement.substring(6).trim();
			if (!Utils.checkStringPrefix(sqlStatement, "FROM")) throw new Exception("do'nt have DELETE");
			sqlStatement = sqlStatement.substring(4).trim();
			
			int iWhere = Utils.indexOfString(sqlStatement, "WHERE");
			if (iWhere==0) throw new Exception("do'nt have table");
			String table;
			if (iWhere<0) table = sqlStatement.trim();
			else {
				table = sqlStatement.substring(0, iWhere).trim();
				sqlStatement = sqlStatement.substring(iWhere).trim();				
			}
			Delete del = new Delete(table); 
			del.tableConstant = (TableConstant) TableConstant.create(new StringBuilder(table));
			if (iWhere>=0) {				
				iWhere = Utils.indexOfString(sqlStatement, "WHERE");
				if (iWhere<0) throw new Exception("do'nt have table");
				//co menh de where				
				sqlStatement = sqlStatement.substring(iWhere + 5).trim();
				ExpressionTree exp = (ExpressionTree) ExpressionTree.createWhere(sqlStatement);
				del.setWhere(exp);
			}
			


			return  del;
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

	public TableConstant getTableConstant() {
		return tableConstant;
	}

}
