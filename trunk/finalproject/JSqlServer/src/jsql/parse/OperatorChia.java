/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorChia extends OperatorArithmetic {
	@Override
	public String getKyHieu() {
		return "/";
	}
	public int compareTo(Operator o) {
		if (!(o instanceof OperatorArithmetic)) return super.compareTo(o);
		if ((o instanceof OperatorNhan)||(o instanceof OperatorChia)) return 0;
		return 1;
	}
}
