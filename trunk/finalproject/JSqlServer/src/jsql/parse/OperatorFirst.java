/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorFirst extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "FIRST";
	}
}
