package com.klweb.farmservice.dto;

public class CreateFarmDto {
	private String name;
	private String size;
	private String description;
	private String status;
	private CoordinateDto area;
	
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
		return area;
	}
	public void setArea(CoordinateDto area) {
		this.area = area;
	}
	
	
	
}
