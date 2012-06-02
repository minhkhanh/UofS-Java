package jsql.parse;

public class Parser {
	public static Statement parseStatement(String sqlStatement) {
		Statement statement;
		
		//insert
		statement = Insert.createStatement(sqlStatement);
		if (statement!=null) return statement;
		
		
		return null;
	}
	
}
