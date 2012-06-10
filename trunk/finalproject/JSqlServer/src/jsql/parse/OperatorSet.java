/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public abstract class OperatorSet extends Operator {
	@Override
	public int compareTo(Operator o) {
		if (o.getSoNgoi()==1) return -1;
		if (o.getSoNgoi()==2 && (o instanceof OperatorLogic)) return 1;
		if (o.getSoNgoi()==2 && (o instanceof OperatorArithmetic)) return -1;
		if (o.getSoNgoi()==2 && (o instanceof OperatorCompare)) return -1;
		return 0;
	}
}
