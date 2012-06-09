/**
 * 
 */
package jsql.parse;

import jsql.data.FloatType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class FloatConstant extends Constant {

	public FloatConstant(FloatType value) {
		super(FLOAT, value);
	}

	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof FloatConstant)) throw new Exception("compare is not suport!");
		return ((Type)getValue()).compareTo((Type) obj.getValue());
	}
	
	@Override
	public Constant cong(Constant obj) throws Exception {
		if (obj instanceof IntConstant) return new FloatConstant(new FloatType((Float)getBaseValue() + (Integer)obj.getBaseValue()));
		if (!(obj instanceof FloatConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new FloatConstant(new FloatType((Float)getBaseValue() + (Float)obj.getBaseValue()));
	}
	@Override
	public Constant tru(Constant obj) throws Exception {
		if (obj instanceof IntConstant) return new FloatConstant(new FloatType((Float)getBaseValue() - (Integer)obj.getBaseValue()));
		if (!(obj instanceof FloatConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new FloatConstant(new FloatType((Float)getBaseValue() - (Float)obj.getBaseValue()));
	}
	@Override
	public Constant nhan(Constant obj) throws Exception {
		if (obj instanceof IntConstant) return new FloatConstant(new FloatType((Float)getBaseValue() * (Integer)obj.getBaseValue()));
		if (!(obj instanceof FloatConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new FloatConstant(new FloatType((Float)getBaseValue() * (Float)obj.getBaseValue()));
	}
	@Override
	public Constant chia(Constant obj) throws Exception {
		if (obj instanceof IntConstant) return new FloatConstant(new FloatType((Float)getBaseValue() / (Integer)obj.getBaseValue()));
		if (!(obj instanceof FloatConstant)) throw new Exception("toan hang thu 2 ko phai la so!");
		return new FloatConstant(new FloatType((Float)getBaseValue() / (Float)obj.getBaseValue()));
	}
}
