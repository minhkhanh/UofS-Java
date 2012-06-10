/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public abstract class OperatorJoin extends Operator {
	@Override
	public int getSoNgoi() {
		return 2;
	}
	@Override
	public int compareTo(Operator o) {
		return 0;
	}
}
