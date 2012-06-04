/**
 * 
 */
package jsql.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jsql.parse.Insert;
import jsql.parse.Statement;

/**
 * @author tmkhanh
 * 
 */
public class Database implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Table> tables;

	private transient String filePath;

	public Database(String filePath) {
		tables = new ArrayList<Table>();
		this.filePath = filePath;
	}

	public static Database loadFromFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return null;
		try {
			FileInputStream fos = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fos);
			Database database = (Database) in.readObject();
			database.filePath = filePath;
			in.close();
			System.out.println("read database: " + filePath);
			return database;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveToFile() {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			System.out.println("save database: " + filePath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public void addTable(Table table) {
		if (table == null)
			return;
		tables.add(table);
		this.saveToFile();
	}

	public Table getTable(int index) {
		return tables.get(index);
	}

	public Result executeStatement(Statement statement) {
		if (statement instanceof Insert)
			return executeInsert((Insert) statement);
		return new Result("statement is dont suport!");
	}

	private Result executeInsert(Insert insert) {
		try {
			if (insert == null)
				throw new Exception("insert is null!");
			Table table = null;
			for (Table t : tables) {
				if (t.getName().equals(insert.getTable())) {
					table = t;
					break;
				}
			}
			if (table == null)
				throw new Exception("table is not exit!");
			// if (table.checkInsertInput(insert.getColumns(),
			// insert.getValues())) throw new
			// Exception("check input insert false");
			table.insertRow(insert.getColumns(), insert.getValues());
			return new Result("insert row done!");
		} catch (Exception e) {
			e.printStackTrace();
			// return new Result("insert error!");
		}
		return new Result("insert error!");
	}

	// added by Khuong
	public void DeleteTable(int idx) {
		tables.remove(idx);
		this.saveToFile();
	}

	public void DeleteTable(String tableName) {
		for (int i = 0; i < tables.size(); i++)
			if (tables.get(i).getName().equals(tableName))
				tables.remove(i);
		this.saveToFile();
	}

	public Table getTable(String tableName) {

		for (int i = 0; i < tables.size(); i++)
			if (tables.get(i).getName().equals(tableName))
				return tables.get(i);
		return null;
	}

	public String GetFilePath() {
		return filePath;
	}

	// added by khuong
	public static void createNewDatabase(String fullpath) {
		Database db = new Database(fullpath);
		db.saveToFile();
	}
}
