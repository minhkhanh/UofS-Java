/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorAny extends OperatorSet {
	@Override
	public String getKyHieu() {
		return "ANY";
	}
	@Override
	public int getSoNgoi() {
		return 1;
	}
}
