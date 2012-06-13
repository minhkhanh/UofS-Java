/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class NullType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullType() {
		super(null);
	}

	@Override
	public int compareTo(Type o) {
		return 0;
	}
	

}
