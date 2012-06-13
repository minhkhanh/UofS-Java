/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class BooleanType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BooleanType(Boolean b) {
		super(b);
	}

	@Override
	public int compareTo(Type o) {
		if (o.getClass()!=this.getClass()) return 0;
		return ((Boolean)getValue()).compareTo((Boolean)o.getValue());
	}

}
