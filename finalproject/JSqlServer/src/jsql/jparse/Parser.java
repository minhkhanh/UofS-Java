/**
 * 
 */
package jsql.jparse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Vector;

/**
 * @author tmkhanh
 *
 */
/**
 * Parser: an SQL parser
 */
public class Parser {

	JJParser _parser = null;

	/**
	 * Create a new parser to parse SQL statements from a given input stream.
	 * 
	 * @param in
	 *            The InputStream from which SQL statements will be read.
	 */
	public Parser(InputStream in) {
		initParser(in);
	}

	/**
	 * Create a new parser: before use, call initParser(InputStream) to specify
	 * an input stream for the parsing.
	 */
	public Parser() {
	};

	/**
	 * Initialize (or re-initialize) the input stream for the parser.
	 */
	public void initParser(InputStream in) {
		if (_parser == null) {
			_parser = new JJParser(in);
		} else {
			_parser.ReInit(in);
		}
	}

	public void addCustomFunction(String fct, int nparm) {
		Utils.addCustomFunction(fct, nparm);
	}

	/**
	 * Parse an SQL Statement from the parser's input stream.
	 * 
	 * @return An SQL statement, or null if there's no more statement.
	 */
	public Statement readStatement() throws ParseException {
		if (_parser == null)
			throw new ParseException(
					"Parser not initialized: use initParser(InputStream);");
		return _parser.SQLStatement();
	}

	/**
	 * Parse a set of SQL Statements from the parser's input stream (all the
	 * available statements are parsed and returned).
	 * 
	 * @return A vector of ZStatement objects (SQL statements).
	 */
	public Vector readStatements() throws ParseException {
		if (_parser == null)
			throw new ParseException(
					"Parser not initialized: use initParser(InputStream);");
		return _parser.SQLStatements();
	}

	/**
	 * Parse an SQL Expression (like the WHERE clause of an SQL query).
	 * 
	 * @return An SQL expression.
	 */
	public Exp readExpression() throws ParseException {
		if (_parser == null)
			throw new ParseException(
					"Parser not initialized: use initParser(InputStream);");
		return _parser.SQLExpression();
	}

}
