/**
 * 
 */
package jsql.data;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author tmkhanh
 *
 */
public class SetType extends Type {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SetType(Set<Type> set) {
		super(set!=null ? set : new TreeSet<Type>());
	}
	@Override
	public int compareTo(Type o) {
		if (o.getClass()!=this.getClass()) return 0;
		return 0;
		//return ((Set<Type>)getValue()).compareTo((Set)o.getValue());
	}
	
	@SuppressWarnings("unchecked")
	public boolean contain(Type o) {
		return ((Set<Type>)getValue()).contains(o);
	}
	
	@SuppressWarnings("unchecked")
	public boolean add(Type o) {
		return ((Set<Type>)getValue()).add(o);
	}
}
