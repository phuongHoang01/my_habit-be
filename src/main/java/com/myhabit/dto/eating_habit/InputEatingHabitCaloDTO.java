package com.myhabit.dto.eating_habit;

import com.myhabit.dto.habit.HabitDTO;

public class InputEatingHabitCaloDTO {
	
	private float lunchCalo;

	private float breakfastCalo;

	private float dinnerCalo;
	
	public float getLunchCalo() {
		return lunchCalo;
	}

	public InputEatingHabitCaloDTO setLunchCalo(float lunchCalo) {
		this.lunchCalo = lunchCalo;
		return this;
	}

	public float getBreakfastCalo() {
		return breakfastCalo;
	}

	
	public InputEatingHabitCaloDTO setBreakfastCalo(float breakfastCalo) {
		this.breakfastCalo = breakfastCalo;
		return this;
	}

	public float getDinnerCalo() {
		return dinnerCalo;
	}

	public InputEatingHabitCaloDTO setDinnerCalo(float dinnerCalo) {
		this.dinnerCalo = dinnerCalo;
		return this;
	}

}
