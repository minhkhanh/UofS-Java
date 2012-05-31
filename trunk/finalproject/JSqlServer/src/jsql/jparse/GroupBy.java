package jsql.jparse;

import java.util.Vector;

/**
 * GroupBy: an SQL GROUP BY...HAVING clause
 */
public class GroupBy implements java.io.Serializable {

  Vector groupby_;
  Exp having_ = null;

  /**
   * Create a GROUP BY given a set of Expressions
   * @param exps A vector of SQL Expressions (Exp objects).
   */
  public GroupBy(Vector exps) { groupby_ = exps; }

  /**
   * Initiallize the HAVING part of the GROUP BY
   * @param e An SQL Expression (the HAVING clause)
   */
  public void setHaving(Exp e) { having_ = e; }

  /**
   * Get the GROUP BY expressions
   * @return A vector of SQL Expressions (Exp objects)
   */
  public Vector getGroupBy() { return groupby_; }

  /**
   * Get the HAVING clause
   * @return An SQL expression
   */
  public Exp getHaving() { return having_; }

  public String toString() {
    StringBuffer buf = new StringBuffer("group by ");

    //buf.append(groupby_.toString());
    buf.append(groupby_.elementAt(0).toString());
    for(int i=1; i<groupby_.size(); i++) {
      buf.append(", " + groupby_.elementAt(i).toString());
    }
    if(having_ != null) {
      buf.append(" having " + having_.toString());
    }
    return buf.toString();
  }
}