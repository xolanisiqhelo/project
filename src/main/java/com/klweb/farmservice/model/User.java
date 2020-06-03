package com.klweb.farmservice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;

@Entity
@Table(name="users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String status;
	private String description;
	private String user_type_id;
	private String extrenal_system_id;
	private String created_by_user_id;
	private String modified_by_user_id;
//	private Date created_timestamp;
//	private Date modified_timestamp;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "user_farms",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "farm_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private Set<Farm> farms = new HashSet<Farm>();
	
	public User() {
		
	}
	
	public User(Set<Farm> farms) {
		this.farms = farms;
	}
	public User(String name, String status, String description) {
		this.name = name;
		this.status = status;
		this.description = description;
	}
	public User(String name, String status, String description, String user_type_id, 
			String extrenal_system_id, String created_by_user_id, String modified_by_user_id, 
			Date created_timestamp, Date modified_timestamp, Set<Farm> farms) {
		this.name = name;
		this.status = status;
		this.description = description;
		this.user_type_id = user_type_id;
		this.extrenal_system_id = extrenal_system_id;
		this.created_by_user_id = created_by_user_id;
		this.modified_by_user_id = modified_by_user_id;
//		this.created_timestamp = created_timestamp;
//		this.modified_timestamp = modified_timestamp;
		this.farms = farms;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

//	public Date getCreated_timestamp() {
//		return created_timestamp;
//	}
//	public void setCreated_timestamp(Date created_timestamp) {
//		this.created_timestamp = created_timestamp;
//	}
//
//	public Date getModified_timestamp() {
//		return modified_timestamp;
//	}
//	public void setModified_timestamp(Date modified_timestamp) {
//		this.modified_timestamp = modified_timestamp;
//	}

	public Set<Farm> getFarms() {
		if(farms == null) {
			farms = new HashSet<Farm>();
		}
		return farms;
	}

	public void setFarms(Set<Farm> farms) {
		this.farms = farms;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
