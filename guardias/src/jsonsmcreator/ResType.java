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
import java.util.ArrayList;
import java.util.List;
import static jsonsmcreator.Utils.toIntArray;

/**
 * @author luis
 * @date 12-12-2016
 */
public class ResType implements Serializable {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	private String name;
	private List<Integer> roles;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public ResType() {
		this(-1, "", new ArrayList());
	}
	
	public ResType(Integer id, String name, String roles) {
		this(id, name, toIntArray(roles));
	}
	
	public ResType(Integer id, String name, List<Integer> roles) {
		this.id = id;
		this.name = name;
		this.roles = roles;
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

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"name\":\"" + this.getName() + "\",";
		str += "\"roles\":[";
		for (int i = 0; i < this.getRoles().size(); i++) {
			str += "{\"id\":" + this.getRoles().get(i) + "}";
			if (i < (this.getRoles().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "]";
		str += "}";
		return str;
	}
	// </editor-fold>

}
