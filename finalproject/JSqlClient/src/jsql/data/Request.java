package jsql.data;

import java.io.Serializable;
import java.util.Vector;

public class Request implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String querys = null;
	public Request(String querys) {
		this.querys = querys;
	}
	
	public static Vector<Request> create(String statement) throws Exception {
		if (statement==null || statement.length()==0) throw new Exception("Câu lệnh rỗng!");
		try {
			Vector<Request> querys = new Vector<Request>();
			String[] listQ = statement.split("\n");
			for (String string : listQ) {
				if (string.trim().length()>0) querys.add(new Request(string));
			}
			if (querys.size()==0) throw new Exception("Câu lệnh rỗng!");
			return querys;
		} catch (Exception e) {
			throw new Exception("Câu lệnh rỗng!");
		}
	}
	
	public String getQuery() {
		return this.querys;
	}
	
	public void setQuery(String query) {
		this.querys = query;
	}
}
