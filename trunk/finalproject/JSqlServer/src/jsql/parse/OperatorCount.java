/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorCount extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "COUNT";
	}
}
