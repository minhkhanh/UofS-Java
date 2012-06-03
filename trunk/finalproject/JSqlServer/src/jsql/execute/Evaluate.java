/**
 * 
 */
package jsql.execute;

import java.sql.SQLException;
import java.util.Vector;

import jsql.jparse.Constant;
import jsql.jparse.Exp;
import jsql.jparse.Expression;

/**
 * @author tmkhanh
 *
 */
/**
 * Evaluate SQL expressions
 */
public class Evaluate {

	/**
	 * Evaluate a boolean expression to true or false (for example, SQL WHERE
	 * clauses are boolean expressions)
	 * 
	 * @param rowInfo
	 *            The rowInfo on which to evaluate the expression
	 * @param exp
	 *            The expression to evaluate
	 * @return true if the expression evaluate to true for this rowInfo, false if
	 *         not.
	 */
	public boolean eval(RowInfo rowInfo, Exp exp) throws SQLException {

		if (rowInfo == null || exp == null) {
			throw new SQLException("Eval.eval(): null argument or operator");
		}
		if (!(exp instanceof Expression))
			throw new SQLException(
					"Eval.eval(): only expressions are supported");

		Expression pred = (Expression) exp;
		String op = pred.getOperator();

		if (op.equals("AND")) {
			boolean and = true;
			for (int i = 0; i < pred.nbOperands(); i++) {
				and &= eval(rowInfo, pred.getOperand(i));
			}
			return and;
		} else if (op.equals("OR")) {
			boolean or = false;
			for (int i = 0; i < pred.nbOperands(); i++) {
				or |= eval(rowInfo, pred.getOperand(i));
			}
			return or;
		} else if (op.equals("NOT")) {
			return !eval(rowInfo, pred.getOperand(0));

		} else if (op.equals("=")) {
			return evalCmp(rowInfo, pred.getOperands()) == 0;
		} else if (op.equals("!=")) {
			return evalCmp(rowInfo, pred.getOperands()) != 0;
		} else if (op.equals("<>")) {
			return evalCmp(rowInfo, pred.getOperands()) != 0;
		} else if (op.equals("#")) {
			throw new SQLException("Eval.eval(): Operator # not supported");
		} else if (op.equals(">")) {
			return evalCmp(rowInfo, pred.getOperands()) > 0;
		} else if (op.equals(">=")) {
			return evalCmp(rowInfo, pred.getOperands()) >= 0;
		} else if (op.equals("<")) {
			return evalCmp(rowInfo, pred.getOperands()) < 0;
		} else if (op.equals("<=")) {
			return evalCmp(rowInfo, pred.getOperands()) <= 0;

		} else if (op.equals("BETWEEN") || op.equals("NOT BETWEEN")) {

			// Between: borders included
			Expression newexp = new Expression("AND", new Expression(">=",
					pred.getOperand(0), pred.getOperand(1)), new Expression(
					"<=", pred.getOperand(0), pred.getOperand(2)));

			if (op.equals("NOT BETWEEN"))
				return !eval(rowInfo, newexp);
			else
				return eval(rowInfo, newexp);

		} else if (op.equals("LIKE") || op.equals("NOT LIKE")) {
			boolean like = evalLike(rowInfo, pred.getOperands());
			return op.equals("LIKE") ? like : !like;

		} else if (op.equals("IN") || op.equals("NOT IN")) {

			Expression newexp = new Expression("OR");

			for (int i = 1; i < pred.nbOperands(); i++) {
				newexp.addOperand(new Expression("=", pred.getOperand(0), pred
						.getOperand(i)));
			}

			if (op.equals("NOT IN"))
				return !eval(rowInfo, newexp);
			else
				return eval(rowInfo, newexp);

		} else if (op.equals("IS NULL")) {

			if (pred.nbOperands() <= 0 || pred.getOperand(0) == null)
				return true;
			Exp x = pred.getOperand(0);
			if (x instanceof Constant) {
				return (((Constant) x).getType() == Constant.NULL);
			} else {
				throw new SQLException("Eval.eval(): can't eval IS (NOT) NULL");
			}

		} else if (op.equals("IS NOT NULL")) {

			Expression x = new Expression("IS NULL");
			x.setOperands(pred.getOperands());
			return !eval(rowInfo, x);

		} else {
			throw new SQLException("Eval.eval(): Unknown operator " + op);
		}

	}

