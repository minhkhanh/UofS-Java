/**
 * 
 */
package jsql.parse;

import jsql.data.StringType;

/**
 * @author tmkhanh
 *
 */
public class ColumnConstant extends Constant {

	private String columnName;
	private String alias = null;
	private boolean distinct = false;
	protected ColumnConstant(StringType columnName) {
		super(COLUMNNAME, columnName);
		String data = (String) columnName.getValue();
		int i=-1;
		if ((i = data.indexOf('.'))>0) {
			this.columnName = data.substring(i + 1);
			alias = data.substring(0, i);
		} else this.columnName = data;
	}
	public ColumnConstant(String alias, String columnName) {
		super(COLUMNNAME, columnName);
		this.alias = alias;
		this.columnName = columnName;
	}
	@Override
	public int compareTo(Constant obj) throws Exception {
		throw new Exception("compare is not suport!");
	}
	public String getColumnName() {
		return columnName;
	}
	public String getAlias() {
		return alias;
	}
	public void setDistinct() {
		distinct = true;
	}
	public boolean isDistinct() {
		return distinct;
	}
	public boolean isWildcard() {
		return columnName.equals("*");
	}
	
	@Override
	public boolean equals(Object obj) {
		ColumnConstant col = (ColumnConstant)obj;
		if (col.alias==null) return columnName.toUpperCase().equals(col.columnName.toUpperCase()); 
		return columnName.toUpperCase().equals(col.columnName.toUpperCase()) && alias.toUpperCase().equals(col.alias.toUpperCase());
	}
	
	@Override
	public String toString() {
		if (alias==null) return columnName;
		return alias + "." + columnName;
	}
}
