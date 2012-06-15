/**
 * 
 */
package jsql.parse;

import jsql.data.FloatType;
import jsql.data.IntType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class IntConstant extends NumberConstant {

	public IntConstant(IntType value) {
		super(INT, value);
	}

	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof IntConstant)) throw new Exception("compare is not suport!");
		return ((Type)getValue()).compareTo((Type) obj.getValue());
	}
	
	@Override
	public Constant cong(Constant obj) throws Exception {
		if (obj instanceof FloatConstant) return new FloatConstant(new FloatType((Integer)getBaseValue() + (Float)obj.getBaseValue()));
		if (!(obj instanceof IntConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new IntConstant(new IntType((Integer)getBaseValue() + (Integer)obj.getBaseValue()));
	}
	@Override
	public Constant tru(Constant obj) throws Exception {
		if (obj instanceof FloatConstant) return new FloatConstant(new FloatType((Integer)getBaseValue() - (Float)obj.getBaseValue()));
		if (!(obj instanceof IntConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new IntConstant(new IntType((Integer)getBaseValue() - (Integer)obj.getBaseValue()));
	}
	@Override
	public Constant nhan(Constant obj) throws Exception {
		if (obj instanceof FloatConstant) return new FloatConstant(new FloatType((Integer)getBaseValue() * (Float)obj.getBaseValue()));
		if (!(obj instanceof IntConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new IntConstant(new IntType((Integer)getBaseValue() * (Integer)obj.getBaseValue()));
	}
	@Override
	public Constant chia(Constant obj) throws Exception {
		if (obj instanceof FloatConstant) return new FloatConstant(new FloatType(((Integer)getBaseValue()).floatValue() / (Float)obj.getBaseValue()));
		if (!(obj instanceof IntConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new FloatConstant(new FloatType(((Integer)getBaseValue()).floatValue() / (Integer)obj.getBaseValue()));
	}
}
