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
public class Calendar {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private Integer year;
	private Integer month;
	private Integer seed;
	private Boolean fixed;

	private Integer inCharge; // non used attribute outside the class
	
	private List<Day> days;
	private List<FixRes> residents;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Calendar() {
		this(-1, -1, -1, -1, true, new ArrayList(), new ArrayList());
	}
	
	public Calendar(Integer id, Integer year, Integer month, Integer seed,
			Boolean fixed, List<Day> days, List<FixRes> residents) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.seed = seed;
		this.fixed = fixed;
		this.inCharge = -1;
		this.days = days;
		this.residents = residents;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
	}

	public Boolean getFixed() {
		return fixed;
	}

	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}

	public Integer getInCharge() {
		return inCharge;
	}

	public void setInCharge(Integer inCharge) {
		this.inCharge = inCharge;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public List<FixRes> getResidents() {
		return residents;
	}
	
	public void setResidents(List<FixRes> residents) {	
		this.residents = residents;
	}

	// </editor-fold>
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		// Basic
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"year\":" + this.getYear() + ",";
		str += "\"month\":" + this.getMonth() + ",";
		str += "\"seed\":" + this.getSeed() + ",";
		str += "\"fixed\":" + this.getFixed() + ",";
		// InCharge
		str += "\"inCharge\":{\"id\":-1},";
		// Days
		str += "\"days\":[";
		for (int i = 0; i < this.getDays().size(); i++) {
			str += this.getDays().get(i).toJSON();
			if (i < (this.getDays().size() - 1))
				str += ",";
		}
		str += "],";
		// Residents
		str += "\"residents\":[";
		for (int i = 0; i < this.getResidents().size(); i++) {
			str += this.getResidents().get(i).toJSON();
			if (i < (this.getResidents().size() - 1))
				str += ",";
		}
		str += "]";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
