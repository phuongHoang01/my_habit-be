package com.myhabit.dto.eating_habit;

import java.sql.Timestamp;

import javax.persistence.Column;
import com.myhabit.dto.habit.HabitDTO;


public class EatingHabitDTO extends HabitDTO {
	
	@Column(name = "lunch_calo")
	private float lunchCalo;
	
	@Column(name = "breakfast_calo")
	private float breakfastCalo;
	
	@Column(name = "dinner_calo")
	private float dinnerCalo;
	
	@Column(name = "total_calo")
	private float totalCalo;
	
	@Column(name = "user_id")
	private String userId;
	
	public float getLunchCalo() {
		return lunchCalo;
	}

	public void setLunchCalo(float lunchCalo) {
		this.lunchCalo = lunchCalo;
	}

	public float getBreakfastCalo() {
		return breakfastCalo;
	}

	public void setBreakfastCalo(float breakfastCalo) {
		this.breakfastCalo = breakfastCalo;
	}

	public float getDinnerCalo() {
		return dinnerCalo;
	}

	public void setDinnerCalo(float dinnerCalo) {
		this.dinnerCalo = dinnerCalo;
	}

	public float getTotalCalo() {
		return totalCalo;
	}

	public void setTotalCalo(float totalCalo) {
		this.totalCalo = totalCalo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
