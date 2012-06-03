/**
 * 
 */
package jsql.execute;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Vector;

import jsql.jparse.Expression;
import jsql.jparse.FromItem;
import jsql.jparse.Insert;
import jsql.jparse.Parser;
import jsql.jparse.Query;
import jsql.jparse.SelectItem;
import jsql.jparse.Statement;

/**
 * @author tmkhanh
 * 
 */
public class Test {

	public static void main(String args[]) {
		try {

			Parser p = null;

			if (args.length < 1) {
				System.out
						.println("Reading SQL from stdin (quit; or exit; to quit)");
				p = new Parser(System.in);
			} else {
				p = new Parser(
						new DataInputStream(new FileInputStream(args[0])));
			}

			// Read all SQL statements from input
			Statement st;
			while ((st = p.readStatement()) != null) {

				System.out.println(st.toString()); // Display the statement

				if (st instanceof Query) { // An SQL query: query the DB
					queryDB((Query) st);
				} else if (st instanceof Insert) { // An SQL insert
					insertDB((Insert) st);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Query the database
	 */
	static void queryDB(Query q) throws Exception {

		Vector sel = q.getSelect(); // SELECT part of the query
		Vector from = q.getFrom(); // FROM part of the query
		Expression where = (Expression) q.getWhere(); // WHERE part of the
														// query

		if (from.size() > 1) {
			throw new SQLException("Joins are not supported");
		}

		// Retrieve the table name in the FROM clause
		FromItem table = (FromItem) from.elementAt(0);

		// We suppose the data is in a text file called <tableName>.db
		// <tableName> is the table name in the FROM clause
		BufferedReader db = new BufferedReader(new FileReader(table.getTable()
				+ ".db"));

		// Read the column names (the 1st line of the .db file)
		Tuple tuple = new Tuple(db.readLine());

		Eval evaluator = new Eval();

		// Now, each line in the .db file is a tuple
		String tpl;
		while ((tpl = db.readLine()) != null) {

			tuple.setRow(tpl);

			// Evaluate the WHERE expression for the current tuple
			// Display the tuple if the condition evaluates to true

			if (where == null || evaluator.eval(tuple, where)) {
				DisplayTuple(tuple, sel);
			}

		}

		db.close();
	}

	/**
	 * Display a tuple, according to a SELECT map
	 */
	static void DisplayTuple(Tuple tuple, Vector map) throws Exception {

		// If it is a "select *", display the whole tuple
		if (((SelectItem) map.elementAt(0)).isWildcard()) {
			System.out.println(tuple.toString());
			return;
		}

		Eval evaluator = new Eval();

		// Evaluate the value of each select item
		for (int i = 0; i < map.size(); i++) {

			SelectItem item = (SelectItem) map.elementAt(i);
			System.out.print(evaluator
					.evalExpValue(tuple, item.getExpression()).toString());

			if (i == map.size() - 1)
				System.out.println("");
			else
				System.out.print(", ");
		}
	}

	static void insertDB(Insert ins) throws Exception {
		System.out.println("Should implement INSERT here");
		System.out.println(ins.toString());
	}

}
