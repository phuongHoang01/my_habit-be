package com.myhabit.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.crypto.Data;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.myhabit.common.helper.DateTimeHelper;
import com.myhabit.common.helper.UserHelper;
import com.myhabit.core.BaseRepository;
import com.myhabit.core.BaseServiceImpl;
import com.myhabit.dto.eating_habit.EatingHabitDTO;
import com.myhabit.dto.habit.HabitDTO;
import com.myhabit.dto.habit.StaisticalHabitDTO;
import com.myhabit.dto.user.UserDTO;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.Habit;
import com.myhabit.entities.User;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.repository.HabitRepository;
import com.myhabit.service.HabitService;

public class HabitServiceImpl<E extends Habit> extends BaseServiceImpl<E> implements HabitService<E> {

	private HabitRepository<E> habitRepository;
	
	public HabitServiceImpl(
		BaseRepository<E> repository,
		HabitRepository<E> habitRepository) {
		super(repository);
		this.habitRepository = habitRepository;
	}
	
	public List<E> findHabitByUserId(String userId) {
		return this.habitRepository.findHabitByUserId(userId);
	}

	public Optional<E> findHabitByCurrentDay(LocalDate now) {
		Optional<E> result = this.habitRepository.findHabitByCreateAt(now);
		return result;
	}

	public List<StaisticalHabitDTO> getTotalInWeek(String week) {

		LocalDate monday = DateTimeHelper.getMondayDateInThisWeek(week);
		LocalDate sunday = DateTimeHelper.getSundayDateInThisWeek(week);
		List<StaisticalHabitDTO> staisticalHabitDTOs = this.habitRepository.findHabitByCreateAtBetween(
				monday, 
				sunday,
				UserHelper.getCurrentUserLoginInSystem().getId()
				)
				.stream()
				.map(habit -> {
					StaisticalHabitDTO item = new StaisticalHabitDTO();
					item.total = habit.getTotal();
					item.createAt = habit.getCreateAt();
					return item;
				})
				.collect(Collectors.toList());
		
		return processingStaisticalForChart(staisticalHabitDTOs, monday, sunday);
	}

	public List<StaisticalHabitDTO> getTotalInMonth(String month) {
		LocalDate firstDateOfMonth = DateTimeHelper.getFirstDayOfMonth(month);
		LocalDate lastDateOfMonth = DateTimeHelper.getLastDateOfMonth(month);
		List<StaisticalHabitDTO> staisticalHabitDTOs = this.habitRepository.findHabitByCreateAtBetween(
				firstDateOfMonth, 
				lastDateOfMonth,
				UserHelper.getCurrentUserLoginInSystem().getId()
				)
				.stream()
				.map(habit -> {
					StaisticalHabitDTO item = new StaisticalHabitDTO();
					item.total = habit.getTotal();
					item.createAt = habit.getCreateAt();
					return item;
				})
				.collect(Collectors.toList());
		
		return processingStaisticalForChart(staisticalHabitDTOs, firstDateOfMonth, lastDateOfMonth);
	}

	public List<StaisticalHabitDTO> getTotalInWeekByUserId(String week, String userId) {
		LocalDate monday = DateTimeHelper.getMondayDateInThisWeek(week);
		LocalDate sunday = DateTimeHelper.getSundayDateInThisWeek(week);
		List<StaisticalHabitDTO> staisticalHabitDTOs = this.habitRepository.findHabitByCreateAtBetween(
				monday, 
				sunday,
				userId
				)
				.stream()
				.map(habit -> {
					StaisticalHabitDTO item = new StaisticalHabitDTO();
					item.total = habit.getTotal();
					item.createAt = habit.getCreateAt();
					return item;
				})
				.collect(Collectors.toList());
		
		return processingStaisticalForChart(staisticalHabitDTOs, monday, sunday);
	}

	public List<StaisticalHabitDTO> getTotalInMonthByUserId(String month, String userId) {
		LocalDate firstDateOfMonth = DateTimeHelper.getFirstDayOfMonth(month);
		LocalDate lastDateOfMonth = DateTimeHelper.getLastDateOfMonth(month);
		List<StaisticalHabitDTO> staisticalHabitDTOs = this.habitRepository.findHabitByCreateAtBetween(
				firstDateOfMonth, 
				lastDateOfMonth,
				userId
				)
				.stream()
				.map(habit -> {
					StaisticalHabitDTO item = new StaisticalHabitDTO();
					item.total = habit.getTotal();
					item.createAt = habit.getCreateAt();
					return item;
				})
				.collect(Collectors.toList());
		
		return processingStaisticalForChart(staisticalHabitDTOs, firstDateOfMonth, lastDateOfMonth);
	}

	public <CDTO> CDTO convertToDTO(Habit habit, Class<CDTO> T) {
		ModelMapper modelMapper = new ModelMapper();
		CDTO dto = modelMapper.map(habit, T);
		return dto;
	}

	public Habit convertToEntity(Object dto) {
		ModelMapper modelMapper = new ModelMapper();
		Habit entity = modelMapper.map(dto, Habit.class);
		return entity;
	}

	public List<StaisticalHabitDTO> processingStaisticalForChart(List<StaisticalHabitDTO> staisticalHabitDTOs,
			LocalDate startDate, LocalDate endDate) {
		List<StaisticalHabitDTO> afterProcessing = new LinkedList<StaisticalHabitDTO>();

		
		// Staistical default value 
		for(LocalDate start = startDate; start.isBefore(endDate) || start.isEqual(endDate) ;start = start.plusDays(1)) {
			StaisticalHabitDTO item = new StaisticalHabitDTO();
			item.total = 0f;
			item.createAt = start;
			afterProcessing.add(item);
		}
		
		if(staisticalHabitDTOs.size() != 0) {
			afterProcessing.stream().forEach(item -> {
				staisticalHabitDTOs.forEach(data -> {
					if(item.createAt.isEqual(data.createAt)) {
						item.total = data.total;
					}
				});
			});	
		}
		
		
		return afterProcessing;
	}
}
