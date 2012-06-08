/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorAvg extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "AVG";
	}
}
