/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
/**
 * ZConstant: a representation of SQL constants
 */
public class Constant implements Exp {

	/**
	 * ZConstant types
	 */
	public static final int UNKNOWN = -1;
	public static final int COLUMNNAME = 0;
	public static final int NULL = 1;
	public static final int NUMBER = 2;
	public static final int STRING = 3;

	int type_ = Constant.UNKNOWN;
	String val_ = null;

	/**
	 * Create a new constant, given its name and type.
	 */
	public Constant(String v, int typ) {
		val_ = new String(v);
		type_ = typ;
	}

	/*
	 * @return the constant value
	 */
	public String getValue() {
		return val_;
	}

	/*
	 * @return the constant type
	 */
	public int getType() {
		return type_;
	}

	public String toString() {
		if (type_ == STRING)
			return '\'' + val_ + '\'';
		else
			return val_;
	}
}