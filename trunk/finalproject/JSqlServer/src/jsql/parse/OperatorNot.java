/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorNot extends OperatorLogic {

	@Override
	public int getSoNgoi() {
		return 1;
	}

	@Override
	public String getKyHieu() {
		return "NOT";
	}

}
