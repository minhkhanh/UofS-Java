/**
 * 
 */
package jsql.parse;

import java.io.Serializable;

import jsql.data.Database;

/**
 * @author tmkhanh
 *
 */
public abstract class Statement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Database database =null;

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
}
