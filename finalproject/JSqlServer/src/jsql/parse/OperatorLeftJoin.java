/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorLeftJoin extends OperatorJoin {
	@Override
	public int getSoNgoi() {
		return 2;
	}

	@Override
	public String getKyHieu() {
		return "LEFT JOIN";
	}

}
