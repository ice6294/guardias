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
public class Module implements Serializable {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	private Integer year;
	private String name;
	private String extendedName;
	private String description;
	private String dayGroups;

	private Integer hospitalId;
	private String hospitalName;
	private String hospitalLocation;

	private Integer admin; // ~ userID

	private List<Member> members;
	
	private List<Rule> rules;
	private List<Job> jobs;
	private List<ResType> resTypes;
	private List<AssignType> assignTypes;
	private List<PetitionType> petitionTypes;

	private DayTemplate dayTemplate;
	private WeekTemplate weekTemplate;
	// </editor-fold>

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Module() {
		this(-1, -1, "", "", "", "", -1, "", "", -1, new ArrayList(),
				new ArrayList(), new ArrayList(), new ArrayList(),
				new ArrayList(), new ArrayList(),
				new DayTemplate(), new WeekTemplate());
	}
	
	public Module(Integer id, Integer year, String name, String extendedName,
			String description, String dayGroups, Integer hospitalId,
			String hospitalName, String hospitalLocation, Integer admin,
			List<Member> members, List<Rule> rules, List<Job> jobs,
			List<ResType> resTypes, List<AssignType> assignTypes,
			List<PetitionType> petitionTypes, DayTemplate day,
			WeekTemplate week) {
		this.id = id;
		this.year = year;
		this.name = name;
		this.extendedName = extendedName;
		this.description = description;
		this.dayGroups = dayGroups;
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.hospitalLocation = hospitalLocation;
		this.admin = admin;
		this.members = members;
		this.rules = rules;
		this.jobs = jobs;
		this.resTypes = resTypes;
		this.assignTypes = assignTypes;
		this.petitionTypes = petitionTypes;
		this.dayTemplate = day;
		this.weekTemplate = week;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtendedName() {
		return extendedName;
	}

	public void setExtendedName(String extendedName) {
		this.extendedName = extendedName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDayGroups() {
		return dayGroups;
	}

	public void setDayGroups(String dayGroups) {
		this.dayGroups = dayGroups;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalLocation() {
		return hospitalLocation;
	}

	public void setHospitalLocation(String hospitalLocation) {
		this.hospitalLocation = hospitalLocation;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<ResType> getResTypes() {
		return resTypes;
	}

	public void setResTypes(List<ResType> resTypes) {
		this.resTypes = resTypes;
	}

	public List<AssignType> getAssignTypes() {
		return assignTypes;
	}

	public void setAssignTypes(List<AssignType> assignTypes) {
		this.assignTypes = assignTypes;
	}
	
	public List<PetitionType> getPetitionTypes() {
		return petitionTypes;
	}

	public void setPetitionTypes(List<PetitionType> petitionTypes) {
		this.petitionTypes = petitionTypes;
	}

	public DayTemplate getDayTemplate() {
		return dayTemplate;
	}

	public void setDayTemplate(DayTemplate dayTemplate) {
		this.dayTemplate = dayTemplate;
	}

	public WeekTemplate getWeekTemplate() {
		return weekTemplate;
	}

	public void setWeekTemplate(WeekTemplate weekTemplate) {
		this.weekTemplate = weekTemplate;
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public String toJSON() {
		// Basic
		String str = "{";
		str += "\"id\":" + this.getId() + ",";
		str += "\"year\":" + this.getYear() + ",";
		str += "\"name\":\"" + this.getName() + "\",";
		str += "\"extendedName\":\"" + this.getExtendedName() + "\",";
		str += "\"description\":\"" + this.getDescription() + "\",";
		str += "\"dayGroups\":\"" + this.getDayGroups() + "\",";
		// Hospital
		str += "\"hospital\":{";
		str += "\"id\":" + this.getHospitalId() + ",";
		str += "\"name\":\"" + this.getHospitalName() + "\",";
		str += "\"location\":\"" + this.getHospitalLocation() + "\"";
		str += "},";
		// Admin
		str += "\"admin\":{\"id\":" + this.getAdmin() + "},";
		// Member
		str += "\"members\":[";
		for (int i = 0; i < this.getMembers().size(); i++) {
			str += this.getMembers().get(i).toJSON();
			if (i < (this.getMembers().size() - 1))
				str += ",";
		}
		str += "],";
		// Rules
		str += "\"rules\":[";
		for (int i = 0; i < this.getRules().size(); i++) {
			str += this.getRules().get(i).toJSON();
			if (i < (this.getRules().size() - 1))
				str += ",";
		}
		str += "],";
		// Jobs
		str += "\"jobs\":[";
		for (int i = 0; i < this.getJobs().size(); i++) {
			str += this.getJobs().get(i).toJSON();
			if (i < (this.getJobs().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "],";
		// ResTypes
		str += "\"resTypes\":[";
		for (int i = 0; i < this.getResTypes().size(); i++) {
			str += this.getResTypes().get(i).toJSON();
			if (i < (this.getResTypes().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "],";
		// AssignTypes
		str += "\"assignTypes\":[";
		for (int i = 0; i < this.getAssignTypes().size(); i++) {
			str += this.getAssignTypes().get(i).toJSON();
			if (i < (this.getAssignTypes().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "],";
		// PetitionTypes
		str += "\"petitionTypes\":[";
		for (int i = 0; i < this.getPetitionTypes().size(); i++) {
			str += this.getPetitionTypes().get(i).toJSON();
			if (i < (this.getPetitionTypes().size() - 1)) // TODO: check??
				str += ",";
		}
		str += "],";
		// DayTemplate
		str += "\"dayTemplate\":" + this.getDayTemplate().toJSON() + ",";
		str += "\"weekTemplate\":" + this.getWeekTemplate().toJSON();
		// WeekTemplate
		str += "}";
		return str;
	}
	// </editor-fold>

}
