/**
 * 
 */
package jsql.data;

import java.io.Serializable;

/**
 * @author tmkhanh
 *
 */
public abstract class Type implements Serializable, Comparable<Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Type(Object obj) {
		value = obj;
	}
	Object value;
	public Object getValue() {
		return value;
	}
	public void setValue(Object obj) {
		value = obj;
	}
	
	public static Type createType(String val) throws Exception {
		if (val.charAt(0)=='\''&&val.charAt(0)=='\'')
		{
			String str = val.substring(1, val.length()-1);
			if (str==null || str.length()==0) throw new Exception("erro type!");
			return new StringType(str);
		}			
		int iVal = Integer.parseInt(val);
		return new IntType(iVal);
		//throw new Exception("type not suport!");		
	}
}
	