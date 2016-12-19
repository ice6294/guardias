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

import java.util.ArrayList;
import java.util.List;

/**
 * @author luis
 * @date 12-12-2016
 */
public class Day {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private String date;
	private List<Assign> assigns;
	private List<Exception> exceptions;
	private List<Petition> petitions;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Day() {
		this(-1, "", new ArrayList(), new ArrayList(), new ArrayList());
	}
	
	public Day(Integer id, String date, List<Assign> assigns,
			List<Exception> exceptions, List<Petition> petitions) {
		this.id = id;
		this.date = date;
		this.assigns = assigns;
		this.exceptions = exceptions;
		this.petitions = petitions;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Assign> getAssigns() {
		return assigns;
	}

	public void setAssigns(List<Assign> assigns) {
		this.assigns = assigns;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}

	public List<Petition> getPetitions() {
		return petitions;
	}

	public void setPetitions(List<Petition> petitions) {
		this.petitions = petitions;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		// Basic
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"date\":\"" + this.getDate() + "\",";
		// Assigns
		str += "\"assigns\":[";
		for (int i = 0; i < this.getAssigns().size(); i++) {
			str += this.getAssigns().get(i).toJSON();
			if (i < (this.getAssigns().size() - 1))
				str += ",";
		}
		str += "],";
		// Exceptions
		str += "\"exceptions\":[";
		for (int i = 0; i < this.getExceptions().size(); i++) {
			str += this.getExceptions().get(i).toJSON();
			if (i < (this.getExceptions().size() - 1))
				str += ",";
		}
		str += "],";
		// Petitions
		str += "\"petitions\":[";
		for (int i = 0; i < this.getPetitions().size(); i++) {
			str += this.getPetitions().get(i).toJSON();
			if (i < (this.getPetitions().size() - 1))
				str += ",";
		}
		str += "]";
		str += "}";
		return str;
	}
	// </editor-fold>

}
