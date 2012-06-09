package jsql.jparse;

import java.util.Hashtable;
import java.util.Vector;

public class JJParser implements JJParserConstants {
	final public void BasicDataTypeDeclaration() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_CHAR:
		case K_FLOAT:
		case K_INTEGER:
		case K_NATURAL:
		case K_NUMBER:
		case K_REAL:
		case K_VARCHAR2:
		case K_VARCHAR:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_CHAR:
				jj_consume_token(K_CHAR);
				break;
			case K_VARCHAR:
				jj_consume_token(K_VARCHAR);
				break;
			case K_VARCHAR2:
				jj_consume_token(K_VARCHAR2);
				break;
			case K_INTEGER:
				jj_consume_token(K_INTEGER);
				break;
			case K_NUMBER:
				jj_consume_token(K_NUMBER);
				break;
			case K_NATURAL:
				jj_consume_token(K_NATURAL);
				break;
			case K_REAL:
				jj_consume_token(K_REAL);
				break;
			case K_FLOAT:
				jj_consume_token(K_FLOAT);
				break;
			default:
				jj_la1[0] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 88:
				jj_consume_token(88);
				jj_consume_token(S_NUMBER);
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case 89:
					jj_consume_token(89);
					jj_consume_token(S_NUMBER);
					break;
				default:
					jj_la1[1] = jj_gen;
					;
				}
				jj_consume_token(90);
				break;
			default:
				jj_la1[2] = jj_gen;
				;
			}
			break;
		case K_DATE:
			jj_consume_token(K_DATE);
			break;
		case K_BINARY_INTEGER:
			jj_consume_token(K_BINARY_INTEGER);
			break;
		case K_BOOLEAN:
			jj_consume_token(K_BOOLEAN);
			break;
		default:
			jj_la1[3] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	final public Vector SQLStatements() throws ParseException {
		Vector v = new Vector();
		Statement s;
		label_1: while (true) {
			s = SQLStatement();
			if (s == null) {
				if (true)
					return v;
			} else
				v.addElement(s);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_COMMIT:
			case K_DELETE:
			case K_EXIT:
			case K_INSERT:
			case K_LOCK:
			case K_QUIT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_SET:
			case K_UPDATE:
				;
				break;
			default:
				jj_la1[4] = jj_gen;
				break label_1;
			}
		}
		{
			if (true)
				return v;
		}
		throw new Error("Missing return statement in function");
	}

	final public Statement SQLStatement() throws ParseException {
		Statement s = null;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_DELETE:
			s = DeleteStatement();
			{
				if (true)
					return s;
			}
			break;
		case K_INSERT:
			s = InsertStatement();
			{
				if (true)
					return s;
			}
			break;
		case K_SELECT:
			s = QueryStatement();
			{
				if (true)
					return s;
			}
			break;
		case K_UPDATE:
			s = UpdateStatement();
			{
				if (true)
					return s;
			}
			break;
		case K_EXIT:
		case K_QUIT:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_EXIT:
				jj_consume_token(K_EXIT);
				break;
			case K_QUIT:
				jj_consume_token(K_QUIT);
				break;
			default:
				jj_la1[5] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			jj_consume_token(91);
			{
				if (true)
					return null;
			}
			break;
		default:
			jj_la1[6] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	// UpdateStatement ::=
	// UPDATE TableReference SET ColumnValues [WHERE SQLExpression]
	final public Update UpdateStatement() throws ParseException {
		Update u;
		Exp e;
		Hashtable t;
		String s;
		Token tk;
		jj_consume_token(K_UPDATE);
		s = TableReference();
		u = new Update(s);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case S_IDENTIFIER:
			tk = jj_consume_token(S_IDENTIFIER);
			u.setAlias(tk.toString());
			break;
		default:
			jj_la1[18] = jj_gen;
			;
		}
		jj_consume_token(K_SET);
		ColumnValues(u);

		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_WHERE:
			jj_consume_token(K_WHERE);
			e = SQLExpression();
			u.addWhere(e);
			break;
		default:
			jj_la1[19] = jj_gen;
			;
		}
		jj_consume_token(91);
		{
			if (true)
				return u;
		}
		throw new Error("Missing return statement in function");
	}

	// ColumnValues ::= TableColumn = UpdatedValue (, TableColumn =
	// UpdatedValue)*
	final public void ColumnValues(Update u) throws ParseException {
		String key;
		Exp val;
		key = TableColumn();
		jj_consume_token(92);
		val = UpdatedValue();
		u.addColumnUpdate(key, val);
		label_3: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 89:
				;
				break;
			default:
				jj_la1[20] = jj_gen;
				break label_3;
			}
			jj_consume_token(89);
			key = TableColumn();
			jj_consume_token(92);
			val = UpdatedValue();
			u.addColumnUpdate(key, val);
		}

	}

	// UpdatedValue ::= ( SelectStatement ) | SQLExpression
	final public Exp UpdatedValue() throws ParseException {
		Exp e;
		if (jj_2_1(2147483647)) {
			jj_consume_token(88);
			e = SelectStatement();
			jj_consume_token(90);
			{
				if (true)
					return e;
			}
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_AVG:
			case K_COUNT:
			case K_EXISTS:
			case K_MAX:
			case K_MIN:
			case K_NOT:
			case K_NULL:
			case K_PRIOR:
			case K_SUM:
			case S_NUMBER:
			case S_IDENTIFIER:
			case S_BIND:
			case S_CHAR_LITERAL:
			case S_QUOTED_IDENTIFIER:
			case 88:
			case 101:
			case 102:
				e = SQLExpression();
				{
					if (true)
						return e;
				}
				break;
			case 105:
				e = PreparedCol();
				{
					if (true)
						return e;
				}
				break;
			default:
				jj_la1[21] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		throw new Error("Missing return statement in function");
	}

	// InsertStatement ::= INSERT INTO TableReference
	// [ "(" TableColumn (, TableColumn)* ")" ]
	// ValueSpec | SelectStatement
	// ValueSpec ::= VALUES ( SQLExpressionList )
	final public Insert InsertStatement() throws ParseException {
		Insert ins;
		String s;
		Vector v;
		Query q;
		jj_consume_token(K_INSERT);
		jj_consume_token(K_INTO);
		s = TableReference();
		ins = new Insert(s);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 88:
			jj_consume_token(88);
			s = TableColumn();
			v = new Vector();
			v.addElement(s);
			label_4: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case 89:
					;
					break;
				default:
					jj_la1[22] = jj_gen;
					break label_4;
				}
				jj_consume_token(89);
				s = TableColumn();
				v.addElement(s);
			}
			jj_consume_token(90);
			ins.addColumns(v);
			break;
		default:
			jj_la1[23] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_VALUES:
			jj_consume_token(K_VALUES);
			jj_consume_token(88);
			v = SQLExpressionList();
			jj_consume_token(90);
			Expression e = new Expression(",");
			e.setOperands(v);
			ins.addValueSpec(e);
			break;
		case K_SELECT:
			q = SelectStatement();
			ins.addValueSpec(q);
			break;
		default:
			jj_la1[24] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(91);
		{
			if (true)
				return ins;
		}
		throw new Error("Missing return statement in function");
	}

	// DeleteStatement ::= DELETE [FROM] TableReference [WHERE SQLExpression]
	final public Delete DeleteStatement() throws ParseException {
		Delete d;
		Exp e;
		String s;
		jj_consume_token(K_DELETE);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_FROM:
			jj_consume_token(K_FROM);
			break;
		default:
			jj_la1[25] = jj_gen;
			;
		}
		s = TableReference();
		d = new Delete(s);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_WHERE:
			jj_consume_token(K_WHERE);
			e = SQLExpression();
			d.addWhere(e);
			break;
		default:
			jj_la1[26] = jj_gen;
			;
		}
		jj_consume_token(91);
		{
			if (true)
				return d;
		}
		throw new Error("Missing return statement in function");
	}

	// QueryStatement ::= SelectStatement
	final public Query QueryStatement() throws ParseException {
		Query q;
		q = SelectStatement();
		jj_consume_token(91);
		{
			if (true)
				return q;
		}
		throw new Error("Missing return statement in function");
	}

	/* ---------------- General Productions --------------------- */
	final public String TableColumn() throws ParseException {
		StringBuffer buf = new StringBuffer();
		String s;
		// user.table.column
		s = OracleObjectName();
		buf.append(s);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 93:
			jj_consume_token(93);
			s = OracleObjectName();
			buf.append("." + s);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 93:
				jj_consume_token(93);
				s = OracleObjectName();
				buf.append("." + s);
				break;
			default:
				jj_la1[27] = jj_gen;
				;
			}
			break;
		default:
			jj_la1[28] = jj_gen;
			;
		}
		{
			if (true)
				return buf.toString();
		}
		throw new Error("Missing return statement in function");
	}

	final public String OracleObjectName() throws ParseException {
		Token t;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case S_IDENTIFIER:
			t = jj_consume_token(S_IDENTIFIER);
			{
				if (true)
					return t.toString();
			}
			break;
		case S_QUOTED_IDENTIFIER:
			t = jj_consume_token(S_QUOTED_IDENTIFIER);
			{
				if (true)
					return t.toString();
			}
			break;
		default:
			jj_la1[29] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	final public String Relop() throws ParseException {
		Token op;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 92:
			op = jj_consume_token(92);
			{
				if (true)
					return op.toString();
			}
			break;
		case 94:
			op = jj_consume_token(94);
			{
				if (true)
					return op.toString();
			}
			break;
		case 95:
			op = jj_consume_token(95);
			{
				if (true)
					return op.toString();
			}
			break;
		case 96:
			op = jj_consume_token(96);
			{
				if (true)
					return op.toString();
			}
			break;
		case 97:
			op = jj_consume_token(97);
			{
				if (true)
					return op.toString();
			}
			break;
		case 98:
			op = jj_consume_token(98);
			{
				if (true)
					return op.toString();
			}
			break;
		case 99:
			op = jj_consume_token(99);
			{
				if (true)
					return op.toString();
			}
			break;
		case 100:
			op = jj_consume_token(100);
			{
				if (true)
					return op.toString();
			}
			break;
		default:
			jj_la1[30] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	// TableReference ::= OracleObjectName[.OracleObjectName]
	final public String TableReference() throws ParseException {
		StringBuffer buf = new StringBuffer();
		String s;
		s = OracleObjectName();
		buf.append(s);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 93:
			jj_consume_token(93);
			s = OracleObjectName();
			buf.append("." + s);
			break;
		default:
			jj_la1[31] = jj_gen;
			;
		}
		{
			if (true)
				return buf.toString();
		}
		throw new Error("Missing return statement in function");
	}

	final public void NumOrID() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case S_IDENTIFIER:
			jj_consume_token(S_IDENTIFIER);
			break;
		case S_NUMBER:
		case 101:
		case 102:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 101:
			case 102:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case 101:
					jj_consume_token(101);
					break;
				case 102:
					jj_consume_token(102);
					break;
				default:
					jj_la1[32] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
				break;
			default:
				jj_la1[33] = jj_gen;
				;
			}
			jj_consume_token(S_NUMBER);
			break;
		default:
			jj_la1[34] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	/* --------------- General Productions ends here --------------- */

	/* ----------- SQL productions start here ----------------- */
	final public Query SelectStatement() throws ParseException {
		Query q;
		Vector v;
		q = SelectWithoutOrder();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_ORDER:
			v = OrderByClause();
			q.addOrderBy(v);
			break;
		default:
			jj_la1[35] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_FOR:
			ForUpdateClause();
			q.forupdate_ = true;
			break;
		default:
			jj_la1[36] = jj_gen;
			;
		}
		{
			if (true)
				return q;
		}
		throw new Error("Missing return statement in function");
	}

	final public Query SelectWithoutOrder() throws ParseException {
		Query q = new Query();
		Vector select;
		Vector from;
		Exp where = null;
		GroupBy groupby = null;
		Expression setclause = null;
		jj_consume_token(K_SELECT);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_ALL:
		case K_DISTINCT:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_ALL:
				jj_consume_token(K_ALL);
				break;
			case K_DISTINCT:
				jj_consume_token(K_DISTINCT);
				q.distinct_ = true;
				break;
			default:
				jj_la1[37] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[38] = jj_gen;
			;
		}
		select = SelectList();
		from = FromClause();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_WHERE:
			where = WhereClause();
			break;
		default:
			jj_la1[39] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_CONNECT:
		case K_START:
			ConnectClause();
			break;
		default:
			jj_la1[40] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_GROUP:
			groupby = GroupByClause();
			break;
		default:
			jj_la1[41] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_INTERSECT:
		case K_MINUS:
		case K_UNION:
			setclause = SetClause();
			break;
		default:
			jj_la1[42] = jj_gen;
			;
		}
		q.addSelect(select);
		q.addFrom(from);
		q.addWhere(where);
		q.addGroupBy(groupby);
		q.addSet(setclause);

		{
			if (true)
				return q;
		}
		throw new Error("Missing return statement in function");
	}

	/* Checks for whatever follows SELECT */

	// SelectList ::= * | COUNT(*) | SelectItem (, SelectItem)*
	// 18/10/01 PY. Gibello - Removed "COUNT(*)" from here
	// COUNT is an aggregate, like AVG...
	final public Vector SelectList() throws ParseException {
		Vector v = new Vector(8);
		SelectItem elem;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 103:
			jj_consume_token(103);
			v.addElement(new SelectItem("*"));
			{
				if (true)
					return v;
			}
			break;
		case K_AVG:
		case K_COUNT:
		case K_MAX:
		case K_MIN:
		case K_NULL:
		case K_SUM:
		case S_NUMBER:
		case S_IDENTIFIER:
		case S_BIND:
		case S_CHAR_LITERAL:
		case S_QUOTED_IDENTIFIER:
		case 88:
		case 101:
		case 102:
			elem = SelectItem();
			v.addElement(elem);
			label_5: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case 89:
					;
					break;
				default:
					jj_la1[43] = jj_gen;
					break label_5;
				}
				jj_consume_token(89);
				elem = SelectItem();
				v.addElement(elem);
			}
			{
				if (true)
					return v;
			}
			break;
		default:
			jj_la1[44] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	// 01/07/00 PY. Gibello - Added LOOKAHEAD(3) before COUNT
	// Due to a fix in AggregateFunc (COUNT added) that made the grammar
	// ambiguous
	// 18/10/01 PY. Gibello - Removed "COUNT(*)" from here (see aggregate funcs)

	// SelectItem() ::= SelectStar | SQLSimpleExpression() [SelectAlias]
	final public SelectItem SelectItem() throws ParseException {
		String s;
		SelectItem it;
		Exp e;
		if (jj_2_2(2147483647)) {
			s = SelectStar();
			{
				if (true)
					return new SelectItem(s);
			}
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_AVG:
			case K_COUNT:
			case K_MAX:
			case K_MIN:
			case K_NULL:
			case K_SUM:
			case S_NUMBER:
			case S_IDENTIFIER:
			case S_BIND:
			case S_CHAR_LITERAL:
			case S_QUOTED_IDENTIFIER:
			case 88:
			case 101:
			case 102:
				e = SQLSimpleExpression();
				// PY.Gibello 21 Apr 2001 - added e.toString() as arg
				it = new SelectItem(e.toString());
				it.setExpression(e);
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_AS:
				case S_IDENTIFIER:
				case S_QUOTED_IDENTIFIER:
					s = SelectAlias();
					it.setAlias(s);
					break;
				default:
					jj_la1[45] = jj_gen;
					;
				}
				{
					if (true)
						return it;
				}
				break;
			default:
				jj_la1[46] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		throw new Error("Missing return statement in function");
	}

	// SelectAlias ::= (<S_IDENTIFIER>)+
	final public String SelectAlias() throws ParseException {
		Token tk;
		StringBuffer b = null;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_AS:
			jj_consume_token(K_AS);
			break;
		default:
			jj_la1[47] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case S_QUOTED_IDENTIFIER:
			tk = jj_consume_token(S_QUOTED_IDENTIFIER);
			{
				if (true)
					return tk.toString().trim();
			}
			break;
		case S_IDENTIFIER:
			label_6: while (true) {
				tk = jj_consume_token(S_IDENTIFIER);
				if (b == null)
					b = new StringBuffer(tk.toString().trim());
				else
					b.append(" " + tk.toString().trim());
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case S_IDENTIFIER:
					;
					break;
				default:
					jj_la1[48] = jj_gen;
					break label_6;
				}
			}
			break;
		default:
			jj_la1[49] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return b.toString().trim();
		}
		throw new Error("Missing return statement in function");
	}

	// SelectStar() ::= OracleObjectName()".*" |
	// OracleObjectName()"." OracleObjectName() ".*"
	final public String SelectStar() throws ParseException {
		String s, s2;
		if (jj_2_3(2)) {
			s = OracleObjectName();
			jj_consume_token(104);
			{
				if (true)
					return new String(s + ".*");
			}
		} else if (jj_2_4(4)) {
			s = OracleObjectName();
			jj_consume_token(93);
			s2 = OracleObjectName();
			jj_consume_token(104);
			{
				if (true)
					return new String(s + "." + s2 + ".*");
			}
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	// FromClause ::= FromItem (, FromItem)*
	final public Vector FromClause() throws ParseException {
		Vector v = new Vector(8);
		FromItem f;
		jj_consume_token(K_FROM);
		f = FromItem();
		v.addElement(f);
		label_7: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 89:
				;
				break;
			default:
				jj_la1[50] = jj_gen;
				break label_7;
			}
			jj_consume_token(89);
			f = FromItem();
			v.addElement(f);
		}
		{
			if (true)
				return v;
		}
		throw new Error("Missing return statement in function");
	}

	// FromItem ::= TableReference [alias]
	final public FromItem FromItem() throws ParseException {
		FromItem f;
		String s;
		Token tk;
		s = TableReference();
		f = new FromItem(s);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case S_IDENTIFIER:
			tk = jj_consume_token(S_IDENTIFIER);
			f.setAlias(tk.toString());
			break;
		default:
			jj_la1[51] = jj_gen;
			;
		}
		{
			if (true)
				return f;
		}
		throw new Error("Missing return statement in function");
	}

	final public Exp WhereClause() throws ParseException {
		Exp e;
		jj_consume_token(K_WHERE);
		e = SQLExpression();
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public void ConnectClause() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_START:
			jj_consume_token(K_START);
			jj_consume_token(K_WITH);
			SQLExpression();
			break;
		default:
			jj_la1[52] = jj_gen;
			;
		}
		jj_consume_token(K_CONNECT);
		jj_consume_token(K_BY);
		SQLExpression();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_START:
			jj_consume_token(K_START);
			jj_consume_token(K_WITH);
			SQLExpression();
			break;
		default:
			jj_la1[53] = jj_gen;
			;
		}
	}

	final public GroupBy GroupByClause() throws ParseException {
		GroupBy g = null;
		Vector v;
		Exp e;
		jj_consume_token(K_GROUP);
		jj_consume_token(K_BY);
		v = SQLExpressionList();
		g = new GroupBy(v);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_HAVING:
			jj_consume_token(K_HAVING);
			e = SQLExpression();
			g.setHaving(e);
			break;
		default:
			jj_la1[54] = jj_gen;
			;
		}
		{
			if (true)
				return g;
		}
		throw new Error("Missing return statement in function");
	}

	// SetClause ::= UNION [ALL] Qry | INTERSECT Qry | MINUS Qry
	// Qry ::= SelectWithoutOrder | ( SelectWithoutOrder )
	final public Expression SetClause() throws ParseException {
		Expression e;
		Query q;
		Token t;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_UNION:
			t = jj_consume_token(K_UNION);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_ALL:
				jj_consume_token(K_ALL);
				break;
			default:
				jj_la1[55] = jj_gen;
				;
			}
			break;
		case K_INTERSECT:
			t = jj_consume_token(K_INTERSECT);
			break;
		case K_MINUS:
			t = jj_consume_token(K_MINUS);
			break;
		default:
			jj_la1[56] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		e = new Expression(t.toString());
		if (jj_2_5(2147483647)) {
			jj_consume_token(88);
			q = SelectWithoutOrder();
			e.addOperand(q);
			jj_consume_token(90);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_SELECT:
				q = SelectWithoutOrder();
				e.addOperand(q);
				break;
			default:
				jj_la1[57] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public Vector OrderByClause() throws ParseException {
		Vector v = new Vector();
		Exp e;
		OrderBy ob;
		jj_consume_token(K_ORDER);
		jj_consume_token(K_BY);
		e = SQLSimpleExpression();
		ob = new OrderBy(e);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_ASC:
		case K_DESC:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_ASC:
				jj_consume_token(K_ASC);
				break;
			case K_DESC:
				jj_consume_token(K_DESC);
				ob.setAscOrder(false);
				break;
			default:
				jj_la1[58] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[59] = jj_gen;
			;
		}
		v.addElement(ob);
		label_8: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 89:
				;
				break;
			default:
				jj_la1[60] = jj_gen;
				break label_8;
			}
			jj_consume_token(89);
			e = SQLSimpleExpression();
			ob = new OrderBy(e);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_ASC:
			case K_DESC:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_ASC:
					jj_consume_token(K_ASC);
					break;
				case K_DESC:
					jj_consume_token(K_DESC);
					ob.setAscOrder(false);
					break;
				default:
					jj_la1[61] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
				break;
			default:
				jj_la1[62] = jj_gen;
				;
			}
			v.addElement(ob);
		}
		{
			if (true)
				return v;
		}
		throw new Error("Missing return statement in function");
	}

	final public void ForUpdateClause() throws ParseException {
		jj_consume_token(K_FOR);
		jj_consume_token(K_UPDATE);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_OF:
			jj_consume_token(K_OF);
			TableColumn();
			label_9: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case 89:
					;
					break;
				default:
					jj_la1[63] = jj_gen;
					break label_9;
				}
				jj_consume_token(89);
				TableColumn();
			}
			break;
		default:
			jj_la1[64] = jj_gen;
			;
		}
	}

	final public Exp SQLExpression() throws ParseException {
		Exp e1, e2;
		Expression e = null;
		boolean single = true;
		e1 = SQLAndExpression();
		label_10: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_OR:
				;
				break;
			default:
				jj_la1[65] = jj_gen;
				break label_10;
			}
			jj_consume_token(K_OR);
			e2 = SQLAndExpression();
			if (single) {
				e = new Expression("OR", e1);
			}
			single = false;
			e.addOperand(e2);
		}
		{
			if (true)
				return (single ? e1 : e);
		}
		throw new Error("Missing return statement in function");
	}

	final public Exp SQLAndExpression() throws ParseException {
		Exp e1, e2;
		Expression e = null;
		boolean single = true;
		e1 = SQLUnaryLogicalExpression();
		label_11: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_AND:
				;
				break;
			default:
				jj_la1[66] = jj_gen;
				break label_11;
			}
			jj_consume_token(K_AND);
			e2 = SQLUnaryLogicalExpression();
			if (single) {
				e = new Expression("AND", e1);
			}
			single = false;
			e.addOperand(e2);
		}
		{
			if (true)
				return (single ? e1 : e);
		}
		throw new Error("Missing return statement in function");
	}

	final public Exp SQLUnaryLogicalExpression() throws ParseException {
		Exp e1, e;
		boolean not = false;
		if (jj_2_6(2)) {
			e = ExistsClause();
			{
				if (true)
					return e;
			}
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_AVG:
			case K_COUNT:
			case K_MAX:
			case K_MIN:
			case K_NOT:
			case K_NULL:
			case K_PRIOR:
			case K_SUM:
			case S_NUMBER:
			case S_IDENTIFIER:
			case S_BIND:
			case S_CHAR_LITERAL:
			case S_QUOTED_IDENTIFIER:
			case 88:
			case 101:
			case 102:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_NOT:
					jj_consume_token(K_NOT);
					not = true;
					break;
				default:
					jj_la1[67] = jj_gen;
					;
				}
				e1 = SQLRelationalExpression();
				if (not)
					e = new Expression("NOT", e1);
				else
					e = e1;
				{
					if (true)
						return e;
				}
				break;
			default:
				jj_la1[68] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression ExistsClause() throws ParseException {
		Expression e;
		Query q;
		boolean not = false;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_NOT:
			jj_consume_token(K_NOT);
			not = true;
			break;
		default:
			jj_la1[69] = jj_gen;
			;
		}
		jj_consume_token(K_EXISTS);
		jj_consume_token(88);
		q = SubQuery();
		jj_consume_token(90);
		Expression e1 = new Expression("EXISTS", q);
		if (not)
			e = new Expression("NOT", e1);
		else
			e = e1;
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	// SQLRelationalExpression ::=
	// ( [PRIOR] SQLSimpleExpression | SQLExpressionList )
	// ( SQLRelationalOperatorExpression
	// | SQLInClause | SQLBetweenClause | SQLLikeClause | IsNullClause )?
	final public Exp SQLRelationalExpression() throws ParseException {
		Exp e1, eleft;
		Expression eright = null;
		Vector v;
		boolean prior = false;
		if (jj_2_7(2147483647)) {
			jj_consume_token(88);
			v = SQLExpressionList();
			jj_consume_token(90);
			eleft = new Expression(",");
			((Expression) eleft).setOperands(v);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_AVG:
			case K_COUNT:
			case K_MAX:
			case K_MIN:
			case K_NULL:
			case K_PRIOR:
			case K_SUM:
			case S_NUMBER:
			case S_IDENTIFIER:
			case S_BIND:
			case S_CHAR_LITERAL:
			case S_QUOTED_IDENTIFIER:
			case 88:
			case 101:
			case 102:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_PRIOR:
					jj_consume_token(K_PRIOR);
					prior = true;
					break;
				default:
					jj_la1[70] = jj_gen;
					;
				}
				e1 = SQLSimpleExpression();
				if (prior)
					eleft = new Expression("PRIOR", e1);
				else
					eleft = e1;
				break;
			default:
				jj_la1[71] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_BETWEEN:
		case K_IN:
		case K_IS:
		case K_LIKE:
		case K_NOT:
		case 92:
		case 94:
		case 95:
		case 96:
		case 97:
		case 98:
		case 99:
		case 100:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 92:
			case 94:
			case 95:
			case 96:
			case 97:
			case 98:
			case 99:
			case 100:
				eright = SQLRelationalOperatorExpression();
				break;
			default:
				jj_la1[72] = jj_gen;
				if (jj_2_8(2)) {
					eright = SQLInClause();
				} else if (jj_2_9(2)) {
					eright = SQLBetweenClause();
				} else if (jj_2_10(2)) {
					eright = SQLLikeClause();
				} else {
					switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
					case K_IS:
						eright = IsNullClause();
						break;
					default:
						jj_la1[73] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
					}
				}
			}
			break;
		default:
			jj_la1[74] = jj_gen;
			;
		}
		if (eright == null) {
			if (true)
				return eleft;
		}
		Vector v2 = eright.getOperands();
		if (v2 == null)
			v2 = new Vector(); // For IS NULL, which is unary!
		v2.insertElementAt(eleft, 0);
		eright.setOperands(v2);
		{
			if (true)
				return eright;
		}
		throw new Error("Missing return statement in function");
	}

	final public Vector SQLExpressionList() throws ParseException {
		Vector v = new Vector(8);
		Exp e;
		e = SQLSimpleExpressionOrPreparedCol();
		v.addElement(e);
		label_12: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 89:
				;
				break;
			default:
				jj_la1[75] = jj_gen;
				break label_12;
			}
			jj_consume_token(89);
			e = SQLSimpleExpressionOrPreparedCol();
			v.addElement(e);
		}
		{
			if (true)
				return v;
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression SQLRelationalOperatorExpression()
			throws ParseException {
		Expression e;
		Exp e1, eright;
		String op;
		String unaryOp = null;
		op = Relop();
		e = new Expression(op);
		if (jj_2_11(2147483647)) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_ALL:
			case K_ANY:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_ALL:
					jj_consume_token(K_ALL);
					unaryOp = "ALL";
					break;
				case K_ANY:
					jj_consume_token(K_ANY);
					unaryOp = "ANY";
					break;
				default:
					jj_la1[76] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
				break;
			default:
				jj_la1[77] = jj_gen;
				;
			}
			jj_consume_token(88);
			e1 = SubQuery();
			jj_consume_token(90);
			if (unaryOp == null)
				eright = e1;
			else
				eright = new Expression(unaryOp, e1);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case K_AVG:
			case K_COUNT:
			case K_MAX:
			case K_MIN:
			case K_NULL:
			case K_PRIOR:
			case K_SUM:
			case S_NUMBER:
			case S_IDENTIFIER:
			case S_BIND:
			case S_CHAR_LITERAL:
			case S_QUOTED_IDENTIFIER:
			case 88:
			case 101:
			case 102:
			case 105:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_PRIOR:
					jj_consume_token(K_PRIOR);
					unaryOp = "PRIOR";
					break;
				default:
					jj_la1[78] = jj_gen;
					;
				}
				e1 = SQLSimpleExpressionOrPreparedCol();
				if (unaryOp == null)
					eright = e1;
				else
					eright = new Expression(unaryOp, e1);
				break;
			default:
				jj_la1[79] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		e.addOperand(eright);
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public Exp SQLSimpleExpressionOrPreparedCol() throws ParseException {
		Exp e;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_AVG:
		case K_COUNT:
		case K_MAX:
		case K_MIN:
		case K_NULL:
		case K_SUM:
		case S_NUMBER:
		case S_IDENTIFIER:
		case S_BIND:
		case S_CHAR_LITERAL:
		case S_QUOTED_IDENTIFIER:
		case 88:
		case 101:
		case 102:
			e = SQLSimpleExpression();
			{
				if (true)
					return e;
			}
			break;
		case 105:
			e = PreparedCol();
			{
				if (true)
					return e;
			}
			break;
		default:
			jj_la1[80] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	// For prepared columns ("?")
	final public Exp PreparedCol() throws ParseException {
		jj_consume_token(105);
		{
			if (true)
				return new Expression("?");
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression SQLInClause() throws ParseException {
		Expression e;
		Query q;
		boolean not = false;
		Vector v;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_NOT:
			jj_consume_token(K_NOT);
			not = true;
			break;
		default:
			jj_la1[81] = jj_gen;
			;
		}
		jj_consume_token(K_IN);
		e = new Expression(not ? "NOT IN" : "IN");
		jj_consume_token(88);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_AVG:
		case K_COUNT:
		case K_MAX:
		case K_MIN:
		case K_NULL:
		case K_SUM:
		case S_NUMBER:
		case S_IDENTIFIER:
		case S_BIND:
		case S_CHAR_LITERAL:
		case S_QUOTED_IDENTIFIER:
		case 88:
		case 101:
		case 102:
		case 105:
			v = SQLExpressionList();
			e.setOperands(v);
			break;
		case K_SELECT:
			q = SubQuery();
			e.addOperand(q);
			break;
		default:
			jj_la1[82] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(90);
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression SQLBetweenClause() throws ParseException {
		Expression e;
		Exp e1, e2;
		boolean not = false;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_NOT:
			jj_consume_token(K_NOT);
			not = true;
			break;
		default:
			jj_la1[83] = jj_gen;
			;
		}
		jj_consume_token(K_BETWEEN);
		e1 = SQLSimpleExpressionOrPreparedCol();
		jj_consume_token(K_AND);
		e2 = SQLSimpleExpressionOrPreparedCol();
		if (not)
			e = new Expression("NOT BETWEEN", e1, e2);
		else
			e = new Expression("BETWEEN", e1, e2);
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression SQLLikeClause() throws ParseException {
		Exp eright;
		Expression e;
		boolean not = false;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_NOT:
			jj_consume_token(K_NOT);
			not = true;
			break;
		default:
			jj_la1[84] = jj_gen;
			;
		}
		jj_consume_token(K_LIKE);
		eright = SQLSimpleExpressionOrPreparedCol();
		if (not)
			e = new Expression("NOT LIKE", eright);
		else
			e = new Expression("LIKE", eright);
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression IsNullClause() throws ParseException {
		boolean not = false;
		jj_consume_token(K_IS);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_NOT:
			jj_consume_token(K_NOT);
			not = true;
			break;
		default:
			jj_la1[85] = jj_gen;
			;
		}
		jj_consume_token(K_NULL);
		{
			if (true)
				return (not ? new Expression("IS NOT NULL") : new Expression(
						"IS NULL"));
		}
		throw new Error("Missing return statement in function");
	}

	// SQLSimpleExpression
	// ::= SQLMultiplicativeExpression (OP SQLMultiplicativeExpression)*
	// OP ::= + | - | "||"
	final public Exp SQLSimpleExpression() throws ParseException {
		Token op;
		Exp e1, e2;
		Expression e = null;
		e1 = SQLMultiplicativeExpression();
		label_13: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 101:
			case 102:
			case 106:
				;
				break;
			default:
				jj_la1[86] = jj_gen;
				break label_13;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 101:
				op = jj_consume_token(101);
				break;
			case 102:
				op = jj_consume_token(102);
				break;
			case 106:
				op = jj_consume_token(106);
				break;
			default:
				jj_la1[87] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			e2 = SQLMultiplicativeExpression();
			e = new Expression(op.toString(), e1);
			e.addOperand(e2);
			e1 = e;
		}
		{
			if (true)
				return e1;
		}
		throw new Error("Missing return statement in function");
	}

	// SQLMultiplicativeExpression
	// ::= SQLExpotentExpression (OP SQLExpotentExpression)*
	// OP ::= * | /
	final public Exp SQLMultiplicativeExpression() throws ParseException {
		Token op;
		Exp e1, e2;
		Expression e = null;
		e1 = SQLExpotentExpression();
		label_14: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 103:
			case 107:
				;
				break;
			default:
				jj_la1[88] = jj_gen;
				break label_14;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 103:
				op = jj_consume_token(103);
				break;
			case 107:
				op = jj_consume_token(107);
				break;
			default:
				jj_la1[89] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			e2 = SQLExpotentExpression();
			e = new Expression(op.toString(), e1);
			e.addOperand(e2);
			e1 = e;
		}
		{
			if (true)
				return e1;
		}
		throw new Error("Missing return statement in function");
	}

	// SQLExpotentExpression ::= SQLUnaryExpression (** SQLUnaryExpression)*
	final public Exp SQLExpotentExpression() throws ParseException {
		Token op;
		Exp e1, e2;
		Expression e = null;
		boolean single = true;
		e1 = SQLUnaryExpression();
		label_15: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 108:
				;
				break;
			default:
				jj_la1[90] = jj_gen;
				break label_15;
			}
			op = jj_consume_token(108);
			e2 = SQLUnaryExpression();
			if (single)
				e = new Expression(op.toString(), e1);
			single = false;
			e.addOperand(e2);
		}
		{
			if (true)
				return (single ? e1 : e);
		}
		throw new Error("Missing return statement in function");
	}

	// SQLUnaryExpression ::= [+|-] SQLPrimaryExpression
	final public Exp SQLUnaryExpression() throws ParseException {
		Token op = null;
		Exp e1, e;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 101:
		case 102:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 101:
				op = jj_consume_token(101);
				break;
			case 102:
				op = jj_consume_token(102);
				break;
			default:
				jj_la1[91] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[92] = jj_gen;
			;
		}
		e1 = SQLPrimaryExpression();
		if (op == null)
			e = e1;
		else
			e = new Expression(op.toString(), e1);
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	// 01/07/00 PY.Gibello - Added COUNT(*) to SQLPrimaryExpression
	// TBD should be Constant("COUNT(*)", Constant.STRING))
	// instead of Constant("*", Constant.COLUMNNAME) ??
	// 01/06/02 PY.Gibello - Added ALL | DISTINCT (then the column name
	// starts with "all " or "distinct " when required, which may not be the
	// fairest solution...)

	// SQLPrimaryExpression ::= NULL | OuterJoinExpression
	// | AggregateFunc ( [ ALL | DISTINCT ] TableColumn ) | TableColumn
	// | COUNT(*)
	// | <NUMBER> | <STRING> | <BIND>
	final public Exp SQLPrimaryExpression() throws ParseException {
		Token t;
		String s, s2, modifier = "";
		Exp e;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_NULL:
			jj_consume_token(K_NULL);
			{
				if (true)
					return new Constant("NULL", Constant.NULL);
			}
			break;
		default:
			jj_la1[94] = jj_gen;
			if (jj_2_12(2147483647)) {
				s = OuterJoinExpression();
				{
					if (true)
						return new Constant(s, Constant.COLUMNNAME);
				}
				// return new Expression("_NOT_SUPPORTED"); //TBD

			} else if (jj_2_13(3)) {
				jj_consume_token(K_COUNT);
				jj_consume_token(88);
				jj_consume_token(103);
				jj_consume_token(90);
				{
					if (true)
						return new Expression("COUNT", new Constant("*",
								Constant.COLUMNNAME));
				}
			} else if (jj_2_14(3)) {
				s = AggregateFunc();
				jj_consume_token(88);
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case K_ALL:
					jj_consume_token(K_ALL);
					modifier = "all ";
					break;
				case K_DISTINCT:
					jj_consume_token(K_DISTINCT);
					modifier = "distinct ";
					break;
				default:
					jj_la1[93] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
				s2 = TableColumn();
				jj_consume_token(90);
				{
					if (true)
						return new Expression(s, new Constant(modifier + s2,
								Constant.COLUMNNAME));
				}
			} else if (jj_2_15(2)) {
				e = FunctionCall();
				{
					if (true)
						return e;
				}
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case S_IDENTIFIER:
				case S_QUOTED_IDENTIFIER:
					s = TableColumn();
					{
						if (true)
							return new Constant(s, Constant.COLUMNNAME);
					}
					break;
				case S_NUMBER:
					t = jj_consume_token(S_NUMBER);
					{
						if (true)
							return new Constant(t.toString(), Constant.NUMBER);
					}
					break;
				case S_CHAR_LITERAL:
					t = jj_consume_token(S_CHAR_LITERAL);
					s = t.toString();
					if (s.startsWith("\'"))
						s = s.substring(1);
					if (s.endsWith("\'"))
						s = s.substring(0, s.length() - 1);
					{
						if (true)
							return new Constant(s, Constant.STRING);
					}
					break;
				case S_BIND:
					t = jj_consume_token(S_BIND);
					{
						if (true)
							return new Constant(t.toString(), Constant.STRING);
					}
					break;
				case 88:
					jj_consume_token(88);
					e = SQLExpression();
					jj_consume_token(90);
					{
						if (true)
							return e;
					}
					break;
				default:
					jj_la1[95] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
		}
		throw new Error("Missing return statement in function");
	}

	// AggregateFunc ::= SUM | AVG | MAX | MIN | COUNT
	// 01/07/00 PY.Gibello - Added COUNT
	final public String AggregateFunc() throws ParseException {
		Token t;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_SUM:
			t = jj_consume_token(K_SUM);
			{
				if (true)
					return t.toString();
			}
			break;
		case K_AVG:
			t = jj_consume_token(K_AVG);
			{
				if (true)
					return t.toString();
			}
			break;
		case K_MAX:
			t = jj_consume_token(K_MAX);
			{
				if (true)
					return t.toString();
			}
			break;
		case K_MIN:
			t = jj_consume_token(K_MIN);
			{
				if (true)
					return t.toString();
			}
			break;
		case K_COUNT:
			t = jj_consume_token(K_COUNT);
			{
				if (true)
					return t.toString();
			}
			break;
		default:
			jj_la1[96] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression FunctionCall() throws ParseException {
		Token t;
		String s;
		Expression e;
		Vector parm = null;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case S_IDENTIFIER:
			t = jj_consume_token(S_IDENTIFIER);
			s = t.toString();
			break;
		case K_AVG:
		case K_COUNT:
		case K_MAX:
		case K_MIN:
		case K_SUM:
			s = AggregateFunc();
			break;
		default:
			jj_la1[97] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(88);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case K_AVG:
		case K_COUNT:
		case K_MAX:
		case K_MIN:
		case K_NULL:
		case K_SUM:
		case S_NUMBER:
		case S_IDENTIFIER:
		case S_BIND:
		case S_CHAR_LITERAL:
		case S_QUOTED_IDENTIFIER:
		case 88:
		case 101:
		case 102:
		case 105:
			parm = SQLExpressionList();
			break;
		default:
			jj_la1[98] = jj_gen;
			;
		}
		jj_consume_token(90);
		int nparm = Utils.isCustomFunction(s);
		if (nparm < 0)
			nparm = (Utils.isAggregate(s) ? 1 : -1);
		if (nparm < 0) {
			if (true)
				throw new ParseException("Undefined function: " + s);
		}
		if (nparm != Utils.VARIABLE_PLIST && nparm > 0) {
			if (parm == null || parm.size() != nparm) {
				if (true)
					throw new ParseException("Function " + s + " should have "
							+ nparm + " parameter(s)");
			}
		}

		e = new Expression(s);
		e.setOperands(parm);
		{
			if (true)
				return e;
		}
		throw new Error("Missing return statement in function");
	}

	final public String OuterJoinExpression() throws ParseException {
		String s = null;
		String c = "";
		// user.table.col
		s = OracleObjectName();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 93:
			jj_consume_token(93);
			c = OracleObjectName();
			s += "." + c;
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case 93:
				jj_consume_token(93);
				c = OracleObjectName();
				s += "." + c;
				break;
			default:
				jj_la1[99] = jj_gen;
				;
			}
			break;
		default:
			jj_la1[100] = jj_gen;
			;
		}
		jj_consume_token(88);
		jj_consume_token(101);
		jj_consume_token(90);
		{
			if (true)
				return s + "(+)";
		}
		throw new Error("Missing return statement in function");
	}

	final public Query SubQuery() throws ParseException {
		Query q;
		q = SelectWithoutOrder();
		{
			if (true)
				return q;
		}
		throw new Error("Missing return statement in function");
	}

	final private boolean jj_2_1(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_1();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(0, xla);
		}
	}

	final private boolean jj_2_2(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_2();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(1, xla);
		}
	}

	final private boolean jj_2_3(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_3();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(2, xla);
		}
	}

	final private boolean jj_2_4(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_4();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(3, xla);
		}
	}

	final private boolean jj_2_5(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_5();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(4, xla);
		}
	}

	final private boolean jj_2_6(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_6();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(5, xla);
		}
	}

	final private boolean jj_2_7(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_7();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(6, xla);
		}
	}

	final private boolean jj_2_8(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_8();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(7, xla);
		}
	}

	final private boolean jj_2_9(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_9();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(8, xla);
		}
	}

	final private boolean jj_2_10(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_10();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(9, xla);
		}
	}

	final private boolean jj_2_11(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_11();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(10, xla);
		}
	}

	final private boolean jj_2_12(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_12();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(11, xla);
		}
	}

	final private boolean jj_2_13(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_13();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(12, xla);
		}
	}

	final private boolean jj_2_14(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_14();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(13, xla);
		}
	}

	final private boolean jj_2_15(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_15();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(14, xla);
		}
	}

	final private boolean jj_3_1() {
		Token xsp;
		if (jj_scan_token(88))
			return true;
		while (true) {
			xsp = jj_scanpos;
			if (jj_scan_token(88)) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(K_SELECT))
			return true;
		return false;
	}

	final private boolean jj_3R_134() {
		if (jj_3R_135())
			return true;
		return false;
	}

	final private boolean jj_3_2() {
		if (jj_3R_16())
			return true;
		return false;
	}

	final private boolean jj_3R_47() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(103)) {
			jj_scanpos = xsp;
			if (jj_scan_token(107))
				return true;
		}
		if (jj_3R_46())
			return true;
		return false;
	}

	final private boolean jj_3R_32() {
		if (jj_3R_46())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_47()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_130() {
		if (jj_3R_19())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_134())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_31() {
		if (jj_scan_token(K_NOT))
			return true;
		return false;
	}

	final private boolean jj_3R_129() {
		if (jj_3R_16())
			return true;
		return false;
	}

	final private boolean jj_3R_109() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_129()) {
			jj_scanpos = xsp;
			if (jj_3R_130())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_18() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_31())
			jj_scanpos = xsp;
		if (jj_scan_token(K_EXISTS))
			return true;
		if (jj_scan_token(88))
			return true;
		if (jj_3R_77())
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_33() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(101)) {
			jj_scanpos = xsp;
			if (jj_scan_token(102)) {
				jj_scanpos = xsp;
				if (jj_scan_token(106))
					return true;
			}
		}
		if (jj_3R_32())
			return true;
		return false;
	}

	final private boolean jj_3R_75() {
		if (jj_scan_token(K_NOT))
			return true;
		return false;
	}

	final private boolean jj_3R_19() {
		if (jj_3R_32())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_33()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_110() {
		if (jj_scan_token(89))
			return true;
		if (jj_3R_109())
			return true;
		return false;
	}

	final private boolean jj_3R_74() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_75())
			jj_scanpos = xsp;
		if (jj_3R_76())
			return true;
		return false;
	}

	final private boolean jj_3R_96() {
		if (jj_3R_109())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_110()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3_6() {
		if (jj_3R_18())
			return true;
		return false;
	}

	final private boolean jj_3R_71() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_6()) {
			jj_scanpos = xsp;
			if (jj_3R_74())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_95() {
		if (jj_scan_token(103))
			return true;
		return false;
	}

	final private boolean jj_3R_86() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_95()) {
			jj_scanpos = xsp;
			if (jj_3R_96())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_108() {
		if (jj_scan_token(K_NOT))
			return true;
		return false;
	}

	final private boolean jj_3R_72() {
		if (jj_scan_token(K_AND))
			return true;
		if (jj_3R_71())
			return true;
		return false;
	}

	final private boolean jj_3R_94() {
		if (jj_scan_token(K_DISTINCT))
			return true;
		return false;
	}

	final private boolean jj_3R_93() {
		if (jj_scan_token(K_IS))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_108())
			jj_scanpos = xsp;
		if (jj_scan_token(K_NULL))
			return true;
		return false;
	}

	final private boolean jj_3R_67() {
		if (jj_3R_71())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_72()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_45() {
		if (jj_3R_25())
			return true;
		return false;
	}

	final private boolean jj_3R_77() {
		if (jj_3R_81())
			return true;
		return false;
	}

	final private boolean jj_3R_85() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(5)) {
			jj_scanpos = xsp;
			if (jj_3R_94())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_91() {
		if (jj_3R_102())
			return true;
		return false;
	}

	final private boolean jj_3R_90() {
		if (jj_3R_101())
			return true;
		return false;
	}

	final private boolean jj_3R_37() {
		if (jj_scan_token(K_NOT))
			return true;
		return false;
	}

	final private boolean jj_3R_89() {
		if (jj_3R_100())
			return true;
		return false;
	}

	final private boolean jj_3R_22() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_37())
			jj_scanpos = xsp;
		if (jj_scan_token(K_LIKE))
			return true;
		if (jj_3R_36())
			return true;
		return false;
	}

	final private boolean jj_3R_88() {
		if (jj_3R_99())
			return true;
		return false;
	}

	final private boolean jj_3R_68() {
		if (jj_scan_token(K_OR))
			return true;
		if (jj_3R_67())
			return true;
		return false;
	}

	final private boolean jj_3R_38() {
		if (jj_scan_token(93))
			return true;
		if (jj_3R_17())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_50())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_50() {
		if (jj_scan_token(93))
			return true;
		if (jj_3R_17())
			return true;
		return false;
	}

	final private boolean jj_3R_64() {
		if (jj_3R_67())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_68()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_24() {
		if (jj_3R_17())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_38())
			jj_scanpos = xsp;
		if (jj_scan_token(88))
			return true;
		if (jj_scan_token(101))
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_81() {
		if (jj_scan_token(K_SELECT))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_85())
			jj_scanpos = xsp;
		if (jj_3R_86())
			return true;
		if (jj_3R_87())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_88())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_89())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_90())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_91())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_35() {
		if (jj_scan_token(K_NOT))
			return true;
		return false;
	}

	final private boolean jj_3R_21() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_35())
			jj_scanpos = xsp;
		if (jj_scan_token(K_BETWEEN))
			return true;
		if (jj_3R_36())
			return true;
		if (jj_scan_token(K_AND))
			return true;
		if (jj_3R_36())
			return true;
		return false;
	}

	final private boolean jj_3R_106() {
		if (jj_3R_70())
			return true;
		return false;
	}

	final private boolean jj_3R_30() {
		if (jj_scan_token(S_QUOTED_IDENTIFIER))
			return true;
		return false;
	}

	final private boolean jj_3R_107() {
		if (jj_3R_77())
			return true;
		return false;
	}

	final private boolean jj_3_5() {
		if (jj_scan_token(88))
			return true;
		return false;
	}

	final private boolean jj_3R_34() {
		if (jj_scan_token(K_NOT))
			return true;
		return false;
	}

	final private boolean jj_3R_20() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_34())
			jj_scanpos = xsp;
		if (jj_scan_token(K_IN))
			return true;
		if (jj_scan_token(88))
			return true;
		xsp = jj_scanpos;
		if (jj_3R_106()) {
			jj_scanpos = xsp;
			if (jj_3R_107())
				return true;
		}
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_66() {
		if (jj_3R_70())
			return true;
		return false;
	}

	final private boolean jj_3R_133() {
		if (jj_scan_token(K_ANY))
			return true;
		return false;
	}

	final private boolean jj_3R_118() {
		if (jj_3R_81())
			return true;
		return false;
	}

	final private boolean jj_3R_44() {
		if (jj_scan_token(S_IDENTIFIER))
			return true;
		return false;
	}

	final private boolean jj_3R_28() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_44()) {
			jj_scanpos = xsp;
			if (jj_3R_45())
				return true;
		}
		if (jj_scan_token(88))
			return true;
		xsp = jj_scanpos;
		if (jj_3R_66())
			jj_scanpos = xsp;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_117() {
		if (jj_scan_token(88))
			return true;
		if (jj_3R_81())
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_23() {
		if (jj_scan_token(88))
			return true;
		if (jj_scan_token(K_SELECT))
			return true;
		return false;
	}

	final private boolean jj_3R_116() {
		if (jj_scan_token(K_UNION))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(5))
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_53() {
		if (jj_scan_token(105))
			return true;
		return false;
	}

	final private boolean jj_3R_131() {
		if (jj_scan_token(93))
			return true;
		if (jj_3R_17())
			return true;
		return false;
	}

	final private boolean jj_3R_102() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_116()) {
			jj_scanpos = xsp;
			if (jj_scan_token(35)) {
				jj_scanpos = xsp;
				if (jj_scan_token(42))
					return true;
			}
		}
		xsp = jj_scanpos;
		if (jj_3R_117()) {
			jj_scanpos = xsp;
			if (jj_3R_118())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_27() {
		if (jj_scan_token(K_DISTINCT))
			return true;
		return false;
	}

	final private boolean jj_3R_111() {
		if (jj_3R_17())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_131())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_43() {
		if (jj_scan_token(K_COUNT))
			return true;
		return false;
	}

	final private boolean jj_3R_49() {
		if (jj_3R_53())
			return true;
		return false;
	}

	final private boolean jj_3R_42() {
		if (jj_scan_token(K_MIN))
			return true;
		return false;
	}

	final private boolean jj_3R_36() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_48()) {
			jj_scanpos = xsp;
			if (jj_3R_49())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_48() {
		if (jj_3R_19())
			return true;
		return false;
	}

	final private boolean jj_3R_41() {
		if (jj_scan_token(K_MAX))
			return true;
		return false;
	}

	final private boolean jj_3R_40() {
		if (jj_scan_token(K_AVG))
			return true;
		return false;
	}

	final private boolean jj_3R_25() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_39()) {
			jj_scanpos = xsp;
			if (jj_3R_40()) {
				jj_scanpos = xsp;
				if (jj_3R_41()) {
					jj_scanpos = xsp;
					if (jj_3R_42()) {
						jj_scanpos = xsp;
						if (jj_3R_43())
							return true;
					}
				}
			}
		}
		return false;
	}

	final private boolean jj_3R_39() {
		if (jj_scan_token(K_SUM))
			return true;
		return false;
	}

	final private boolean jj_3R_115() {
		if (jj_scan_token(K_HAVING))
			return true;
		if (jj_3R_64())
			return true;
		return false;
	}

	final private boolean jj_3_11() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(7)) {
			jj_scanpos = xsp;
			if (jj_scan_token(5)) {
				jj_scanpos = xsp;
				if (jj_3R_23())
					return true;
			}
		}
		return false;
	}

	final private boolean jj_3R_128() {
		if (jj_scan_token(K_PRIOR))
			return true;
		return false;
	}

	final private boolean jj_3R_126() {
		if (jj_scan_token(100))
			return true;
		return false;
	}

	final private boolean jj_3R_125() {
		if (jj_scan_token(99))
			return true;
		return false;
	}

	final private boolean jj_3R_105() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_128())
			jj_scanpos = xsp;
		if (jj_3R_36())
			return true;
		return false;
	}

	final private boolean jj_3R_101() {
		if (jj_scan_token(K_GROUP))
			return true;
		if (jj_scan_token(K_BY))
			return true;
		if (jj_3R_70())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_115())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_124() {
		if (jj_scan_token(98))
			return true;
		return false;
	}

	final private boolean jj_3R_123() {
		if (jj_scan_token(97))
			return true;
		return false;
	}

	final private boolean jj_3R_62() {
		if (jj_scan_token(88))
			return true;
		if (jj_3R_64())
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_122() {
		if (jj_scan_token(96))
			return true;
		return false;
	}

	final private boolean jj_3R_61() {
		if (jj_scan_token(S_BIND))
			return true;
		return false;
	}

	final private boolean jj_3R_121() {
		if (jj_scan_token(95))
			return true;
		return false;
	}

	final private boolean jj_3R_132() {
		if (jj_scan_token(K_ALL))
			return true;
		return false;
	}

	final private boolean jj_3R_127() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_132()) {
			jj_scanpos = xsp;
			if (jj_3R_133())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_120() {
		if (jj_scan_token(94))
			return true;
		return false;
	}

	final private boolean jj_3R_119() {
		if (jj_scan_token(92))
			return true;
		return false;
	}

	final private boolean jj_3R_103() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_119()) {
			jj_scanpos = xsp;
			if (jj_3R_120()) {
				jj_scanpos = xsp;
				if (jj_3R_121()) {
					jj_scanpos = xsp;
					if (jj_3R_122()) {
						jj_scanpos = xsp;
						if (jj_3R_123()) {
							jj_scanpos = xsp;
							if (jj_3R_124()) {
								jj_scanpos = xsp;
								if (jj_3R_125()) {
									jj_scanpos = xsp;
									if (jj_3R_126())
										return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	final private boolean jj_3R_114() {
		if (jj_scan_token(K_START))
			return true;
		if (jj_scan_token(K_WITH))
			return true;
		if (jj_3R_64())
			return true;
		return false;
	}

	final private boolean jj_3R_113() {
		if (jj_scan_token(K_START))
			return true;
		if (jj_scan_token(K_WITH))
			return true;
		if (jj_3R_64())
			return true;
		return false;
	}

	final private boolean jj_3R_104() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_127())
			jj_scanpos = xsp;
		if (jj_scan_token(88))
			return true;
		if (jj_3R_77())
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_60() {
		if (jj_scan_token(S_CHAR_LITERAL))
			return true;
		return false;
	}

	final private boolean jj_3R_59() {
		if (jj_scan_token(S_NUMBER))
			return true;
		return false;
	}

	final private boolean jj_3R_100() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_113())
			jj_scanpos = xsp;
		if (jj_scan_token(K_CONNECT))
			return true;
		if (jj_scan_token(K_BY))
			return true;
		if (jj_3R_64())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_114())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_58() {
		if (jj_3R_63())
			return true;
		return false;
	}

	final private boolean jj_3R_29() {
		if (jj_scan_token(S_IDENTIFIER))
			return true;
		return false;
	}

	final private boolean jj_3R_17() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_29()) {
			jj_scanpos = xsp;
			if (jj_3R_30())
				return true;
		}
		return false;
	}

	final private boolean jj_3_15() {
		if (jj_3R_28())
			return true;
		return false;
	}

	final private boolean jj_3R_26() {
		if (jj_scan_token(K_ALL))
			return true;
		return false;
	}

	final private boolean jj_3_12() {
		if (jj_3R_24())
			return true;
		return false;
	}

	final private boolean jj_3R_92() {
		if (jj_3R_103())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_104()) {
			jj_scanpos = xsp;
			if (jj_3R_105())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_99() {
		if (jj_scan_token(K_WHERE))
			return true;
		if (jj_3R_64())
			return true;
		return false;
	}

	final private boolean jj_3_14() {
		if (jj_3R_25())
			return true;
		if (jj_scan_token(88))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_26()) {
			jj_scanpos = xsp;
			if (jj_3R_27())
				return true;
		}
		if (jj_3R_63())
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_69() {
		if (jj_scan_token(93))
			return true;
		if (jj_3R_17())
			return true;
		return false;
	}

	final private boolean jj_3R_65() {
		if (jj_scan_token(93))
			return true;
		if (jj_3R_17())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_69())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3_13() {
		if (jj_scan_token(K_COUNT))
			return true;
		if (jj_scan_token(88))
			return true;
		if (jj_scan_token(103))
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_63() {
		if (jj_3R_17())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_65())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_57() {
		if (jj_3R_24())
			return true;
		return false;
	}

	final private boolean jj_3R_112() {
		if (jj_scan_token(S_IDENTIFIER))
			return true;
		return false;
	}

	final private boolean jj_3R_55() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_56()) {
			jj_scanpos = xsp;
			if (jj_3R_57()) {
				jj_scanpos = xsp;
				if (jj_3_13()) {
					jj_scanpos = xsp;
					if (jj_3_14()) {
						jj_scanpos = xsp;
						if (jj_3_15()) {
							jj_scanpos = xsp;
							if (jj_3R_58()) {
								jj_scanpos = xsp;
								if (jj_3R_59()) {
									jj_scanpos = xsp;
									if (jj_3R_60()) {
										jj_scanpos = xsp;
										if (jj_3R_61()) {
											jj_scanpos = xsp;
											if (jj_3R_62())
												return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	final private boolean jj_3R_56() {
		if (jj_scan_token(K_NULL))
			return true;
		return false;
	}

	final private boolean jj_3R_73() {
		if (jj_scan_token(89))
			return true;
		if (jj_3R_36())
			return true;
		return false;
	}

	final private boolean jj_3R_97() {
		if (jj_3R_111())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_112())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_70() {
		if (jj_3R_36())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_73()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_98() {
		if (jj_scan_token(89))
			return true;
		if (jj_3R_97())
			return true;
		return false;
	}

	final private boolean jj_3R_87() {
		if (jj_scan_token(K_FROM))
			return true;
		if (jj_3R_97())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_98()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_84() {
		if (jj_3R_93())
			return true;
		return false;
	}

	final private boolean jj_3_10() {
		if (jj_3R_22())
			return true;
		return false;
	}

	final private boolean jj_3_9() {
		if (jj_3R_21())
			return true;
		return false;
	}

	final private boolean jj_3_8() {
		if (jj_3R_20())
			return true;
		return false;
	}

	final private boolean jj_3R_54() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(101)) {
			jj_scanpos = xsp;
			if (jj_scan_token(102))
				return true;
		}
		return false;
	}

	final private boolean jj_3R_83() {
		if (jj_3R_92())
			return true;
		return false;
	}

	final private boolean jj_3R_80() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_83()) {
			jj_scanpos = xsp;
			if (jj_3_8()) {
				jj_scanpos = xsp;
				if (jj_3_9()) {
					jj_scanpos = xsp;
					if (jj_3_10()) {
						jj_scanpos = xsp;
						if (jj_3R_84())
							return true;
					}
				}
			}
		}
		return false;
	}

	final private boolean jj_3R_51() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_54())
			jj_scanpos = xsp;
		if (jj_3R_55())
			return true;
		return false;
	}

	final private boolean jj_3_4() {
		if (jj_3R_17())
			return true;
		if (jj_scan_token(93))
			return true;
		if (jj_3R_17())
			return true;
		if (jj_scan_token(104))
			return true;
		return false;
	}

	final private boolean jj_3_3() {
		if (jj_3R_17())
			return true;
		if (jj_scan_token(104))
			return true;
		return false;
	}

	final private boolean jj_3R_16() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_3()) {
			jj_scanpos = xsp;
			if (jj_3_4())
				return true;
		}
		return false;
	}

	final private boolean jj_3R_82() {
		if (jj_scan_token(K_PRIOR))
			return true;
		return false;
	}

	final private boolean jj_3_7() {
		if (jj_scan_token(88))
			return true;
		if (jj_3R_19())
			return true;
		if (jj_scan_token(89))
			return true;
		return false;
	}

	final private boolean jj_3R_136() {
		if (jj_scan_token(S_QUOTED_IDENTIFIER))
			return true;
		return false;
	}

	final private boolean jj_3R_79() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_82())
			jj_scanpos = xsp;
		if (jj_3R_19())
			return true;
		return false;
	}

	final private boolean jj_3R_52() {
		if (jj_scan_token(108))
			return true;
		if (jj_3R_51())
			return true;
		return false;
	}

	final private boolean jj_3R_138() {
		if (jj_scan_token(S_IDENTIFIER))
			return true;
		return false;
	}

	final private boolean jj_3R_46() {
		if (jj_3R_51())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_52()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_137() {
		Token xsp;
		if (jj_3R_138())
			return true;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_138()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	final private boolean jj_3R_78() {
		if (jj_scan_token(88))
			return true;
		if (jj_3R_70())
			return true;
		if (jj_scan_token(90))
			return true;
		return false;
	}

	final private boolean jj_3R_76() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_78()) {
			jj_scanpos = xsp;
			if (jj_3R_79())
				return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_80())
			jj_scanpos = xsp;
		return false;
	}

	final private boolean jj_3R_135() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(8))
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_136()) {
			jj_scanpos = xsp;
			if (jj_3R_137())
				return true;
		}
		return false;
	}

	public JJParserTokenManager token_source;
	SimpleCharStream jj_input_stream;
	public Token token, jj_nt;
	private int jj_ntk;
	private Token jj_scanpos, jj_lastpos;
	private int jj_la;
	public boolean lookingAhead = false;
	private boolean jj_semLA;
	private int jj_gen;
	final private int[] jj_la1 = new int[101];
	static private int[] jj_la1_0;
	static private int[] jj_la1_1;
	static private int[] jj_la1_2;
	static private int[] jj_la1_3;
	static {
		jj_la1_0();
		jj_la1_1();
		jj_la1_2();
		jj_la1_3();
	}

	private static void jj_la1_0() {
		jj_la1_0 = new int[] { 0x8008000, 0x0, 0x0, 0x810b000, 0x4220000,
				0x4000000, 0x4220000, 0x0, 0x10000, 0x0, 0x0, 0x0, 0x10000,
				0x0, 0x1000000, 0x0, 0x0, 0x1000000, 0x0, 0x0, 0x0, 0x2080400,
				0x0, 0x0, 0x0, 0x20000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x10000000, 0x800020, 0x800020, 0x0, 0x40000,
				0x40000000, 0x0, 0x0, 0x80400, 0x100, 0x80400, 0x100, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x80000000, 0x20, 0x0, 0x0, 0x400200,
				0x400200, 0x0, 0x400200, 0x400200, 0x0, 0x0, 0x0, 0x40, 0x0,
				0x80400, 0x0, 0x0, 0x80400, 0x0, 0x0, 0x800, 0x0, 0xa0, 0xa0,
				0x0, 0x80400, 0x80400, 0x0, 0x80400, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x800020, 0x0, 0x0, 0x80400, 0x80400,
				0x80400, 0x0, 0x0, };
	}

	private static void jj_la1_1() {
		jj_la1_1 = new int[] { 0x1011004, 0x0, 0x0, 0x1011004, 0x1a400082,
				0x400000, 0x1a400082, 0x0, 0x0, 0x0, 0x4000, 0x0, 0x0, 0x40000,
				0x20000000, 0x4000000, 0x4000000, 0x24000000, 0x0, 0x0, 0x0,
				0x20a300, 0x0, 0x0, 0x8000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x100000, 0x0, 0x0, 0x0, 0x0, 0x80000000,
				0x0, 0x408, 0x0, 0x8300, 0x0, 0x8300, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x80000000, 0x80000000, 0x0, 0x0, 0x408, 0x8000000, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x20000, 0x80000, 0x0, 0x2000, 0x20a300,
				0x2000, 0x200000, 0x208300, 0x0, 0x20, 0x2061, 0x0, 0x0, 0x0,
				0x200000, 0x208300, 0x8300, 0x2000, 0x8008300, 0x2000, 0x2000,
				0x2000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x8000, 0x0,
				0x300, 0x300, 0x8300, 0x0, 0x0, };
	}

	private static void jj_la1_2() {
		jj_la1_2 = new int[] { 0xc0, 0x2000000, 0x1000000, 0xc0, 0x10, 0x0,
				0x10, 0x400, 0x0, 0x2000000, 0x0, 0x400, 0x0, 0x800, 0x0, 0x10,
				0x10, 0x0, 0x40000, 0x100, 0x2000000, 0x1e41001, 0x2000000,
				0x1000000, 0x20, 0x0, 0x100, 0x20000000, 0x20000000, 0x840000,
				0xd0000000, 0x20000000, 0x0, 0x0, 0x41000, 0x0, 0x0, 0x0, 0x0,
				0x100, 0x0, 0x0, 0x8, 0x2000000, 0x1e41001, 0x840000,
				0x1e41001, 0x0, 0x40000, 0x840000, 0x2000000, 0x40000, 0x0,
				0x0, 0x0, 0x0, 0x8, 0x0, 0x0, 0x0, 0x2000000, 0x0, 0x0,
				0x2000000, 0x0, 0x0, 0x0, 0x0, 0x1e41001, 0x0, 0x0, 0x1e41001,
				0xd0000000, 0x0, 0xd0000000, 0x2000000, 0x0, 0x0, 0x0,
				0x1e41001, 0x1e41001, 0x0, 0x1e41001, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1e41000, 0x1, 0x40001,
				0x1e41001, 0x20000000, 0x20000000, };
	}

	private static void jj_la1_3() {
		jj_la1_3 = new int[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x260, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1f, 0x0, 0x60,
				0x60, 0x60, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xe0,
				0x0, 0x60, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x60,
				0x0, 0x0, 0x60, 0x1f, 0x0, 0x1f, 0x0, 0x0, 0x0, 0x0, 0x260,
				0x260, 0x0, 0x260, 0x0, 0x0, 0x0, 0x460, 0x460, 0x880, 0x880,
				0x1000, 0x60, 0x60, 0x0, 0x0, 0x0, 0x0, 0x0, 0x260, 0x0, 0x0, };
	}

	final private JJCalls[] jj_2_rtns = new JJCalls[15];
	private boolean jj_rescan = false;
	private int jj_gc = 0;

	public JJParser(java.io.InputStream stream) {
		this(stream, null);
	}

	public JJParser(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new JJParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 101; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 101; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public JJParser(java.io.Reader stream) {
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new JJParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 101; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 101; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public JJParser(JJParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 101; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public void ReInit(JJParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 101; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	final private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			if (++jj_gc > 100) {
				jj_gc = 0;
				for (int i = 0; i < jj_2_rtns.length; i++) {
					JJCalls c = jj_2_rtns[i];
					while (c != null) {
						if (c.gen < jj_gen)
							c.first = null;
						c = c.next;
					}
				}
			}
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	static private final class LookaheadSuccess extends java.lang.Error {
	}

	final private LookaheadSuccess jj_ls = new LookaheadSuccess();

	final private boolean jj_scan_token(int kind) {
		if (jj_scanpos == jj_lastpos) {
			jj_la--;
			if (jj_scanpos.next == null) {
				jj_lastpos = jj_scanpos = jj_scanpos.next = token_source
						.getNextToken();
			} else {
				jj_lastpos = jj_scanpos = jj_scanpos.next;
			}
		} else {
			jj_scanpos = jj_scanpos.next;
		}
		if (jj_rescan) {
			int i = 0;
			Token tok = token;
			while (tok != null && tok != jj_scanpos) {
				i++;
				tok = tok.next;
			}
			if (tok != null)
				jj_add_error_token(kind, i);
		}
		if (jj_scanpos.kind != kind)
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			throw jj_ls;
		return false;
	}

	final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	final public Token getToken(int index) {
		Token t = lookingAhead ? jj_scanpos : token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	final private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	private java.util.Vector jj_expentries = new java.util.Vector();
	private int[] jj_expentry;
	private int jj_kind = -1;
	private int[] jj_lasttokens = new int[100];
	private int jj_endpos;

	private void jj_add_error_token(int kind, int pos) {
		if (pos >= 100)
			return;
		if (pos == jj_endpos + 1) {
			jj_lasttokens[jj_endpos++] = kind;
		} else if (jj_endpos != 0) {
			jj_expentry = new int[jj_endpos];
			for (int i = 0; i < jj_endpos; i++) {
				jj_expentry[i] = jj_lasttokens[i];
			}
			boolean exists = false;
			for (java.util.Enumeration e = jj_expentries.elements(); e
					.hasMoreElements();) {
				int[] oldentry = (int[]) (e.nextElement());
				if (oldentry.length == jj_expentry.length) {
					exists = true;
					for (int i = 0; i < jj_expentry.length; i++) {
						if (oldentry[i] != jj_expentry[i]) {
							exists = false;
							break;
						}
					}
					if (exists)
						break;
				}
			}
			if (!exists)
				jj_expentries.addElement(jj_expentry);
			if (pos != 0)
				jj_lasttokens[(jj_endpos = pos) - 1] = kind;
		}
	}

	public ParseException generateParseException() {
		jj_expentries.removeAllElements();
		boolean[] la1tokens = new boolean[109];
		for (int i = 0; i < 109; i++) {
			la1tokens[i] = false;
		}
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 101; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
					if ((jj_la1_1[i] & (1 << j)) != 0) {
						la1tokens[32 + j] = true;
					}
					if ((jj_la1_2[i] & (1 << j)) != 0) {
						la1tokens[64 + j] = true;
					}
					if ((jj_la1_3[i] & (1 << j)) != 0) {
						la1tokens[96 + j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 109; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.addElement(jj_expentry);
			}
		}
		jj_endpos = 0;
		jj_rescan_token();
		jj_add_error_token(0, 0);
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = (int[]) jj_expentries.elementAt(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	final public void enable_tracing() {
	}

	final public void disable_tracing() {
	}

	final private void jj_rescan_token() {
		jj_rescan = true;
		for (int i = 0; i < 15; i++) {
			try {
				JJCalls p = jj_2_rtns[i];
				do {
					if (p.gen > jj_gen) {
						jj_la = p.arg;
						jj_lastpos = jj_scanpos = p.first;
						switch (i) {
						case 0:
							jj_3_1();
							break;
						case 1:
							jj_3_2();
							break;
						case 2:
							jj_3_3();
							break;
						case 3:
							jj_3_4();
							break;
						case 4:
							jj_3_5();
							break;
						case 5:
							jj_3_6();
							break;
						case 6:
							jj_3_7();
							break;
						case 7:
							jj_3_8();
							break;
						case 8:
							jj_3_9();
							break;
						case 9:
							jj_3_10();
							break;
						case 10:
							jj_3_11();
							break;
						case 11:
							jj_3_12();
							break;
						case 12:
							jj_3_13();
							break;
						case 13:
							jj_3_14();
							break;
						case 14:
							jj_3_15();
							break;
						}
					}
					p = p.next;
				} while (p != null);
			} catch (LookaheadSuccess ls) {
			}
		}
		jj_rescan = false;
	}

	final private void jj_save(int index, int xla) {
		JJCalls p = jj_2_rtns[index];
		while (p.gen > jj_gen) {
			if (p.next == null) {
				p = p.next = new JJCalls();
				break;
			}
			p = p.next;
		}
		p.gen = jj_gen + xla - jj_la;
		p.first = token;
		p.arg = xla;
	}

	static final class JJCalls {
		int gen;
		Token first;
		int arg;
		JJCalls next;
	}

	// main ends here

}