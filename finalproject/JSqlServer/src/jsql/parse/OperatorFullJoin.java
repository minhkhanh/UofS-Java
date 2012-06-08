/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorFullJoin extends OperatorJoin {
	@Override
	public int getSoNgoi() {
		return 2;
	}

	@Override
	public String getKyHieu() {
		return "FULL JOIN";
	}

}
