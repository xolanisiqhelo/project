package com.klweb.farmservice.dto;

import com.google.gson.Gson;
import com.klweb.farmservice.model.Coordinate;

public class UpdateFarmDto {
	private String name;
	private String size;
	private String description;
	private String status;
	private CoordinateDto area;
	
	public UpdateFarmDto() {}
	
	public UpdateFarmDto(String name, String size, String description, String status, CoordinateDto area) {
		this.name = name;
		this.size = size;
		this.description = description;
		this.status = status;
		this.area = area;
		
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
	public CoordinateDto getArea() {
		if(area == null) {
			area = new CoordinateDto();
		}
		return area;
	}
	public void setArea(CoordinateDto area) {
		this.area = area;
	}
	
	public String toString(){
		return new Gson().toJson(this);
	}
}
