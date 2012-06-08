/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorLike extends OperatorCompare {
	@Override
	public String getKyHieu() {
		return "LIKE";
	}	
}
