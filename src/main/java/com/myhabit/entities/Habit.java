package com.myhabit.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.myhabit.core.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="habit_type", 
discriminatorType = DiscriminatorType.INTEGER)
public class Habit extends BaseEntity {

	private String description;
	protected Float total;
	protected float point;

	public String getDescription() {
		return description;
	}

	public Habit setDescription(String description) {
		this.description = description;
		return this;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public Habit setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public Habit active(boolean isActive) {
		this.active = isActive;
		return this;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public Habit setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}
	
	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public Habit setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
		return this;
	}
	
	public LocalDate getCreateAt() {
		return this.createAt;
	}

	public Habit setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
		return this;
	}

}
