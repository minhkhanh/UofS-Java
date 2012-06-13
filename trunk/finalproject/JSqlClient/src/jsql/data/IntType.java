/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class IntType extends NumberType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntType(Integer _int) {
		super(_int);
	}

	@Override
	public int compareTo(Type o) {
		if (o.getClass()==FloatType.class) return ((Integer)getValue()).compareTo(((Float)o.getValue()).intValue());;
		if (o.getClass()!=this.getClass()) return 0;
		return ((Integer)getValue()).compareTo((Integer)o.getValue());
	}

	@Override
	public NumberType cong(NumberType num) {
		if (num instanceof FloatType) {
			Integer t = ((Float)num.getValue()).intValue();
			this.value = (Integer)this.value + t;
			return new FloatType( (Float)num.getValue() + (Integer) value);
		}
		if (num instanceof IntType) {
			this.value = (Integer)this.value + (Integer)num.getValue();
			return new IntType((Integer) value);
		}
		return null;
	}
	

}
