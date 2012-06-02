/**
 * 
 */
package jsql.data;

import java.util.ArrayList;
import java.util.List;

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
		Database database = Database.loadFromFile("test.db");
		Statement statement = Parser.parseStatement("INSERT INTO Persons (P_Id, LastName, FirstName) VALUES (5, 'Tjessem', 'Jakob')");
		System.out.println("insert test.");
	}

	public static void generateDatabase() {
		Database database = new Database("test.db");
		
		Table table = new Table();
		table.setName("HocSinh");
		List<Column> listCol = new ArrayList<Column>();
		listCol.add(new Column("MS", "INT"));
		listCol.add(new Column("TEN", "STRING"));
		table.setColumns(listCol);
		List<Row> rows = new ArrayList<Row>();
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
