/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public abstract class OperatorAggregate extends Operator {
	@Override
	public int compareTo(Operator o) {
		if (o.getSoNgoi()==1) return 0;
		if (o.getSoNgoi()==2 && !(o instanceof OperatorAggregate)) return 1;
		return 0;
	}
	@Override
	public int getSoNgoi() {
		return 1;
	}
}
