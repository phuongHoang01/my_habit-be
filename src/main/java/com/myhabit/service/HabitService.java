package com.myhabit.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myhabit.core.BaseService;
import com.myhabit.dto.habit.HabitDTO;
import com.myhabit.dto.habit.StaisticalHabitDTO;
import com.myhabit.entities.Habit;
import com.myhabit.repository.HabitRepository;

public interface HabitService<E extends Habit> extends BaseService<E>{
	public List<E> findHabitByUserId(String userId);
	public Optional<E> findHabitByCurrentDay(LocalDate now);
	public List<StaisticalHabitDTO> getTotalInWeek(String week);
	public List<StaisticalHabitDTO> getTotalInMonth(String month);
	public List<StaisticalHabitDTO> getTotalInMonthByUserId(String month, String userId);
	public List<StaisticalHabitDTO> getTotalInWeekByUserId(String week, String userId);
}