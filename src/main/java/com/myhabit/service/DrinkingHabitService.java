package com.myhabit.service;

import com.myhabit.dto.drinking_habit.InputDrinkingHabitDTO;
import com.myhabit.entities.DrinkingHabit;

public interface DrinkingHabitService extends HabitService<DrinkingHabit> {
    public void inputMiliWater(InputDrinkingHabitDTO inputDrinkingHabitDTO);
}
