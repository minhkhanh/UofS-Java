/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class SelectItem {
	private Exp exp = null; //cong thuc trung binh ;	
	private String alias = null; //alias cua column
	public SelectItem(String data) throws Exception {
		int i=-1;
		if ((i = Utils.indexOfString(data, "AS "))>0 && !Utils.isWordChar(data.charAt(i-1))) {
			if (i==0) throw new Exception("column sai syntax");
			String col = data.substring(0, i).trim();
			data = data.substring(i + 2).trim();
			if (data.length()==0) throw new Exception("column sai syntax");
			alias = data;
			data = col;
		}
		exp = ExpressionTree.createExp(data);
	}
	
	public ExpressionTree getExpression() {
		return (ExpressionTree) exp;
	}
	
	public Exp getValue() {
		return exp;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public boolean isColumnConstant() {
		return exp instanceof ColumnConstant;
	}
	
	public boolean isColumnFunction() {
		return exp instanceof ExpressionTree;
	}
}
