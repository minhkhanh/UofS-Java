/**
 * 
 */
package jsql.parse;

import jsql.data.BooleanType;
import jsql.data.StringType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class StringConstant extends Constant {

	protected StringConstant(StringType value) {
		super(STRING, value);
	}
	
	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof StringConstant)) throw new Exception("compare is not suport!");
		return ((Type)getValue()).compareTo((Type) obj.getValue());
	}
	
	public BooleanConstant like(StringConstant strCon) {
		boolean b = like((String)getBaseValue(), (String)strCon.getBaseValue());
		return new BooleanConstant(new BooleanType(b));
	}
	
	public static boolean like(String str, String expr) {
	    //expr = expr.toLowerCase(); 
	    expr = expr.replace(".", "\\."); 
	    expr = expr.replace("?", ".");
	    expr = expr.replace("%", ".*");
	    //str = str.toLowerCase();
	    return str.matches(expr);
	}
}
