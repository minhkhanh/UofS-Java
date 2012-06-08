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
public class StringConstant extends Constant {

	protected StringConstant(StringType value) {
		super(STRING, value);
	}
	
	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof StringConstant)) throw new Exception("compare is not suport!");
		return ((Type)getValue()).compareTo((Type) obj.getValue());
	}
}
