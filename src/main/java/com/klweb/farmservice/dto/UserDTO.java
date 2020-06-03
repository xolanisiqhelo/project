package com.klweb.farmservice.dto;

import java.util.HashSet;
import java.util.Set;

import com.klweb.farmservice.model.Farm;

public class UserDTO {
	private String name;
	private String status;
	private String description;
	private String user_type_id;
	private String extrenal_system_id;
	private String created_by_user_id;
	private String modified_by_user_id;
	private Set<Farm> farms = new HashSet<Farm>();
	
	public UserDTO() {}
	
	public UserDTO(Set<Farm> farms) {
		this.farms = farms;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(String user_type_id) {
		this.user_type_id = user_type_id;
	}
	public String getExtrenal_system_id() {
		return extrenal_system_id;
	}
	public void setExtrenal_system_id(String extrenal_system_id) {
		this.extrenal_system_id = extrenal_system_id;
	}
	public String getCreated_by_user_id() {
		return created_by_user_id;
	}
	public void setCreated_by_user_id(String created_by_user_id) {
		this.created_by_user_id = created_by_user_id;
	}
	public String getModified_by_user_id() {
		return modified_by_user_id;
	}
	public void setModified_by_user_id(String modified_by_user_id) {
		this.modified_by_user_id = modified_by_user_id;
	}

	public Set<Farm> getFarms() {
		if(farms == null) {
			farms = new HashSet<Farm>();
		}
		return farms;
	}

	public void setFarms(Set<Farm> farms) {
		this.farms = farms;
	}
	
	
}
