/**
 * 
 */
package jsql.parse;

import jsql.data.Database;
import jsql.data.QueryTable;


/**
 * @author tmkhanh
 *
 */
public class FromTree extends ExpressionTree {
	protected ExpressionTree on;
	protected FromTree(Operator operator) {
		super(operator);
	}
	public static Exp createFrom(String sqlStatement) throws Exception {
		StringBuilder statement = new StringBuilder(sqlStatement);
		
		Exp eR = TableConstant.create(statement);
		if (statement.length()==0) {
			return eR;
		}
		OperatorJoin opt = null;						
		if (Utils.indexOfString(statement, "JOIN") == 0) {
			statement = Utils.trim(statement.delete(0, 4 + 1));
			opt = new OperatorInnerJoin();
		} else if (Utils.indexOfString(statement, "LEFT JOIN") == 0) {
			statement = Utils.trim(statement.delete(0, 9 + 1));
			opt = new OperatorLeftJoin();
		} else if (Utils.indexOfString(statement, "RIGHT JOIN") == 0) {
			statement = Utils.trim(statement.delete(0, 10 + 1));
			opt = new OperatorRightJoin();
		} else if (Utils.indexOfString(statement, "FULL JOIN") == 0) {
			statement = Utils.trim(statement.delete(0, 9 + 1));
			opt = new OperatorFullJoin();
		} else if (Utils.indexOfString(statement, "INNER JOIN") == 0) {
			statement = Utils.trim(statement.delete(0, 10 + 1));
			opt = new OperatorInnerJoin();
		}
	
		ExpressionTree on = null;
		Exp eL = TableConstant.create(statement);
		
		if (opt==null) {
			//ket binh thuong ko dung join			
			opt = new OperatorInnerJoin();
		} else {
			statement = Utils.trim(statement.delete(0, 2));
			on = ExpressionTree.createWhere(statement.toString());
		}
		FromTree from = new FromTree(opt);
		from.childRight = eR;
		from.childLeft = eL;
		from.on = on;
		
		return from;
	}
	public QueryTable executeFrom(Database database) throws Exception {
		if (isOneChild()) {
			TableConstant table = (TableConstant)getOneChild();
			if (table==null) throw new Exception("from statement error");
			QueryTable q = new QueryTable(table, database);
			return q;
		}
		if (operator instanceof OperatorJoin) {
			QueryTable tableA = (getChildRight() instanceof FromTree) ? ((FromTree)getChildRight()).executeFrom(database) : new QueryTable((TableConstant)getChildRight(), database);
			QueryTable tableB = (getChildLeft() instanceof FromTree) ? ((FromTree)getChildLeft()).executeFrom(database) : new QueryTable((TableConstant)getChildLeft(), database);
			return tableA.executeOperator((OperatorJoin) operator, on, tableB);
		}
		throw new Exception("from statement error");
	}
}
