/**
 * 
 */
package jsql.data;

import java.util.ArrayList;
import java.util.List;

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
		generateDatabase();
	}

	private static void generateDatabase() {
		Database database = new Database("test.db");
		
		Table table = new Table();
		table.setName("HocSinh");
		List<Column> listCol = new ArrayList<Column>();
		listCol.add(new Column("MS", "INT"));
		listCol.add(new Column("TEN", "STRING"));
		table.setColumns(listCol);
		List<Row> rows = new ArrayList<Row>();
		Row row = new Row();
		row.addData(1);
		row.addData("Khánh");
		rows.add(row);
		row = new Row();
		row.addData(2);
		row.addData("Trần");
		rows.add(row);
		table.setRows(rows);
		database.addTable(table);
		
		database.saveToFile();
	}
}
