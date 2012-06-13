/**
 * 
 */
package jsql.parse;

import jsql.data.StringType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class UpdateChange {
	private ColumnConstant column;
	private Type value;
	private UpdateChange() {
		
	}
	public static UpdateChange create(String data) throws Exception {
		int j = data.indexOf('=');
		String col = data.substring(0, j).trim();
		UpdateChange update = new UpdateChange();
		update.column = new ColumnConstant(new StringType(col));
		data = data.substring(j+1).trim();
		
		update.value = Type.createType(data);
		//toan hang
//		if (Utils.indexOfString(data, "'")==0) {
//			int i = Utils.indexOfString(data.substring(1), "'");
//			if (i<0) throw new Exception("split false!");
//			i++; // bu vao phan da tru ra
//			String tmp = data.substring(1, i);
//			update.value = new StringType(tmp);
//		} else update.value = new IntType(Integer.parseInt(data));
		
		return update;
	}
	public Type getValue() {
		return value;
	}
	public ColumnConstant getColumn() {
		return column;
	}
}
