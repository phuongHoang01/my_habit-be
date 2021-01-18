package com.myhabit.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.myhabit.core.BaseEntity;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
	
	@Column(unique =  true, nullable = false)
	private String name;
	
	@Column(length = 100)
	@Length(min = 0, max = 100, message = "Mô tả từ chỉ từ 0 đến 100 kí tự")
	private String description;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private List<User> users;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public Role setId(String id) {
		this.id = id;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Role setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public Role active(boolean isActive) {
		this.active = isActive;
		return this;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public Role setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}
	
	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public Role setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
		return this;
	}

}
