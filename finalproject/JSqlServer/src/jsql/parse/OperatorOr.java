/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorOr extends OperatorLogic {

	@Override
	public int getSoNgoi() {
		return 2;
	}

	@Override
	public String getKyHieu() {
		return "OR";
	}

}
