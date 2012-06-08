/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorLast extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "LAST";
	}
}
