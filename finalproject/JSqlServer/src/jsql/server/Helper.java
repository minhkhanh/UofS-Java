package jsql.server;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;

import jsql.data.Database;
import jsql.data.Table;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Helper implements Serializable {

	// kiểm tra chuỗi tring tên fieldname, talbename hợp lệ không
	public static Boolean CheckFieldAndTableName(String str) {

		// các ký tự hợp lệ để đặt tên abc...z ABC...Z 1234 ký tự đầu là chữ
		// nếu đầu hoặc cuối có khoảng trắng thì chuỗi chỉ là phần còn lại

		// xóa khoảng trắng đầu và cuối
		str = str.trim();

		if (!((str.charAt(0) >= 'a' && str.charAt(0) <= 'z') || (str.charAt(0) >= 'A' && str
				.charAt(0) <= 'Z'))) {
			return false;
		}

		for (int i = 1; i < str.length(); i++) {
			if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9')
					|| (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str
					.charAt(i) >= 'A' && str.charAt(i) <= 'Z'))) {
				return false;
			}
		}

		return true;
	}

	// Load Image
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Main.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// de load vao combobox
	public static String[] GetListTableName(Database db) {

		if (db == null)
			return null;

		List<Table> tables = db.getTables();
		String[] obj = new String[tables.size()];

		for (int i = 0; i < tables.size(); i++) {
			obj[i] = tables.get(i).getName();
		}

		return obj;
	}

	// de load vao header table
	public static Vector<String> GetListFiledAndType(Table table) {

		if (table == null)
			return null;
		Vector<String> obj = new Vector<String>();

		for (int i = 0; i < table.getColumns().size(); i++) {
			obj.add(table.getColumn(i).getName() + " ("
					+ table.getColumn(i).getType() + ")");
		}

		return obj;
	}

	// de load vao table
	public static Vector<Vector<Object>> GetValues(Table table) {

		if (table == null)
			return null;

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

	// tao row empty de add vao table de thay doi data theo y minh
	public static Vector<Vector<Object>> CreateRowEmpy(Table table) {

		if (table == null)
			return null;

		Vector<Vector<Object>> obj = new Vector<Vector<Object>>();
		Vector<Object> tRow;
		int nCol;

		nCol = table.getColumns().size();

		tRow = new Vector<Object>();

		for (int j = 0; j < nCol; j++) {
			tRow.add("");
		}

		obj.add(tRow);

		return obj;
	}
}
