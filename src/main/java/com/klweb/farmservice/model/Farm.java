package com.klweb.farmservice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;

@Entity
@Table(name="farms")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Farm extends AuditModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String size;
	private String description;
	private String status;
	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@JoinColumn(name="area_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Coordinate area;
	
	@ManyToMany(mappedBy = "farms", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private Set<User> users =  new HashSet<User>();
	
	public Farm() {}
		
	public Farm(String name, String size, String description, String status, Coordinate area) {
		this.name = name;
		this.size = size;
		this.description = description;
		this.status = status;
		this.area = area;		
	}
	
	public Farm(String name, String size, String description, String status, Coordinate area, Set<User> users) {
		this.name = name;
		this.size = size;
		this.description = description;
		this.status = status;
		this.area = area;
		this.users =  users;		
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


	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	public Coordinate getArea() {
		if(area == null) {
			area = new Coordinate();
		}
		return area;
	}
	public void setArea(Coordinate area) {
		this.area = area;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
