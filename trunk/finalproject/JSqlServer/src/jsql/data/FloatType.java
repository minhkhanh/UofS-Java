/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class FloatType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FloatType(Float f) {
		super(f);
	}

	@Override
	public int compareTo(Type o) {
		if (o.getClass()!=IntType.class) return ((Float)getValue()).compareTo(((Integer)o.getValue()).floatValue());;
		if (o.getClass()!=this.getClass()) return 0;
		return ((Float)getValue()).compareTo((Float)o.getValue());
	}
	

}
