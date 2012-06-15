/**
 * 
 */
package jsql.parse;

import java.util.Stack;
import java.util.Vector;

import jsql.data.BooleanType;
import jsql.data.QueryRow;
import jsql.data.QueryTable;
import jsql.data.SetType;
import jsql.data.Type;

/**
 * @author tmkhanh
 *
 */
public class ExpressionTree implements Exp {
	protected Operator operator;
	protected Exp childLeft; //toan hang 2
	protected Exp childRight; //toan hang 1
	protected ExpressionTree(Operator operator) {
		//super(EXP, null);
		this.operator = operator;
	}
	public static Exp createWhere(String sqlStatement) throws Exception {
		return (Exp) createExp(sqlStatement);
	}
	public static Exp createExp(String sqlStatement) throws Exception {
		//chuyen cau lenh string thanh mang cac obj
		Vector<Object> listStatement = new Vector<Object>();
		StringBuilder statement = new StringBuilder(sqlStatement);
		Object e = null;
		while ((e = Utils.splitWhereStatement(statement))!=null) {
			listStatement.add(e);
//			if (e instanceof OperatorIn) {
//				e = Utils.splitWhereStatementIN(statement);
//				listStatement.add(e);
//			}
		}
		// chuyen tu trung to sang hau to
		Vector<Object> listOutput = new Vector<Object>();
		Stack<Object> stack = new Stack<Object>();
		for (Object object : listStatement) {
			if (object instanceof Exp) listOutput.add((Exp) object);
			if (object instanceof DauNgoacMo) stack.push(object);
			if (object instanceof DauNgoacDong) {
				Object obj = null;
				while (!stack.empty() && !((obj = stack.pop()) instanceof DauNgoacMo)) {
					listOutput.add(obj);
				}
			}
			if (object instanceof Operator) {
				while (!stack.empty() && (stack.peek() instanceof Operator) && ((Operator)stack.peek()).compareTo((Operator) object)>=0) {
					listOutput.add(stack.pop());
				}
				stack.push(object);
			}
		}
		while (!stack.empty()) listOutput.add(stack.pop());
		
		Stack<Exp> stackExp = new Stack<Exp>();
		//chuyen thanh cay bieu thuc
		for (Object exp : listOutput) {
			if (exp instanceof Exp) {
				stackExp.push((Exp) exp);
			}
			if (exp instanceof Operator) {
				ExpressionTree temp = new ExpressionTree((Operator) exp);
				if (((Operator) exp).getSoNgoi()==1) {					
					temp.setOneChild(stackExp.pop());
				} else {
					temp.setChildLeft(stackExp.pop());
					temp.setChildRight(stackExp.pop());
				}
				stackExp.push(temp);
			}
		}		
		return stackExp.pop();
	}
	
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public Exp getChildLeft() {
		return childLeft;
	}
	public void setChildLeft(Exp childLeft) {
		this.childLeft = childLeft;
	}
	public Exp getChildRight() {
		return childRight;
	}
	public void setChildRight(Exp childRight) {
		this.childRight = childRight;
	}
	public boolean isOneChild() {
		return (childLeft==null && childRight!=null)||(childLeft!=null && childRight==null);
	}
	public Exp getOneChild() {
		if (!isOneChild()) return null;
		return (childLeft==null)?childRight:childLeft;
	}
	public void setOneChild(Exp e) {
		childRight = e; 
		childLeft = null;
	}	
	
