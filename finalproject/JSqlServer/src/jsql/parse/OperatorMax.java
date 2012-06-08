/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorMax extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "MAX";
	}
}
