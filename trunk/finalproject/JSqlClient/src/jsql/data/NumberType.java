/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public abstract class NumberType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberType(Object obj) {
		super(obj);
	}
	
	public abstract NumberType cong(NumberType num);

}
