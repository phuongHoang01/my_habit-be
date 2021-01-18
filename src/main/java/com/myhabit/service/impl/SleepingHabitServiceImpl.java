package com.myhabit.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myhabit.common.helper.DateTimeHelper;
import com.myhabit.common.helper.RandomString;
import com.myhabit.common.helper.UserHelper;
import com.myhabit.dto.sleeping_habit.InputSleepingHourDTO;
import com.myhabit.repository.SleepingHabitResponsitory;
import com.myhabit.entities.SleepingHabit;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.service.HabitService;
import com.myhabit.service.SleepingHabitService;
@Service
public class SleepingHabitServiceImpl extends HabitServiceImpl<SleepingHabit> 
										implements SleepingHabitService {
	
	SleepingHabitResponsitory sleepingHabitResponsitory;
	
	public SleepingHabitServiceImpl(SleepingHabitResponsitory sleepingHabitResponsitory) {
		this.sleepingHabitResponsitory = sleepingHabitResponsitory;
	}
	
	public void inputSleepingHabit(InputSleepingHourDTO inputSleepingHourDTO) {
		Optional<SleepingHabit> sleepingHabit = super.findHabitByCurrentDay(LocalDate.now());
		
		UserPrincipal currentLoginUser = UserHelper.getCurrentUserLoginInSystem();
		
		String id = RandomString.randomIdString();

		if(!sleepingHabit.isPresent()) {
			sleepingHabit = Optional.of(new SleepingHabit());
			sleepingHabit.get()
			.setCreateBy(currentLoginUser.getId())
			.setId(id)
			.setFromHour(DateTimeHelper.convertStringToLocalDateTime(inputSleepingHourDTO.fromHour))
			.setToHour(DateTimeHelper.convertStringToLocalDateTime(inputSleepingHourDTO.toHour));
		}	
		else {
			sleepingHabit
				.get()
				.setToHour(DateTimeHelper.convertStringToLocalDateTime(inputSleepingHourDTO.toHour))
				.setFromHour(DateTimeHelper.convertStringToLocalDateTime(inputSleepingHourDTO.fromHour));
		}
		
		sleepingHabit.get()
		.setUpdateBy(currentLoginUser.getId())
		.setUserId(currentLoginUser.getId());
		
		double total = totalSleepingTime(sleepingHabit.get().getFromHour(), sleepingHabit.get().getToHour());
		
		sleepingHabit.get().setTotal((float) total);
		
		this.sleepingHabitResponsitory.save(sleepingHabit.get());
	}
	
	public <CDTO> CDTO convertToDTO(SleepingHabit sleepingHabit, Class<CDTO> T) {
		ModelMapper modelMapper = new ModelMapper();
		CDTO dto = modelMapper.map(sleepingHabit, T);
		return dto;
	}

	public SleepingHabit convertToEntity(Object dto) {
		ModelMapper modelMapper = new ModelMapper();
		SleepingHabit entity = modelMapper.map(dto, SleepingHabit.class);
		return entity;
	}
	
	public double totalSleepingTime(LocalDateTime from, LocalDateTime to) {
		return Duration.between(from, to).toMinutes();
	}
	
}
