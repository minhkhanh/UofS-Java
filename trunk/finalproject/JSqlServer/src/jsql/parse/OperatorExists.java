/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorExists extends OperatorSet {
	@Override
	public String getKyHieu() {
		return "EXISTS";
	}
	@Override
	public int getSoNgoi() {
		return 1;
	}
}
