package com.myhabit.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myhabit.core.BaseEntity;


@Entity
@Table(name = "eat_habits")
@DiscriminatorValue("2")
public class EatingHabit extends Habit implements Serializable{
	
	@Column(name = "lunch_calo")
	private float lunchCalo;
	
	@Column(name = "breakfast_calo")
	private float breakfastCalo;
	
	@Column(name = "dinner_calo")
	private float dinnerCalo;
	
	@Column(name = "user_id")
	private String userId;
	
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;

	@Column(name = "advice_id")
	private String adviceId;


	public String getId() {
		return id;
	}

	public EatingHabit setId(String id) {
		this.id = id;
		return this;
	}
	

	public float getLunchCalo() {
		return lunchCalo;
	}

	public EatingHabit setLunchCalo(float lunchCalo) {
		this.lunchCalo = lunchCalo;
		return this;
	}

	public float getBreakfastCalo() {
		return breakfastCalo;
	}

	public EatingHabit setBreakfastCalo(float breakfastCalo) {
		this.breakfastCalo = breakfastCalo;
		return this;
	}

	public float getDinnerCalo() {
		return dinnerCalo;
	}

	public EatingHabit setDinnerCalo(float dinnerCalo) {
		this.dinnerCalo = dinnerCalo;
		return this;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public EatingHabit setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public EatingHabit active(boolean isActive) {
		this.active = isActive;
		return this;
	}
	
	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public EatingHabit setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public EatingHabit setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public EatingHabit setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
}
