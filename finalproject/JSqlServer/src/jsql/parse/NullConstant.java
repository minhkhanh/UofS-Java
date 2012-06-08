/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class NullConstant extends Constant {

	protected NullConstant() {
		super(NULL, null);
	}
	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof NullConstant)) throw new Exception("compare is not suport!");
		return 0;
	}
}
