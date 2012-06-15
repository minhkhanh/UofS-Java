/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorAll extends OperatorSet {
	@Override
	public String getKyHieu() {
		return "ALL";
	}
	@Override
	public int getSoNgoi() {
		return 1;
	}
}
