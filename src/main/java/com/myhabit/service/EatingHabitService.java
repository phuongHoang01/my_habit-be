package com.myhabit.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

import com.myhabit.core.BaseService;
import com.myhabit.dto.eating_habit.EatingHabitByIUserIdDTO;
import com.myhabit.dto.eating_habit.EatingHabitDTO;
import com.myhabit.dto.eating_habit.InputEatingHabitCaloDTO;
import com.myhabit.entities.EatingHabit;
import com.myhabit.repository.EatingHabitRepository;

public interface EatingHabitService extends HabitService<EatingHabit> {
	public List<EatingHabitByIUserIdDTO> findEatingHabitByIUserId(String userId);
	public void inputCalo(InputEatingHabitCaloDTO inputEatingHabitCaloDTO);
}