	double evalCmp(RowInfo rowInfo, Vector operands) throws SQLException {

		if (operands.size() < 2) {
			throw new SQLException(
					"Eval.evalCmp(): Trying to compare less than two values");
		}
		if (operands.size() > 2) {
			throw new SQLException(
					"Eval.evalCmp(): Trying to compare more than two values");
		}

		Object o1 = null, o2 = null;

		o1 = evalExpValue(rowInfo, (Exp) operands.elementAt(0));
		o2 = evalExpValue(rowInfo, (Exp) operands.elementAt(1));

		if (o1 instanceof String || o2 instanceof String) {
			return (o1.equals(o2) ? 0 : -1);
		}

		if (o1 instanceof Number && o2 instanceof Number) {
			return ((Number) o1).doubleValue() - ((Number) o2).doubleValue();
		} else {
			throw new SQLException("Eval.evalCmp(): can't compare ("
					+ o1.toString() + ") with (" + o2.toString() + ")");
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * evalLike evaluates the LIKE operand
	 * 
	 * @param rowInfo
	 *            the rowInfo to evaluate
	 * @param operands
	 *            the operands
	 * @return true-> the expression matches
	 * @throws SQLException
	 */
	private boolean evalLike(RowInfo rowInfo, Vector operands) throws SQLException {
		if (operands.size() < 2) {
			throw new SQLException(
					"Eval.evalCmp(): Trying to compare less than two values");
		}
		if (operands.size() > 2) {
			throw new SQLException(
					"Eval.evalCmp(): Trying to compare more than two values");
		}

		Object o1 = evalExpValue(rowInfo, (Exp) operands.elementAt(0));
		Object o2 = evalExpValue(rowInfo, (Exp) operands.elementAt(1));

		if ((o1 instanceof String) && (o2 instanceof String)) {
			String s1 = (String) o1;
			String s2 = (String) o2;
			if (s2.startsWith("%")) {
				return s1.endsWith(s2.substring(1));
			} else if (s2.endsWith("%")) {
				return s1.startsWith(s2.substring(0, s2.length() - 1));
			} else {
				return s1.equalsIgnoreCase(s2);
			}
		} else {
			throw new SQLException(
					"Eval.evalLike(): LIKE can only compare strings");
		}

	}

	double evalNumericExp(RowInfo rowInfo, Expression exp) throws SQLException {

		if (rowInfo == null || exp == null || exp.getOperator() == null) {
			throw new SQLException("Eval.eval(): null argument or operator");
		}

		String op = exp.getOperator();

		Object o1 = evalExpValue(rowInfo, (Exp) exp.getOperand(0));
		if (!(o1 instanceof Double))
			throw new SQLException(
					"Eval.evalNumericExp(): expression not numeric");
		Double dobj = (Double) o1;

		if (op.equals("+")) {

			double val = dobj.doubleValue();
			for (int i = 1; i < exp.nbOperands(); i++) {
				Object obj = evalExpValue(rowInfo, (Exp) exp.getOperand(i));
				val += ((Number) obj).doubleValue();
			}
			return val;

		} else if (op.equals("-")) {

			double val = dobj.doubleValue();
			if (exp.nbOperands() == 1)
				return -val;
			for (int i = 1; i < exp.nbOperands(); i++) {
				Object obj = evalExpValue(rowInfo, (Exp) exp.getOperand(i));
				val -= ((Number) obj).doubleValue();
			}
			return val;

		} else if (op.equals("*")) {

			double val = dobj.doubleValue();
			for (int i = 1; i < exp.nbOperands(); i++) {
				Object obj = evalExpValue(rowInfo, (Exp) exp.getOperand(i));
				val *= ((Number) obj).doubleValue();
			}
			return val;

		} else if (op.equals("/")) {

			double val = dobj.doubleValue();
			for (int i = 1; i < exp.nbOperands(); i++) {
				Object obj = evalExpValue(rowInfo, (Exp) exp.getOperand(i));
				val /= ((Number) obj).doubleValue();
			}
			return val;

		} else if (op.equals("**")) {

			double val = dobj.doubleValue();
			for (int i = 1; i < exp.nbOperands(); i++) {
				Object obj = evalExpValue(rowInfo, (Exp) exp.getOperand(i));
				val = Math.pow(val, ((Number) obj).doubleValue());
			}
			return val;

		} else {
			throw new SQLException("Eval.evalNumericExp(): Unknown operator "
					+ op);
		}
	}

	/**
	 * Evaluate a numeric or string expression (example: a+1)
	 * 
	 * @param rowInfo
	 *            The rowInfo on which to evaluate the expression
	 * @param exp
	 *            The expression to evaluate
	 * @return The expression's value
	 */
	public Object evalExpValue(RowInfo rowInfo, Exp exp) throws SQLException {

		Object o2 = null;

		if (exp instanceof Constant) {

			Constant c = (Constant) exp;

			switch (c.getType()) {

			case Constant.COLUMNNAME:

				Object o1 = rowInfo.getAttValue(c.getValue());
				if (o1 == null)
					throw new SQLException(
							"Eval.evalExpValue(): unknown column "
									+ c.getValue());
				try {
					o2 = new Double(o1.toString());
				} catch (NumberFormatException e) {
					o2 = o1;
				}
				break;

			case Constant.NUMBER:
				o2 = new Double(c.getValue());
				break;

			case Constant.STRING:
			default:
				o2 = c.getValue();
				break;
			}
		} else if (exp instanceof Expression) {
			o2 = new Double(evalNumericExp(rowInfo, (Expression) exp));
		}
		return o2;
	}
}
