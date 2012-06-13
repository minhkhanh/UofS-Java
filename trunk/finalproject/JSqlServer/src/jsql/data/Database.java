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
import java.util.Vector;

import jsql.parse.ColumnConstant;
import jsql.parse.Delete;
import jsql.parse.Insert;
import jsql.parse.Select;
import jsql.parse.Statement;
import jsql.parse.Update;
import jsql.parse.UpdateChange;

/**
 * @author tmkhanh
 * 
 */
public class Database implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String INT = "INT";
	public static final String STRING = "STRING";
	public static final String FLOAT = "FLOAT";

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

	public boolean haveTable(String name) {
		for (Table table : tables) {
			if (table.getName().toUpperCase().equals(name.toUpperCase()))
				return true;
		}
		return false;
	}

	public Result executeStatement(Statement statement) {
		if (statement instanceof Insert)
			return executeInsert((Insert) statement);
		if (statement instanceof Delete)
			return executeDelete((Delete) statement);
		if (statement instanceof Update)
			return executeUpdate((Update) statement);
		if (statement instanceof Select)
			return executeSelect((Select) statement);
		return new Result("statement is dont suport!");
	}

	private Result executeSelect(Select select) {
		try {
			if (select == null)
				throw new Exception("select is null!");

			select.setDatabase(this);

			return select.executeSelect(null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Result(e.getMessage());
		}
	}

	private Result executeUpdate(Update update) {
		try {
			if (update == null)
				throw new Exception("update is null!");
			Table table = null;
			for (Table t : tables) {
				if (t.checkName(update.getTable())) {
					table = t;
					break;
				}
			}
			if (table == null)
				throw new Exception("table is not exit!");

			update.setDatabase(this);
			//int num = table.executeUpdate(update);
			int iCount = 0;
			QueryTable queryTable = new QueryTable(update.getTableConstant(),
					update.getDatabase());
			for (int i = 0; i < table.getRows().size(); ++i) {
				QueryRow queryRow = new QueryRow(table.getRows().get(i), queryTable.getColumns());
				if (update.getWhere() == null
						|| update.getWhere().filterByExpression(queryRow)) {
					Row row = new Row(table.getRows().get(i));
					for (UpdateChange change : update.getChange()) {
						ColumnConstant col = new ColumnConstant(null,
								(String) change.getColumn().getBaseValue());
						row.setDataAt(queryRow.getColumnIndex(col),
								change.getValue());
					}
					if (table.updateRow(i, row))
						++iCount;
				}
			}

			System.out.println("update " + iCount + " row done!");
			return new Result("update " + iCount + " row done!");
		} catch (Exception e) {
			System.out.println("update error! " + e.getMessage());
			return new Result("update error! " + e.getMessage());
		}
	}

	private Result executeDelete(Delete del) {
		try {
			if (del == null)
				throw new Exception("delete is null!");
			Table table = null;
			for (Table t : tables) {
				if (t.checkName(del.getTable())) {
					table = t;
					break;
				}
			}
			if (table == null)
				throw new Exception("table is not exit!");

			del.setDatabase(this);

			//int num = table.executeDelete(del);
			int iCount = 0;
			if (del.getWhere() == null) {
				iCount = table.getRows().size();
				table.getRows().clear();
			} else {
				QueryTable queryTable = new QueryTable(del.getTableConstant(),
						del.getDatabase());
				// RowInfo rowInfo = new RowInfo(columns);
				for (int i = 0; i < table.getRows().size();) {
					Row row = table.getRows().get(i);
					// rowInfo.setRow(row);
					QueryRow queryRow = new QueryRow(row, queryTable.getColumns());
					if (del.getWhere().filterByExpression(queryRow)) {
						table.removeRow(row);
						++iCount;
					} else
						++i;
				}
			}
			
			System.out.println("delete " + iCount + " row done!");
			return new Result("delete " + iCount + " row done!");
		} catch (Exception e) {
			System.out.println("delete error! " + e.getMessage());
			return new Result("delete error! " + e.getMessage());
		}
	}

	private Result executeInsert(Insert insert) {
		try {
			if (insert == null)
				throw new Exception("insert is null!");
			Table table = null;
			for (Table t : tables) {
				if (t.checkName(insert.getTable())) {
					table = t;
					break;
				}
			}
			if (table == null)
				throw new Exception("table is not exit!");
			// if (table.checkInsertInput(insert.getColumns(),
			// insert.getValues())) throw new
			// Exception("check input insert false");
			insert.setDatabase(this);
			
			
			Vector<String> columnsName = insert.getColumns();
			Vector<Type> values = insert.getValues();
			if (!table.checkInsertInput(columnsName, values))
				throw new Exception("check input insert false");
			if (columnsName.size() == 0) {
				table.addRow(new Row(values));
			} else {
				Row row = new Row(table.getColumns().size());
				for (int i = 0; i < columnsName.size(); i++) {
					int index = table.getColumnIndex(columnsName.get(i));
					if (index == -1)
						throw new Exception("column not exist");
					row.setDataAt(index, values.get(i));
				}
				table.addRow(row);
			}
			
			//table.executeInsert(insert);
			
			System.out.println("insert row done!");
			return new Result("insert row done!");
		} catch (Exception e) {
			System.out.println("insert error! " + e.getMessage());
			return new Result("insert error! " + e.getMessage());
		}
	}

	// added by Khuong
	public void DeleteTable(int idx) {
		tables.remove(idx);
	}

	// added by khuong
	public void DeleteTable(String tableName) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {

				System.out.println("delete  " + tables.get(i).getName());
				tables.remove(i);
				return;
			}
		}
	}

	// added by khuong
	public Table getTable(String tableName) {

		for (int i = 0; i < tables.size(); i++)
			if (tables.get(i).getName().toUpperCase().equals(tableName.toUpperCase()))
				return tables.get(i);
		return null;
	}

	// added by khuong
	public String GetFilePath() {
		return filePath;
	}

	// added by khuong
	public static void createNewDatabase(String fullpath) {
		Database db = new Database(fullpath);
		db.saveToFile();
	}
}
