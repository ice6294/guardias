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

/**
 * @author luis
 * @date 12-12-2016
 */
public class WeekTemplate implements Serializable {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;

	private List<DayTemplate> dayTemplates;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public WeekTemplate() {
		this(-1, new ArrayList());
	}
	
	public WeekTemplate(Integer id, List<DayTemplate> dayTemplates) {
		this.id = id;
		this.dayTemplates = dayTemplates;
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

	public List<DayTemplate> getDayTemplates() {
		return dayTemplates;
	}

	public void setDayTemplates(List<DayTemplate> dayTemplates) {
		this.dayTemplates = dayTemplates;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"dayTemplates\":[";
		for (int i = 0; i < this.getDayTemplates().size(); i++) {
			str += "{\"id\":" + this.getDayTemplates().get(i).toJSON() + "}";
			if (i < (this.getDayTemplates().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "]";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
