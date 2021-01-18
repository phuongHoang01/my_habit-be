package com.myhabit.service;

import org.springframework.stereotype.Repository;

import com.myhabit.dto.sleeping_habit.InputSleepingHourDTO;
import com.myhabit.entities.SleepingHabit;
@Repository
public interface SleepingHabitService extends HabitService<SleepingHabit> {
	public void inputSleepingHabit(InputSleepingHourDTO inputSleepingHourDTO);
}
