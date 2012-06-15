/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public abstract class Operator implements Comparable<Operator> {
	public abstract int getSoNgoi();
	public abstract String getKyHieu();
	@Override
	public String toString() {
		return getKyHieu();
	}
}
