/**
 * 
 */
package jsql.data;

import java.io.Serializable;

/**
 * @author tmkhanh
 *
 */
public class Column implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String description;
	private boolean primary = false;
	public Column(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public Column(String name, String type, String description, boolean primary) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.primary = primary;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
	@SuppressWarnings("rawtypes")
	public Class getClassType() {
		if (type.equalsIgnoreCase("INT")) return IntType.class;
		if (type.equalsIgnoreCase("STRING")) return StringType.class;
		return null;
	}
	
	// added by khuong
	
	public Column(){
		
	}
}
