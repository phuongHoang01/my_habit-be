package com.myhabit.core;

import java.io.Serializable;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	
	@Id
	@Column(length = 36)
	protected String id;

	@Column(name = "create_at", nullable = false)
	@CreationTimestamp
	protected LocalDate createAt;
	
	@Column(name = "update_at", nullable = false)
	@UpdateTimestamp
	protected LocalDate updateAt;
	
	@Column(name = "update_by", nullable = false)
	protected String updateBy;
	
	@Column(name = "create_by", nullable = false)
	@CreatedBy
	protected String createBy;
	
	protected boolean active = Boolean.TRUE;

}
