package tn.esprit.spring.entities;

import java.util.List;

public class MissionDTO{
	private int id;
	
	private String name;
	
	private String description;
	
	private int departementId;
	
	private  List<Timesheet> timesheets;

	public MissionDTO(int id, String name, String description, int departementId,List<Timesheet> timesheets) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.departementId = departementId;
		this.timesheets = timesheets;
	}
	
	public MissionDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDepartementId() {
		return departementId;
	}

	public void setDepartementId(int departementId) {
		this.departementId = departementId;
	}

	public List<Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
	
	
	
	
}