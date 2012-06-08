/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorCong extends OperatorArithmetic {
	@Override
	public String getKyHieu() {
		return "+";
	}
	public int compareTo(Operator o) {
		if (!(o instanceof OperatorArithmetic)) return super.compareTo(o);
		if ((o instanceof OperatorNhan)||(o instanceof OperatorChia)) return -1;
		return 0;
	}
}
