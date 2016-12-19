/**
 *                ███
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███   █  █  █  █        ██
 *                ███    ▀▀ ▀▀ ▀▀█        ██
 * ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄███▄▄▄▄▄▄▄▄▄▄▄▄█▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *     ▀▀▄▄       ███            █        ██
 *        ▀▀▄▄   ███▀            █        ██
 *           ▀▀███▀              █        ██
 *                               █▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *      Copyright (c) 2016       █  ▀▀▄▄  ██
 *      All right reserved       █     ▀▀██▀
 *                               ▀
 */
package jsonsmcreator;

import java.io.Serializable;

/**
 * @author luis
 * @date 12-12-2016
 */
public class Job implements Serializable {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	private String name;
	private String color;
	private String bColor;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Job() {
		this(-1, "", "", "");
	}
	
	public Job(Integer id, String name, String color, String bColor) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.bColor = bColor;
	}
	// </editor-fold>
	
	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBColor() {
		return bColor;
	}

	public void setBColor(String bColor) {
		this.bColor = bColor;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"name\":\"" + this.getName() + "\",";
		str += "\"color\":\"" + this.getColor() + "\",";
		str += "\"bColor\":\"" + this.getBColor() + "\"";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
