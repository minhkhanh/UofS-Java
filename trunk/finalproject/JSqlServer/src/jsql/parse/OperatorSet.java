/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public abstract class OperatorSet extends Operator {
	public int compareTo(Operator o) {
		if (o.getSoNgoi()==1) return -1;
		if (o.getSoNgoi()==2) return 0;
		return 0;
	}
	@Override
	public int getSoNgoi() {
		return 2;
	}
}
