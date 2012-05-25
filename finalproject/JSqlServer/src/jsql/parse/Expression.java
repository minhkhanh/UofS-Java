/**
 * 
 */
package jsql.parse;

import java.util.Vector;

/**
 * @author tmkhanh
 *
 */
/**
 * Expression: an SQL Expression An SQL expression is an operator and one or
 * more operands Example: a AND b AND c -> operator = AND, operands = (a, b, c)
 */
public class Expression implements Exp {

	String op_ = null;
	Vector operands_ = null;

	/**
	 * Create an SQL Expression given the operator
	 * 
	 * @param op
	 *            The operator
	 */
	public Expression(String op) {
		op_ = new String(op);
	}

	/**
	 * Create an SQL Expression given the operator and 1st operand
	 * 
	 * @param op
	 *            The operator
	 * @param o1
	 *            The 1st operand
	 */
	public Expression(String op, Exp o1) {
		op_ = new String(op);
		addOperand(o1);
	}

	/**
	 * Create an SQL Expression given the operator, 1st and 2nd operands
	 * 
	 * @param op
	 *            The operator
	 * @param o1
	 *            The 1st operand
	 * @param o2
	 *            The 2nd operand
	 */
	public Expression(String op, Exp o1, Exp o2) {
		op_ = new String(op);
		addOperand(o1);
		addOperand(o2);
	}

	/**
	 * Get this expression's operator.
	 * 
	 * @return the operator.
	 */
	public String getOperator() {
		return op_;
	}

	/**
	 * Set the operands list
	 * 
	 * @param v
	 *            A vector that contains all operands (Exp objects).
	 */
	public void setOperands(Vector v) {
		operands_ = v;
	}

	/**
	 * Get this expression's operands.
	 * 
	 * @return the operands (as a Vector of Exp objects).
	 */
	public Vector getOperands() {
		return operands_;
	}

	/**
	 * Add an operand to the current expression.
	 * 
	 * @param o
	 *            The operand to add.
	 */
	public void addOperand(Exp o) {
		if (operands_ == null)
			operands_ = new Vector();
		operands_.addElement(o);
	}

	/**
	 * Get an operand according to its index (position).
	 * 
	 * @param pos
	 *            The operand index, starting at 0.
	 * @return The operand at the specified index, null if out of bounds.
	 */
	public Exp getOperand(int pos) {
		if (operands_ == null || pos >= operands_.size())
			return null;
		return (Exp) operands_.elementAt(pos);
	}

	/**
	 * Get the number of operands
	 * 
	 * @return The number of operands
	 */
	public int nbOperands() {
		if (operands_ == null)
			return 0;
		return operands_.size();
	}

	/**
	 * String form of the current expression (reverse polish notation). Example:
	 * a > 1 AND b = 2 -> (AND (> a 1) (= b 2))
	 * 
	 * @return The current expression in reverse polish notation (a String)
	 */
	public String toReversePolish() {
		StringBuffer buf = new StringBuffer("(");
		buf.append(op_);
		for (int i = 0; i < nbOperands(); i++) {
			Exp opr = getOperand(i);
			if (opr instanceof Expression)
				buf.append(" " + ((Expression) opr).toReversePolish()); // Warning
																			// recursive
																			// call
			else if (opr instanceof Query)
				buf.append(" (" + opr.toString() + ")");
			else
				buf.append(" " + opr.toString());
		}
		buf.append(")");
		return buf.toString();
	}

	public String toString() {

		if (op_.equals("?"))
			return op_; // For prepared columns ("?")

		if (Utils.isCustomFunction(op_) >= 0)
			return formatFunction();

		StringBuffer buf = new StringBuffer();
		if (needPar(op_))
			buf.append("(");

		Exp operand;
		switch (nbOperands()) {

		case 1:
			operand = getOperand(0);
			if (operand instanceof Constant) {
				// Operator may be an aggregate function (MAX, SUM...)
				if (Utils.isAggregate(op_))
					buf.append(op_ + "(" + operand.toString() + ")");
				else if (op_.equals("IS NULL") || op_.equals("IS NOT NULL"))
					buf.append(operand.toString() + " " + op_);
				// "," = list of values, here just one single value
				else if (op_.equals(","))
					buf.append(operand.toString());
				else
					buf.append(op_ + " " + operand.toString());
			} else if (operand instanceof Query) {
				buf.append(op_ + " (" + operand.toString() + ")");
			} else {
				if (op_.equals("IS NULL") || op_.equals("IS NOT NULL"))
					buf.append(operand.toString() + " " + op_);
				// "," = list of values, here just one single value
				else if (op_.equals(","))
					buf.append(operand.toString());
				else
					buf.append(op_ + " " + operand.toString());
			}
			break;

		case 3:
			if (op_.toUpperCase().endsWith("BETWEEN")) {
				buf.append(getOperand(0).toString() + " " + op_ + " "
						+ getOperand(1).toString() + " AND "
						+ getOperand(2).toString());
				break;
			}

		default:

			boolean in_op = op_.equals("IN") || op_.equals("NOT IN");

			int nb = nbOperands();
			for (int i = 0; i < nb; i++) {

				if (in_op && i == 1)
					buf.append(" " + op_ + " (");

				operand = getOperand(i);
				if (operand instanceof Query && !in_op) {
					buf.append("(" + operand.toString() + ")");
				} else {
					buf.append(operand.toString());
				}
				if (i < nb - 1) {
					if (op_.equals(",") || (in_op && i > 0))
						buf.append(", ");
					else if (!in_op)
						buf.append(" " + op_ + " ");
				}
			}
			if (in_op)
				buf.append(")");
			break;
		}

		if (needPar(op_))
			buf.append(")");
		return buf.toString();
	}

	private boolean needPar(String op) {
		String tmp = op.toUpperCase();
		return !(tmp.equals("ANY") || tmp.equals("ALL") || tmp.equals("UNION") || Utils
				.isAggregate(tmp));
	}

	private String formatFunction() {
		StringBuffer b = new StringBuffer(op_ + "(");
		int nb = nbOperands();
		for (int i = 0; i < nb; i++) {
			b.append(getOperand(i).toString() + (i < nb - 1 ? "," : ""));
		}
		b.append(")");
		return b.toString();
	}
}