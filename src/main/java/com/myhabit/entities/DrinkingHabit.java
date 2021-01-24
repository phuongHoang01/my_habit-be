package com.myhabit.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drinking_habit")
@DiscriminatorValue("1")
public class DrinkingHabit extends Habit implements Serializable {
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User user;
	
	
	public boolean isActive() {
		return this.active;
	}
	
	public DrinkingHabit active(boolean isActive) {
		this.active = isActive;
		return this;
	}
	
	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public DrinkingHabit setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public DrinkingHabit setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public DrinkingHabit setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	public String getId() {
		return id;
	}

	public DrinkingHabit setId(String id) {
		this.id = id;
		return this;
	}
	
	
}
