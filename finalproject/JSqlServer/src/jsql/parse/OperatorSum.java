/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorSum extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "SUM";
	}
}
