/**
 * 
 */
package jsql.data;

/**
 * @author tmkhanh
 *
 */
public class FloatType extends NumberType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FloatType(Float f) {
		super(f);
	}

	@Override
	public int compareTo(Type o) {
		if (o.getClass()==IntType.class) return ((Float)getValue()).compareTo(((Integer)o.getValue()).floatValue());;
		if (o.getClass()!=this.getClass()) return 0;
		return ((Float)getValue()).compareTo((Float)o.getValue());
	}
	
	@Override
	public NumberType cong(NumberType num) {
		if (num instanceof IntType) {
			Float t = ((Integer)num.getValue()).floatValue();
			this.value = (Float)this.value + t;
			return new FloatType((Float) value);
		}
		if (num instanceof FloatType) {
			this.value = (Float)this.value + (Float)num.getValue();
			return new FloatType((Float) value);
		}
		return null;
	}
}
