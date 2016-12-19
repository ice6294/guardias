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
public class DayTemplate implements Serializable {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;

	private List<Integer> assignTypes; // ~ [ assignTypeID ]
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public DayTemplate() {
		this(-1, new ArrayList());
	}
	
	public DayTemplate(Integer id, String assignTypes) {
		this(id, toIntArray(assignTypes));
	}
	
	public DayTemplate(Integer id, List<Integer> assignTypes) {
		this.id = id;
		this.assignTypes = assignTypes;
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

	public List<Integer> getAssignTypes() {
		return assignTypes;
	}

	public void setAssignTypes(List<Integer> assignTypes) {
		this.assignTypes = assignTypes;
	}
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"assignTypes\":[";
		for (int i = 0; i < this.getAssignTypes().size(); i++) {
			str += "{\"id\":" + this.getAssignTypes().get(i) + "}";
			if (i < (this.getAssignTypes().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "]";
		str += "}";
		return str;
	}
	// </editor-fold>
	
}
