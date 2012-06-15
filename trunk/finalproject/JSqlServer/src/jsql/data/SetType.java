/**
 * 
 */
package jsql.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
		super(set!=null ? set : new HashSet<Type>());
	}
	@SuppressWarnings("unchecked")
	public Set<Type> getSets() {
		return (Set<Type>) value;
	}
	public boolean add(Type obj) throws Exception {
		Type t = null;
		for (Iterator<Type> iter = getSets().iterator(); iter.hasNext();) {
			Type type = (Type) iter.next();
			t = type;
			break;
		}
		if (t!=null && t.getClass()!=obj.getClass()) throw new Exception("set is error");
		return getSets().add(obj);
	}
	@Override
	public int compareTo(Type o) {
		if (o.getClass()!=this.getClass()) return 0;
		return 0;
		//return ((Set<Type>)getValue()).compareTo((Set)o.getValue());
	}
	
	public boolean contain(Type o) {
		for (Iterator<Type> iterator = getSets().iterator(); iterator.hasNext();) {
			Type type = (Type) iterator.next();
			if (type.equals(o)) return true;
		}
		return false;
	}
}