	public boolean filterByExpression(QueryRow queryRow, Select parent) throws Exception {
		Constant c = evaluate(queryRow, parent);
		if (!(c instanceof BooleanConstant)) throw new Exception("where syntax error!");
		return (Boolean) ((BooleanConstant)c).getBaseValue();
	}
	private Constant evaluate(QueryRow queryRow, Select parent) throws Exception {
		if (isOneChild()) {
			Exp e = getOneChild();
			if (e instanceof Constant && operator==null) return (Constant) e;
		}
		if (operator==null) throw new Exception("compare with null");
		Exp eL = getChildLeft();
		Constant cL = null;
		if (eL instanceof ExpressionTree) cL = ((ExpressionTree)eL).evaluate(queryRow, parent);
		else if (eL!=null) cL = (Constant) eL;

		Exp eR = getChildRight();
		Constant cR = null;
		if (eR instanceof ExpressionTree) cR = ((ExpressionTree)eR).evaluate(queryRow, parent);
		else if (eR!=null) cR = (Constant) eR;
		
		if (eR instanceof Select) {
			
		}
		
		if (eL instanceof Select) {
			
		}
		
		if (cL instanceof ColumnConstant) {
			//String colName = (String)cL.getBaseValue();
			Type type = queryRow.getData((ColumnConstant) cL);
			if (type==null) throw new Exception("sql syntac error: column name is not exist!");
			cL = Constant.create(type);
		}
		if (cR instanceof ColumnConstant) {
			//String colName = (String)cR.getBaseValue();
			Type type = queryRow.getData((ColumnConstant) cR);
			if (type==null) throw new Exception("sql syntac error: column name is not exist!");
			cR = Constant.create(type);
		}
		
		if (operator instanceof OperatorLogic) {
			if (!(cR instanceof BooleanConstant)) throw new Exception("compare with non-bool");
			if (operator instanceof OperatorNot) return ((BooleanConstant)cR).not();
			if (!(cL instanceof BooleanConstant)) throw new Exception("compare with non-bool");
			if (operator instanceof OperatorAnd) return ((BooleanConstant)cR).and((BooleanConstant) cL);
			if (operator instanceof OperatorOr) return ((BooleanConstant)cR).or((BooleanConstant) cL);			
			throw new Exception("logic operator is dont suport!");
		}
		
		if (operator instanceof OperatorCompare) {
			if (operator instanceof OperatorLike) return ((StringConstant)cR).like((StringConstant)cL);
			if (cL instanceof SetConstant) {
				Constant t = cL;
				cL = cR;
				cR = t;
				((SetConstant)cR).processing(queryRow);
			}
			if (operator instanceof OperatorBang) return cR.bang(cL);
			if (operator instanceof OperatorKhac) return cR.khac(cL);
			if (operator instanceof OperatorNhoHon) return cR.nho(cL);
			if (operator instanceof OperatorLonHon) return cR.lon(cL);
			if (operator instanceof OperatorNhoHonHoacBang) return cR.nhobang(cL);
			if (operator instanceof OperatorLonHonHoacBang) return cR.lonbang(cL);			
			throw new Exception("compare operator is dont suport!");
		}
		
		if (operator instanceof OperatorSet) {					
			if (operator instanceof OperatorIn) {
				if (cL instanceof Constant && !(cL instanceof ColumnConstant) && !(cL instanceof SetConstant)) {
					SetConstant t = new SetConstant(new SetType(null));
					t.add(cL);
					cL = t;
				}
				if (!(cL instanceof SetConstant)) throw new Exception("set with non-setable");
				((SetConstant)cL).processing(queryRow);
				return ((SetConstant)cL).contain(cR);
			}
			if (operator instanceof OperatorExists) {
				if (!isOneChild()) throw new Exception("Exists syntax error");
				if (getOneChild() instanceof Constant && !(getOneChild() instanceof ColumnConstant) && !(getOneChild() instanceof SetConstant)) {
					return new BooleanConstant(new BooleanType(true));
				}
				if (!(getOneChild() instanceof SetConstant)) throw new Exception("set with non-setable");
				
				SetConstant t = (SetConstant) getOneChild(); 
				t.processing(queryRow);
				return t.isExists();
			}			
			if (operator instanceof OperatorAll) {
				if (!isOneChild()) throw new Exception("All syntax error");
				if (getOneChild() instanceof Constant && !(getOneChild() instanceof ColumnConstant) && !(getOneChild() instanceof SetConstant)) {
					return new BooleanConstant(new BooleanType(true));
				}
				if (!(getOneChild() instanceof SetConstant)) throw new Exception("set with non-setable");
				
				SetConstant t = (SetConstant) getOneChild(); 
				t.processing(queryRow);
				t.setMode(SetConstant.MODE_ALL);
				return t;
			}
			if (operator instanceof OperatorAny) {
				if (!isOneChild()) throw new Exception("Any syntax error");
				if (getOneChild() instanceof Constant && !(getOneChild() instanceof ColumnConstant) && !(getOneChild() instanceof SetConstant)) {
					return new BooleanConstant(new BooleanType(true));
				}
				if (!(getOneChild() instanceof SetConstant)) throw new Exception("set with non-setable");
				
				SetConstant t = (SetConstant) getOneChild(); 
				t.processing(queryRow);
				t.setMode(SetConstant.MODE_ANY);
				return t;
			}
			throw new Exception("set operator is dont suport!");
		}
		
		if (operator instanceof OperatorArithmetic) {			
			//if (!(cR instanceof SetConstant)) throw new Exception("set with non-setable");
			if (operator instanceof OperatorCong) return cR.cong(cL);
			if (operator instanceof OperatorTru) return cR.tru(cL);
			if (operator instanceof OperatorNhan) return cR.nhan(cL);
			if (operator instanceof OperatorChia) return cR.chia(cL);
			throw new Exception("Arithmetic operator is dont suport!");
		}
		
//		if (operator instanceof OperatorAggregate) {			
//			//if (!(cR instanceof SetConstant)) throw new Exception("set with non-setable");
//			if (operator instanceof OperatorAvg) return cR.cong(cL);
//			if (operator instanceof OperatorTru) return cR.tru(cL);
//			if (operator instanceof OperatorNhan) return cR.nhan(cL);
//			if (operator instanceof OperatorChia) return cR.chia(cL);
//			throw new Exception("Arithmetic operator is dont suport!");
//		}
		
		throw new Exception("set operator is dont suport!");
	}
	
