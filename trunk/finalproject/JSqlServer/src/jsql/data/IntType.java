/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class IntType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntType(Integer _int) {
		super(_int);
	}

	@Override
	public int compareTo(Type o) {
		if (o.getClass()!=this.getClass()) return 0;
		return ((Integer)getValue()).compareTo((Integer)o.getValue());
	}
	

}
