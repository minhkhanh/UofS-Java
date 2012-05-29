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
		if (!file.exists()) return null;
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
		if (table==null) return;
		tables.add(table);
	}
}
