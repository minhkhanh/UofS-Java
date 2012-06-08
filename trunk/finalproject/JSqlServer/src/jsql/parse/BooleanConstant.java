/**
 * 
 */
package jsql.parse;

import jsql.data.BooleanType;

/**
 * @author tmkhanh
 *
 */
public class BooleanConstant extends Constant {

	protected BooleanConstant(BooleanType value) {
		super(BOOLEAN, value);
	}

	public BooleanConstant and(BooleanConstant b) {
		return new BooleanConstant(new BooleanType((Boolean)getBaseValue() && (Boolean)b.getBaseValue()));
	}
	
	public BooleanConstant or(BooleanConstant b) {
		return new BooleanConstant(new BooleanType((Boolean)getBaseValue() || (Boolean)b.getBaseValue()));
	}
	
	public BooleanConstant not() {
		return new BooleanConstant(new BooleanType(!(Boolean)getBaseValue()));
	}

	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof BooleanConstant)) throw new Exception("compare is not suport!");
		return (Boolean)getBaseValue() == (Boolean)obj.getBaseValue() ? 0: 1;
	}
	
	public boolean is() {
		return (Boolean)getBaseValue();
	}
}