	public boolean filterByExpression(QueryTable queryTable, Select parent) throws Exception {
		Constant c = evaluate(queryTable, parent);
		if (!(c instanceof BooleanConstant)) throw new Exception("where syntax error!");
		return (Boolean) ((BooleanConstant)c).getBaseValue();
	}
	public Constant evaluate(QueryTable queryTable, Select parent) throws Exception {
		if (isOneChild()) {
			Exp e = getOneChild();
			if (e instanceof Constant && !(operator instanceof OperatorAggregate)) return (Constant) e;
		}
		if (operator==null) throw new Exception("compare with null");
		Exp eL = getChildLeft();
		Constant cL = null;
		if (eL instanceof ExpressionTree) cL = ((ExpressionTree)eL).evaluate(queryTable, parent);
		else if (eL!=null) cL = (Constant) eL;

		Exp eR = getChildRight();
		Constant cR = null;
		if (eR instanceof ExpressionTree) cR = ((ExpressionTree)eR).evaluate(queryTable, parent);
		else if (eR!=null) cR = (Constant) eR;
		
		if (eR instanceof Select) {
			
		}
		
		if (eL instanceof Select) {
			
		}
		
//		if ((cL instanceof ColumnConstant) && !(operator instanceof OperatorAggregate)) {
//			throw new Exception("sql syntac error: column name is not exist in OperatorAggregate!");
//		}
//		if ((cR instanceof ColumnConstant) && !(operator instanceof OperatorAggregate)) {
//			throw new Exception("sql syntac error: column name is not exist in OperatorAggregate!");
//		}
		
		if (operator instanceof OperatorLogic) {
			if (!(cR instanceof BooleanConstant)) throw new Exception("compare with non-bool");
			if (operator instanceof OperatorNot) return ((BooleanConstant)cR).not();
			if (!(cL instanceof BooleanConstant)) throw new Exception("compare with non-bool");
			if (operator instanceof OperatorAnd) return ((BooleanConstant)cR).and((BooleanConstant) cL);
			if (operator instanceof OperatorOr) return ((BooleanConstant)cR).or((BooleanConstant) cL);			
			throw new Exception("logic operator is dont suport!");
		}
		
		if (operator instanceof OperatorCompare) {
			if (operator instanceof OperatorBang) return cR.bang(cL);
			if (operator instanceof OperatorKhac) return cR.khac(cL);
			if (operator instanceof OperatorNhoHon) return cR.nho(cL);
			if (operator instanceof OperatorLonHon) return cR.lon(cL);
			if (operator instanceof OperatorNhoHonHoacBang) return cR.nhobang(cL);
			if (operator instanceof OperatorLonHonHoacBang) return cR.lonbang(cL);
			if (operator instanceof OperatorLike) return ((StringConstant)cR).like((StringConstant)cL);
			throw new Exception("compare operator is dont suport!");
		}
		
		if (operator instanceof OperatorSet) {			
			if (!(cR instanceof SetConstant)) throw new Exception("set with non-setable");
			if (operator instanceof OperatorIn) return ((SetConstant)cR).contain(cL);
			throw new Exception("set operator is dont suport!");
		}
		
		if (operator instanceof OperatorArithmetic) {			
			//if (!(cR instanceof SetConstant)) throw new Exception("set with non-setable");
			if (operator instanceof OperatorCong) return cR.cong(cL);
			if (operator instanceof OperatorTru) return cR.tru(cL);
			if (operator instanceof OperatorNhan) return cR.nhan(cL);
			if (operator instanceof OperatorChia) return cR.chia(cL);
			throw new Exception("Arithmetic operator is dont suport!");
		}
		
		if (operator instanceof OperatorAggregate) {			
			if (!(isOneChild())) throw new Exception("error syntax Aggregate");
			ColumnConstant col = (ColumnConstant) getOneChild();
			return queryTable.executeAggregate((OperatorAggregate) operator, col);
		}
		
		throw new Exception("set operator is dont suport!");
	}
	
	@Override
	public String toString() {
		if (isOneChild()) {
			Exp eL = getOneChild();
			return operator.toString() + "(" + eL.toString() + ")";
		}
		Exp eR = getChildRight();
		Exp eL = getChildLeft();
		return eR.toString() + " " + operator.toString() + " " + eL.toString();
	}
}
