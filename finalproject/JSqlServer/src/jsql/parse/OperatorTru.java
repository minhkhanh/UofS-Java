/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class OperatorTru extends OperatorArithmetic {
	@Override
	public String getKyHieu() {
		return "-";
	}
	@Override
	public int compareTo(Operator o) {
		if (!(o instanceof OperatorArithmetic)) return super.compareTo(o);
		if ((o instanceof OperatorNhan)||(o instanceof OperatorChia)) return -1;
		return 0;
	}
}
