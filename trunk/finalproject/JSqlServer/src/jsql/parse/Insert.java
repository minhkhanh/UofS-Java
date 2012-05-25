package jsql.parse;

import java.util.Vector;

/**
 * Insert: an SQL INSERT statement
 */
public class Insert implements Statement {

	String table_;
	Vector columns_ = null;
	Exp valueSpec_ = null;

	/**
	 * Create an INSERT statement on a given table
	 */
	public Insert(String tab) {
		table_ = new String(tab);
	}

	/**
	 * Get the name of the table involved in the INSERT statement.
	 * 
	 * @return A String equal to the table name
	 */
	public String getTable() {
		return table_;
	}

	/**
	 * Get the columns involved in the INSERT statement.
	 * 
	 * @return A Vector of Strings equal to the column names
	 */
	public Vector getColumns() {
		return columns_;
	}

	/**
	 * Specify which columns to insert
	 * 
	 * @param c
	 *            A vector of column names (Strings)
	 */
	public void addColumns(Vector c) {
		columns_ = c;
	}

	/**
	 * Specify the VALUES part or SQL sub-query of the INSERT statement
	 * 
	 * @param e
	 *            An SQL expression or a SELECT statement. If it is a list of
	 *            SQL expressions, e should be represented by ONE SQL expression
	 *            with operator = "," and operands = the expressions in the
	 *            list. If it is a SELECT statement, e should be a Query
	 *            object.
	 */
	public void addValueSpec(Exp e) {
		valueSpec_ = e;
	}

	/**
	 * Get the VALUES part of the INSERT statement
	 * 
	 * @return A vector of SQL Expressions (Exp objects); If there's no VALUES
	 *         but a subquery, returns null (use getQuery() method).
	 */
	public Vector getValues() {
		if (!(valueSpec_ instanceof Expression))
			return null;
		return ((Expression) valueSpec_).getOperands();
	}

	/**
	 * Get the sub-query (ex. in INSERT INTO table1 SELECT * FROM table2;, the
	 * sub-query is SELECT * FROM table2;)
	 * 
	 * @return A Query object (A SELECT statement), or null if there's no
	 *         sub-query (in that case, use the getValues() method to get the
	 *         VALUES part).
	 */
	public Query getQuery() {
		if (!(valueSpec_ instanceof Query))
			return null;
		return (Query) valueSpec_;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("insert into " + table_);
		if (columns_ != null && columns_.size() > 0) {
			// buf.append(" " + columns_.toString());
			buf.append("(" + columns_.elementAt(0));
			for (int i = 1; i < columns_.size(); i++) {
				buf.append("," + columns_.elementAt(i));
			}
			buf.append(")");
		}

		String vlist = valueSpec_.toString();
		buf.append(" ");
		if (getValues() != null)
			buf.append("values ");
		if (vlist.startsWith("("))
			buf.append(vlist);
		else
			buf.append(" (" + vlist + ")");

		return buf.toString();
	}
}
