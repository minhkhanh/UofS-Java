/**
 * 
 */
package jsql.parse;

import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public abstract class NumberConstant extends Constant {

	public NumberConstant(int type, Type value) {
		super(type, value);
	}
}
