package com.myhabit.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myhabit.common.helper.RandomString;
import com.myhabit.common.helper.UserHelper;
import com.myhabit.core.BaseServiceImpl;
import com.myhabit.dto.eating_habit.EatingHabitByIUserIdDTO;
import com.myhabit.dto.eating_habit.EatingHabitDTO;
import com.myhabit.dto.eating_habit.InputEatingHabitCaloDTO;
import com.myhabit.dto.habit.HabitDTO;
import com.myhabit.dto.user.UserDTO;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.Habit;
import com.myhabit.entities.User;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.repository.EatingHabitRepository;
import com.myhabit.repository.HabitRepository;
import com.myhabit.repository.UserRepository;
import com.myhabit.service.EatingHabitService;

import net.bytebuddy.asm.Advice.This;

@Service
public class EatingServiceImpl extends HabitServiceImpl<EatingHabit> implements EatingHabitService{
	

	private UserRepository userRepository;
	

	private EatingHabitRepository eatingHabitRepository;
	
	public EatingServiceImpl(
		@Qualifier("eatingHabitRepository") EatingHabitRepository eatingHabitRepository, 
			UserRepository userRepository) {
		super(eatingHabitRepository);
		this.userRepository = userRepository;
		this.eatingHabitRepository = eatingHabitRepository;
	}
	
	public List<EatingHabitByIUserIdDTO> findEatingHabitByIUserId(String userId) {
		return super.findHabitByUserId(userId).stream()
				.map(habit -> convertToDTO(habit, EatingHabitByIUserIdDTO.class))
				.collect(Collectors.toList());
	}
	
	public void inputCalo(InputEatingHabitCaloDTO inputEatingHabitCaloDTO) {
		Optional<EatingHabit> eatingHabit = findHabitByCurrentDay(LocalDate.now());
		
		UserPrincipal currentLoginUser = UserHelper.getCurrentUserLoginInSystem();
		
		String id = RandomString.randomIdString();

		if(!eatingHabit.isPresent()) {
			eatingHabit = Optional.of(convertToEntity(inputEatingHabitCaloDTO));
			eatingHabit.get()
			.setId(id)
			.setCreateBy(currentLoginUser.getId());
		}	
		else {
			eatingHabit
				.get()
				.setBreakfastCalo(inputEatingHabitCaloDTO.getBreakfastCalo())
				.setLunchCalo(inputEatingHabitCaloDTO.getLunchCalo())
				.setDinnerCalo(inputEatingHabitCaloDTO.getDinnerCalo());
		}
		
		eatingHabit.get()
		.setUpdateBy(currentLoginUser.getId())
		.setUserId(currentLoginUser.getId())
		.setTotal(updateTotalCalo(
				eatingHabit.get().getBreakfastCalo(), 
				eatingHabit.get().getLunchCalo(), 
				eatingHabit.get().getDinnerCalo())
				);
		
		this.eatingHabitRepository.save(eatingHabit.get());
	}
	
	public void getEatingHabitList() {
		
	}

	public <CDTO> CDTO convertToDTO(EatingHabit eatingHabit, Class<CDTO> T) {
		ModelMapper modelMapper = new ModelMapper();
		CDTO dto = modelMapper.map(eatingHabit, T);
		return dto;
	}

	public EatingHabit convertToEntity(Object dto) {
		ModelMapper modelMapper = new ModelMapper();
		EatingHabit entity = modelMapper.map(dto, EatingHabit.class);
		return entity;
	}
	
	public float updateTotalCalo(float breakfast, float lunch, float dinner) {
		return breakfast + lunch + dinner;
	}
	
}