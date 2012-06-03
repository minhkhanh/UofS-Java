package jsql.server;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import jsql.data.Database;
import jsql.data.Table;

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

	// de load vao header table
	public static Vector<String> GetListFiledName(Table table) {

		Vector<String> obj = new Vector<String>();

		for (int i = 0; i < table.getColumns().size(); i++) {
			obj.add(table.getColumn(i).getName());
		}

		return obj;
	}

	// de load vao table
	public static Vector<Vector<Object>> GetValues(Table table) {

		Vector<Vector<Object>> obj = new Vector<Vector<Object>>();
		Vector<Object> tRow;
		int nCol;
		int nRow;

		nCol = table.getColumns().size();
		nRow = table.getRows().size();

		for (int i = 0; i < nRow; i++) {
			
			tRow = new Vector<Object>();
			
			for (int j = 0; j < nCol; j++) {
				tRow.add(table.getRow(i).getDataAt(j).getValue());
			}
			
			obj.add(tRow);
		}

		return obj;
	}
}
