package com.klweb.farmservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
		value = {"created_timestamp", "modified_timestamp"},
		allowGetters = true
		)
public abstract class AuditModel implements Serializable {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_timestamp", nullable = false, updatable = false)
	@CreatedDate
	private Date created_timestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_timestamp", nullable = false)
	@LastModifiedDate	
	private Date modified_timestamp;

	public Date getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(Date created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public Date getModified_timestamp() {
		return modified_timestamp;
	}

	public void setModified_timestamp(Date modified_timestamp) {
		this.modified_timestamp = modified_timestamp;
	}
	
	
}
