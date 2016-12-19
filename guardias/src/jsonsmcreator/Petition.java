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
public class Petition {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private Integer level;
	private String reason;
	private Integer petitionId;
	private Integer userId;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Petition() {
		this(-1, -1, -1);
	}
	
	public Petition(Integer id, Integer petitionId, Integer userId) {
		this.id = id;
		this.level = 0;
		this.reason = "";
		this.petitionId = petitionId;
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
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getPetitionId() {
		return petitionId;
	}

	public void setPetitionId(Integer petitionId) {
		this.petitionId = petitionId;
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
		str += "\"level\":" + this.getLevel() + ",";
		str += "\"reason\":\"" + this.getReason() + "\",";
		str += "\"resident\":{\"id\":" + this.getUserId()+ "},";
		str += "\"petType\":{\"id\":" + this.getPetitionId()+ "}";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
