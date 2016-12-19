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

/**
 * @author luis
 * @date 12-12-2016
 */
public class FixRes {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private Integer role;
	private Integer userId;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public FixRes() {
		this(-1, -1, -1);
	}
	
	public FixRes(Integer id, Integer role, Integer userId) {
		this.id = id;
		this.role = role;
		this.userId = userId;
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

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"role\":{\"id\":" + this.getRole() + "},";
		str += "\"resident\":{\"id\":" + this.getUserId() + "}";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
