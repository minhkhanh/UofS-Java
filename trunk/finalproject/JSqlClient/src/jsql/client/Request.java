package jsql.client;

import java.io.Serializable;

public class Request implements Serializable {
	private String Query;
	
	public Request(String query) {
		this.Query = query;
	}
	
	public String getQuery() {
		return this.Query;
	}
	
	public void setQuery(String query) {
		this.Query = query;
	}
}
