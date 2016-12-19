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
public class Exception {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private Integer ruleId;
	private Integer userId;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Exception() {
		this(-1, 0, -1);
	}
	
	public Exception(Integer id, Integer userId) {
		this(id, 0, userId);
	}
	
	public Exception(Integer id, Integer ruleId, Integer userId) {
		this.id = id;
		this.ruleId = ruleId;
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

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
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
		str += "\"rule\":{\"id\":" + this.getRuleId() + "},";
		str += "\"resident\":{\"id\":" + this.getUserId()+ "}";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
