/**
 * 
 */
package jsql.data;

import java.util.Vector;

import jsql.parse.Parser;
import jsql.parse.Statement;

/**
 * @author tmkhanh
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testInsert();
	}
	
	public static void testInsert() {
		//generateDatabase();
		Database database = Database.loadFromFile("test.db");
		Statement statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (5, 'Tjessem')");
		database.executeStatement(statement);
		database.saveToFile();
		System.out.println("insert test.");
	}

	public static void generateDatabase() {
		Database database = new Database("test.db");
		
		Table table = new Table();
		table.setName("HocSinh");
		Vector<Column> listCol = new Vector<Column>();
		listCol.add(new Column("MS", "INT"));
		listCol.add(new Column("TEN", "STRING"));
		table.setColumns(listCol);
		Vector<Row> rows = new Vector<Row>();
		Row row = new Row();
		row.addData(new IntType(1));
		row.addData(new StringType("Khánh"));
		rows.add(row);
		row = new Row();
		row.addData(new IntType(2));
		row.addData(new StringType("Trần"));
		rows.add(row);
		table.setRows(rows);
		database.addTable(table);
		
		database.saveToFile();
	}
}
