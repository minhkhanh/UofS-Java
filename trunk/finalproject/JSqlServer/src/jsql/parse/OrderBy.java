/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OrderBy {
	private boolean asc = true;
	private Exp exp;
	
	public OrderBy(String data) throws Exception {
		int i = Utils.indexOfString(data, " ASC");
		if (i>0) data = data.substring(0, i).trim();
		else {
			i = Utils.indexOfString(data, " DESC");
			asc = false;
		}
		if (i>0) data = data.substring(0, i).trim();
		if (data.length()==0) throw new Exception("loi syntax order by");
		exp = ExpressionTree.createExp(data);
	}

	public boolean isAsc() {
		return asc;
	}
	
	public Exp getExp() {
		return exp;
	}
}
