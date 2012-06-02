/**
 * 
 */
package jsql.parse;

/**
 * @author tmkhanh
 *
 */
public class Utils {
	public static boolean checkStringPrefix(String str, String prefix) {
		if (str.length()<prefix.length()) return false;
		String tmp = str.substring(0, prefix.length());
		tmp = tmp.toUpperCase();
		return tmp.equals(prefix);
	}
	public static int indexOfString(String str, String find) {
		return str.toUpperCase().indexOf(find.toUpperCase());
	}
}
