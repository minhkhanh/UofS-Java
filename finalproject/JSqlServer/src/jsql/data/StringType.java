/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class StringType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringType(String str) {
		super(str);
	}

	@Override
	public int compareTo(Type o) {
		if (o.getClass()!=this.getClass()) return 0;
		return ((String)getValue()).compareTo((String)o.getValue());
	}
}
