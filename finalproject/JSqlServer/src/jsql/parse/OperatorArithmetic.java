/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public abstract class OperatorArithmetic extends Operator {
	@Override
	public int getSoNgoi() {
		return 2;
	}
	@Override
	public int compareTo(Operator o) {
		if (o.getSoNgoi()==1) return -1;
		if (o.getSoNgoi()==2 && (o instanceof OperatorLogic)) return 1;
		if (o.getSoNgoi()==2 && (o instanceof OperatorSet)) return 1;
		if (o.getSoNgoi()==2 && (o instanceof OperatorCompare)) return 1;
		return 0;
	}
}
