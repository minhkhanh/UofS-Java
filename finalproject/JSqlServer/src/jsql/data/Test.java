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
		testSelect();
	}
	
	public static void testSelect() {
		Database database = new Database("test.db");
		
		Table table = new Table();
		table.setName("HocSinh");
		Vector<Column> listCol = new Vector<Column>();
		listCol.add(new Column("MS", Database.INT));
		listCol.add(new Column("TEN", Database.STRING));
		listCol.add(new Column("MALOP", Database.INT));
		table.setColumns(listCol);
		database.addTable(table);
		
		table = new Table();
		table.setName("Lop");
		listCol = new Vector<Column>();
		listCol.add(new Column("MALOP", Database.INT));
		listCol.add(new Column("TEN", Database.STRING));
		table.setColumns(listCol);
		database.addTable(table);
		
		Statement statement = Parser.parseStatement("INSERT INTO Lop VALUES (1, 'Lop 1')");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("INSERT INTO Lop VALUES (2, 'Lop 2')");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("INSERT INTO Lop VALUES (3, 'Lop 3')");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (1, 'Khanh', 1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (2, 'Minh', 1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (3, 'Tran', 2)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (4, 'ABC', 2)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (5, 'DEF', 2)");
		database.executeStatement(statement);
		
		//statement = Parser.parseStatement("SELECT Persons.LastName, SUM(Persons.FirstName), Orders.OrderNo FROM Persons RIGHT JOIN Orders ON Persons.P_Id=Orders.P_Id ORDER BY Persons.LastName WHERE LastName='Svendson' AND (FirstName='Tove' OR FirstName='Ola') GROUP BY Customer,OrderDate HAVING SUM(OrderPrice)>1500");
		
		//statement = Parser.parseStatement("SELECT HocSinh.Ten, Lop.Ten FROM HocSinh hs, Lop l where ( ( hs.MALOP=l.MALOP ) and ( HocSinh.MS > Any(2, 3) ) )");
		//statement = Parser.parseStatement("SELECT * FROM Lop l where (select count(*) from HocSinh hs group by hs.MALOP where hs.MALOP=l.MALOP)>=2");
		statement = Parser.parseStatement("SELECT * FROM Lop l where l.MALOP not in (select MALOP from HocSinh)");
		
		//statement = Parser.parseStatement("SELECT MALOP, count(*) FROM HocSinh group by MALOP order by MALOP DESC");
		//statement = Parser.parseStatement("SELECT HocSinh.Ten, Lop.Ten FROM HocSinh group by MALOP having avg(MS)>=3");
		
		Result r = database.executeStatement(statement);
		database.saveToFile();
		//System.out.println("delete test.");
	}
	
	public static void testUpdate() {
		generateDatabase();
		Database database = Database.loadFromFile("test.db");
		//Statement statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (5, 'Tjessem')");
		//database.executeStatement(statement);
		
		Statement statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (1, 'Khanh',1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (2, 'Minh', 1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (3, 'Tran', 1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (4, 'ABC', 2)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (5, 'DEF', 2)");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("UPDATE HocSinh SET TEN='Nissestien 67'");
		//statement = Parser.parseStatement("DELETE FROM HocSinh");
		database.executeStatement(statement);
		//database.saveToFile();
		System.out.println("delete test.");
	}
	
	public static void testDelete() {
		generateDatabase();
		Database database = Database.loadFromFile("test.db");
		
		Statement statement = Parser.parseStatement("INSERT INTO LOP VALUES (1, 'Lop 1')");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("INSERT INTO LOP VALUES (2, 'Lop 2')");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (1, 'Khanh', 1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (2, 'Minh', 1)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (3, 'Tran', 2)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (4, 'ABC', 2)");
		database.executeStatement(statement);
		statement = Parser.parseStatement("INSERT INTO HocSinh VALUES (5, 'DEF', 2)");
		database.executeStatement(statement);
		
		statement = Parser.parseStatement("DELETE FROM HocSinh WHERE TEN='Tran' AND MS=3");
		//statement = Parser.parseStatement("DELETE FROM HocSinh");
		database.executeStatement(statement);
		//database.saveToFile();
		System.out.println("delete test.");
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
		listCol.add(new Column("MS", Database.INT));
		listCol.add(new Column("TEN", Database.STRING));
		listCol.add(new Column("MALOP", Database.INT));
		table.setColumns(listCol);
		database.addTable(table);
		
		table = new Table();
		table.setName("Lop");
		listCol = new Vector<Column>();
		listCol.add(new Column("MALOP", Database.INT));
		listCol.add(new Column("TEN", Database.STRING));
		table.setColumns(listCol);
		database.addTable(table);
		
		database.saveToFile();
	}
	

}
