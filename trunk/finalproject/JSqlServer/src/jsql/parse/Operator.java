/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public interface Operator extends Comparable<Operator>, Exp {
	public int getSoNgoi();
	public String getKyHieu();
}
