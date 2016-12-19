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
public class AssignType implements Serializable {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private Integer jobId; // ~ jobID
	private List<Integer> resTypes; // ~ [ resTypeID ]
	private List<Integer> roles;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public AssignType() {
		this(-1, -1, new ArrayList(), new ArrayList());
	}
	
	public AssignType(Integer id, Integer jobId, String resTypes, String roles) {
		this(id, jobId, toIntArray(resTypes), toIntArray(roles));
	}
	
	public AssignType(Integer id, Integer jobId, List<Integer> resTypes, List<Integer> roles) {
		this.id = id;
		this.jobId = jobId;
		this.resTypes = resTypes;
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

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public List<Integer> getResTypes() {
		return resTypes;
	}

	public void setResTypes(List<Integer> resTypes) {
		this.resTypes = resTypes;
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
		str += "\"job\":{\"id\":" + this.getJobId() + "},";
		str += "\"resTypes\":[";
		for (int i = 0; i < this.getResTypes().size(); i++) {
			str += "{\"id\":" + this.getResTypes().get(i) + "}";
			if (i < (this.getResTypes().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "],";
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
