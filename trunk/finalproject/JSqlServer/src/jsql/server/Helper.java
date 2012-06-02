package jsql.server;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import jsql.data.Database;
import jsql.data.Table;
import jsql.data.Type;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Helper implements Serializable {

	// de load vao combobox
	public static String[] GetListTableName(Database db) {

		List<Table> tables = db.getTables();
		String[] obj = new String[tables.size()];

		for (int i = 0; i < tables.size(); i++) {
			obj[i] = tables.get(i).getName();
		}

		return obj;
	}

	// de load vao table
	public static Vector<String> GetListFiledName(Table table) {

		Vector<String> obj = new Vector<>();

		for (int i = 0; i < table.getColumns().size(); i++) {
			obj.add(table.getColumn(i).getName());
		}

		return obj;
	}

	// de load vao table
	public static Vector<Vector<Type>> GetValues(Table table) {

		Vector<Vector<Type>> obj = new Vector<>();
		int nCol = table.getColumns().size();

		//for (int i = 0; i < table.getRows().size(); i++) {
		//	obj.add(table.getRow(i).getData());
		//}

		return obj;
	}
}
