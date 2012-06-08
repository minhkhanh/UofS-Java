/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorRightJoin extends OperatorJoin {
	@Override
	public int getSoNgoi() {
		return 2;
	}

	@Override
	public String getKyHieu() {
		return "RIGHT JOIN";
	}

}
