/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class TableConstant extends Constant {
	protected TableConstant() {
		super(TABLE, null);
		// TODO Auto-generated constructor stub
	}
	private String name = null;
	private String alias = null;

	@Override
	public int compareTo(Constant obj) throws Exception {
		return 0;
	}
	public static Exp create(StringBuilder statement) throws Exception {
		int iCount = 1;
		if (Utils.indexOfString(statement, "(")==0) {
			for (int i = 1; i < statement.length(); i++) {
				if (statement.charAt(i)=='(') iCount++;
				if (statement.charAt(i)==')') iCount--;
				if (iCount==0) {
					String subIn = statement.substring(1, i).trim();
					statement = Utils.trim(statement.delete(0, i + 1));
					return FromTree.createFrom(subIn);
				}
			}
		}
		statement = Utils.trim(statement);
		int iS = Utils.indexOfString(statement, " ");
		boolean bPhay = false;
		if ((iS>Utils.indexOfString(statement, ",") && Utils.indexOfString(statement, ",")>=0)||(iS==-1)) {
			iS = Utils.indexOfString(statement, ",");
			bPhay = iS!=-1;
		}
		if (iS==-1) iS = statement.length();
		String name = statement.substring(0, iS);
		statement = Utils.trim(statement.delete(0, iS + 1));
		
		TableConstant t = new TableConstant();
		t.name = name;
		if (statement.length()==0) return t;
		

		if (!bPhay && Utils.indexOfString(statement, "ON")!=0 
				&& Utils.indexOfString(statement, "JOIN")!=0
				&& Utils.indexOfString(statement, "LEFT JOIN")!=0
				&& Utils.indexOfString(statement, "RIGHT JOIN")!=0
				&& Utils.indexOfString(statement, "FULL JOIN")!=0
				&& Utils.indexOfString(statement, "INNER JOIN")!=0) {
			int iA = Utils.indexOfString(statement, " ");
			t.alias = statement.substring(0, iA);
			statement = Utils.trim(statement.delete(0, iA + 1));
		}
		return t;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
