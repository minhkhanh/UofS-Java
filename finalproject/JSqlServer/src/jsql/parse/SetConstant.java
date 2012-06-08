/**
 * 
 */
package jsql.parse;

import jsql.data.BooleanType;
import jsql.data.SetType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class SetConstant extends Constant {

	protected SetConstant(SetType value) {
		super(SET, value);
	}
	
	protected SetConstant(String value) {
		super(SET, value);
		//xu ly chuoi tap hop
	}

	public void executeSubQuery() {
		
	}
		
	public BooleanConstant contain(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(((SetType)getValue()).contain((Type) obj.getValue())));
	}
	
	public BooleanConstant add(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(((SetType)getValue()).add((Type) obj.getValue())));
	}
	
	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof IntConstant)) throw new Exception("compare is not suport!");
		return ((Type)getValue()).compareTo((Type) obj.getValue());
	}
}
