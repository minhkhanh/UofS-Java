/**
 * 
 */
package jsql.parse;

import jsql.data.BooleanType;
import jsql.data.FloatType;
import jsql.data.IntType;
import jsql.data.NullType;
import jsql.data.StringType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public abstract class Constant implements Exp, MyComparable {
	public static final int COLUMNNAME = 0;
	public static final int NULL = 1;
	public static final int INT = 2;
	public static final int STRING = 3;
	public static final int SET = 4;
	public static final int BOOLEAN = 5;
	public static final int TABLE = 6;
	public static final int FLOAT = 7;
	public static final int EXP = 8;
	
	private int type = -1;
	private Object value;
	
	protected Constant(int type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	public static Constant create(Type value) throws Exception {
		if (value instanceof IntType) return new IntConstant((IntType) value);
		if (value instanceof BooleanType) return new BooleanConstant((BooleanType) value);
		if (value instanceof FloatType) return new FloatConstant((FloatType) value);
		if (value instanceof StringType) return new StringConstant((StringType) value);
		if (value instanceof NullType) return new NullConstant();
		throw new Exception("type in not suport!");
		//return null;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public Object getBaseValue() {
		return (((Type)value).getValue());
	}
//	public void setValue(Object value) {
//		this.value = value;
//	}
	@Override
	public boolean equals(Object obj) {
		return value.equals(obj);
	}
	
	public BooleanConstant bang(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(compareTo(obj)==0));
	}
	
	public BooleanConstant khac(Constant obj) throws Exception {
		return bang(obj).not();
	}
	
	public BooleanConstant lon(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(compareTo(obj)>0));
	}
	
	public BooleanConstant nho(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(compareTo(obj)<0));
	}
	public BooleanConstant lonbang(Constant obj) throws Exception {
		return lon(obj).or(bang(obj));
	}
	public BooleanConstant nhobang(Constant obj) throws Exception {
		return nho(obj).or(bang(obj));
	}
	public Constant cong(Constant obj) throws Exception {
		throw new Exception("operator in not suport!");
	}
	public Constant tru(Constant obj) throws Exception {
		throw new Exception("operator in not suport!");
	}
	public Constant nhan(Constant obj) throws Exception {
		throw new Exception("operator in not suport!");
	}
	public Constant chia(Constant obj) throws Exception {
		throw new Exception("operator in not suport!");
	}
	
}
