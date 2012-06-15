/**
 * 
 */
package jsql.parse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jsql.data.BooleanType;
import jsql.data.QueryRow;
import jsql.data.QueryTable;
import jsql.data.SetType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class SetConstant extends Constant {

	private HashSet<Exp> set = new HashSet<Exp>(); 

	private int iMode = 0;
	public static final int MODE_DEFAULT = 0;
	//public static final int MODE_EXISTS = 0;
	public static final int MODE_ANY = 1;
	public static final int MODE_ALL = 2;
	protected SetConstant(SetType value) {
		super(SET, value);
	}
	
	protected SetConstant(String value) throws Exception {
		super(SET, new SetType(null));
		//xu ly chuoi tap hop
		StringBuilder sql = new StringBuilder(value);
	
		while (sql.length()>0) {
			//if (sql.charAt(0)=='\'') inString = !inString;
			boolean inString = false;
			if (!inString && sql.charAt(0)=='(') {
				int count = 0;
				for (int j = 0; j < sql.length(); j++) {
					if (sql.charAt(j)=='\'') inString = !inString;
					if (!inString && sql.charAt(j)=='(') ++count;
					if (!inString && sql.charAt(j)==')') --count;
					if (!inString && count==0) {
						String sub = sql.substring(1, j).trim();
						set.add(ExpressionTree.createExp(sub));
						sql.delete(0, j + 1);
						break;
					}
				}
				sql = Utils.trim(sql);
				continue;
			}
			inString = false;
			boolean find = false;
			for (int j = 0; j < sql.length(); j++) {
				if (sql.charAt(j)=='\'') inString = !inString;
				if (!inString && sql.charAt(j) == ',') {
					String sub = sql.substring(0, j).trim();
					set.add(ExpressionTree.createExp(sub));
					sql.delete(0, j + 1);
					sql = Utils.trim(sql);
					find = true;
					break;
				}
			}
			if (sql.length()>0 && !find) {
				set.add(ExpressionTree.createExp(sql.toString()));
				break;
			}
		}
	}

	public void processing(QueryRow queryRow) throws Exception {
		for (Iterator<Exp> iterator = set.iterator(); iterator.hasNext();) {
			Exp exp = (Exp) iterator.next();
			if (exp instanceof IntConstant || exp instanceof FloatConstant || exp instanceof StringConstant || exp instanceof BooleanConstant) {
				Constant c = (Constant)exp;
				((SetType)getValue()).add((Type) c.getValue());
			}
			if (exp instanceof Select) {
				//su ly
			}
		}
	}
	
	public void processing(QueryTable queryTable) throws Exception {
		for (Iterator<Exp> iterator = set.iterator(); iterator.hasNext();) {
			Exp exp = (Exp) iterator.next();
			if (exp instanceof Constant && !(exp instanceof ColumnConstant) && !(exp instanceof SetConstant)) {
				Constant c = (Constant)exp;
				((SetType)getValue()).add((Type) c.getValue());
			}
			if (exp instanceof Select) {
				//su ly
			}
		}
	}
		
	public void setMode(int mode) {
		iMode = mode;
	}


	public BooleanConstant isExists() {
		return new BooleanConstant(new BooleanType(!((SetType)getValue()).getSets().isEmpty()));
	}
	
	public BooleanConstant contain(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(((SetType)getValue()).contain((Type) obj.getValue())));
	}
	
	public BooleanConstant add(Constant obj) throws Exception {
		return new BooleanConstant(new BooleanType(((SetType)getValue()).add((Type) obj.getValue())));
	}
	
	@Override
	public int compareTo(Constant obj) throws Exception {
		if (!(obj instanceof NumberConstant)) throw new Exception("compare is not suport!");
		return ((Type)getValue()).compareTo((Type) obj.getValue());
	}
	
	@Override
	public BooleanConstant lon(Constant obj) throws Exception {
		if (obj instanceof SetConstant) throw new Exception("compare is not suport (set with set)!");
		if (obj instanceof ColumnConstant) throw new Exception("compare is not suport (set with column)!");
		if (obj instanceof BooleanConstant) throw new Exception("compare is not suport (set with BooleanConstant)!");
		if (iMode==MODE_ALL) {
			Set<Type> sets = ((SetType)getValue()).getSets();
			for (Iterator<Type> iterator = sets.iterator(); iterator.hasNext();) {
				Type type1 = (Type)obj.getValue();
				Type type2 = (Type) iterator.next();
				if (type1.compareTo(type2)!=1) {
					return new BooleanConstant(new BooleanType(false));
				}
			}
			return new BooleanConstant(new BooleanType(true));
		}
		if (iMode==MODE_ANY) {
			Set<Type> sets = ((SetType)getValue()).getSets();
			for (Iterator<Type> iterator = sets.iterator(); iterator.hasNext();) {
				Type type1 = (Type)obj.getValue();
				Type type2 = (Type) iterator.next();
				if (type1.compareTo(type2)==1) {
					return new BooleanConstant(new BooleanType(true));
				}
			}
			return new BooleanConstant(new BooleanType(false));
		}
		throw new Exception("compare is not suport (syntax error)!");
	}
	@Override
	public BooleanConstant nho(Constant obj) throws Exception {
		if (obj instanceof SetConstant) throw new Exception("compare is not suport (set with set)!");
		if (obj instanceof ColumnConstant) throw new Exception("compare is not suport (set with column)!");
		if (obj instanceof BooleanConstant) throw new Exception("compare is not suport (set with BooleanConstant)!");
		if (iMode==MODE_ALL) {
			Set<Type> sets = ((SetType)getValue()).getSets();
			for (Iterator<Type> iterator = sets.iterator(); iterator.hasNext();) {
				Type type1 = (Type)obj.getValue();
				Type type2 = (Type) iterator.next();
				if (type1.compareTo(type2)!=-1) {
					return new BooleanConstant(new BooleanType(false));
				}
			}
			return new BooleanConstant(new BooleanType(true));
		}
		if (iMode==MODE_ANY) {
			Set<Type> sets = ((SetType)getValue()).getSets();
			for (Iterator<Type> iterator = sets.iterator(); iterator.hasNext();) {
				Type type1 = (Type)obj.getValue();
				Type type2 = (Type) iterator.next();
				if (type1.compareTo(type2)==-1) {
					return new BooleanConstant(new BooleanType(true));
				}
			}
			return new BooleanConstant(new BooleanType(false));
		}
		throw new Exception("compare is not suport (syntax error)!");
	}
	@Override
	public BooleanConstant bang(Constant obj) throws Exception {
		if (obj instanceof SetConstant) throw new Exception("compare is not suport (set with set)!");
		if (obj instanceof ColumnConstant) throw new Exception("compare is not suport (set with column)!");
		if (obj instanceof BooleanConstant) throw new Exception("compare is not suport (set with BooleanConstant)!");
		if (iMode==MODE_ALL) {
			Set<Type> sets = ((SetType)getValue()).getSets();
			for (Iterator<Type> iterator = sets.iterator(); iterator.hasNext();) {
				Type type1 = (Type)obj.getValue();
				Type type2 = (Type) iterator.next();
				if (type1.compareTo(type2)!=0) {
					return new BooleanConstant(new BooleanType(false));
				}
			}
			return new BooleanConstant(new BooleanType(true));
		}
		if (iMode==MODE_ANY) {
			Set<Type> sets = ((SetType)getValue()).getSets();
			for (Iterator<Type> iterator = sets.iterator(); iterator.hasNext();) {
				Type type1 = (Type)obj.getValue();
				Type type2 = (Type) iterator.next();
				if (type1.compareTo(type2)==0) {
					return new BooleanConstant(new BooleanType(true));
				}
			}
			return new BooleanConstant(new BooleanType(false));
		}
		throw new Exception("compare is not suport (syntax error)!");
	}
}
