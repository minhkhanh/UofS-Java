/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorMin extends OperatorAggregate {
	@Override
	public String getKyHieu() {
		return "MIN";
	}
}
