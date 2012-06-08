/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class GroupByItem {
	private ColumnConstant column;
	private String alias; //alias cua column
	public GroupByItem(String data) throws Exception {
		int i=-1;
		if ((i = Utils.indexOfString(data, "AS "))>0 && !Utils.isWordChar(data.charAt(i-1))) {
			if (i==0) throw new Exception("column sai syntax");
			String col = data.substring(0, i).trim();
			data = data.substring(i + 2).trim();
			if (data.length()==0) throw new Exception("column sai syntax");
			alias = data;
			data = col;
		}
		Exp exp = ExpressionTree.createExp(data);
		if (!(exp instanceof ColumnConstant)) throw new Exception("group by sai syntax");
		column = (ColumnConstant) exp;
	}
	

	public String getAlias() {
		return alias;
	}

	public ColumnConstant getColumn() {
		return column;
	}

}
