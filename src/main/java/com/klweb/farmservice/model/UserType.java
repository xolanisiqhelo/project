package com.klweb.farmservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "user_types")
public class UserType extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String status;
    private String description;
    private String created_by_user_id;
    private String modified_by_user_id;

 

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

    public String toString() {
    	return new Gson().toJson(this);
    }

}
