package jsql.jparse;

/**
 * ZFromItem: an SQL FROM clause (example: the FROM part of a SELECT...FROM).
 */
public class FromItem extends AliasedName {

	/**
	 * Create a new FROM clause. See the ZAliasedName constructor for more
	 * information.
	 */
	public FromItem() {
		super();
	}

	/**
	 * Create a new FROM clause on a given table. See the ZAliasedName
	 * constructor for more information.
	 * 
	 * @param fullname
	 *            the table name.
	 */
	public FromItem(String fullname) {
		super(fullname, AliasedName.FORM_TABLE);
	}

}