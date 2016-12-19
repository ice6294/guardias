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
public class Member implements Serializable {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	private Boolean main;
	private Integer role;

	private Integer userId;
	private Integer userYear;
	private String userName;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userSpeciality;
	// </editor-fold>

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Member() {
		this(-1, true, -1, -1, -1, "", "", "", "", "");
	}
	
	public Member(Integer id, Boolean main, Integer role, Integer userId,
			Integer userYear, String userName, String userFirstName,
			String userLastName, String userEmail, String userSpeciality) {
		this.id = id;
		this.main = main;
		this.role = role;
		this.userId = userId;
		this.userYear = userYear;
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userSpeciality = userSpeciality;
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

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
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

	public Integer getUserYear() {
		return userYear;
	}

	public void setUserYear(Integer userYear) {
		this.userYear = userYear;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserSpeciality() {
		return userSpeciality;
	}

	public void setUserSpeciality(String userSpeciality) {
		this.userSpeciality = userSpeciality;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"main\":" + this.getMain() + ",";
		str += "\"role\":{\"id\":" + this.getRole() + "},";
		str += "\"user\":{";
		str += "\"id\":" + this.getUserId() + ",";
		str += "\"year\":" + this.getUserYear() + ",";
		str += "\"userName\":\"" + this.getUserName() + "\",";
		str += "\"firstName\":\"" + this.getUserFirstName() + "\",";
		str += "\"lastName\":\"" + this.getUserLastName() + "\",";
		str += "\"email\":\"" + this.getUserEmail() + "\",";
		str += "\"speciality\":\"" + this.getUserSpeciality() + "\"";
		str += "}";
		str += "}";
		return str;
	}
	// </editor-fold>

}
