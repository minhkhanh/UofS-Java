package jsql.parse;
/**
 * @author tmkhanh
 *
 */
public class Parser {
	public static Statement parseStatement(String sqlStatement) {
		Statement statement;
		try {
			//insert
			statement = Insert.createStatement(sqlStatement);
			if (statement!=null) return statement;
		} catch (Exception e) {
			//System.out.println("ko phai lenh insert");
		}

		try {
			//delete
			statement = Delete.createStatement(sqlStatement);
			if (statement!=null) return statement;
		} catch (Exception e) {
			//System.out.println("ko phai lenh delete");
		}
		
		try {
			//update
			statement = Update.createStatement(sqlStatement);
			if (statement!=null) return statement;
		} catch (Exception e) {
			//System.out.println("ko phai lenh update");
		}
		
		try {
			//select
			statement = Select.createStatement(sqlStatement);
			if (statement!=null) return statement;
		} catch (Exception e) {
			//System.out.println("ko phai lenh select");
		}
		
		
		System.out.println("lenh sql chua dc ho tro");
		return null;
	}
	
}
