package com.myhabit.dto.eating_habit;

import javax.persistence.Column;

public class EatingHabitByIUserIdDTO {

	private float lunchCalo;

	private float breakfastCalo;

	private float dinnerCalo;

	private float totalCalo;

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
}
