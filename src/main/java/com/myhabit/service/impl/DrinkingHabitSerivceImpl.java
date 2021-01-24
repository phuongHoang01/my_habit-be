package com.myhabit.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.myhabit.common.helper.RandomString;
import com.myhabit.common.helper.UserHelper;
import com.myhabit.core.BaseRepository;
import com.myhabit.dto.drinking_habit.InputDrinkingHabitDTO;
import com.myhabit.entities.DrinkingHabit;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.repository.DrinkingHabitRepository;
import com.myhabit.repository.HabitRepository;
import com.myhabit.service.DrinkingHabitService;
import com.myhabit.service.HabitService;

@Service
public class DrinkingHabitSerivceImpl extends HabitServiceImpl<DrinkingHabit> implements DrinkingHabitService {
	

	private DrinkingHabitRepository drinkingHabitRepository;
	
	
	public DrinkingHabitSerivceImpl(
			BaseRepository<DrinkingHabit> repository,
			HabitRepository<DrinkingHabit> habitRepository,
			DrinkingHabitRepository drinkingHabitRepository) {
		super(repository, habitRepository);
		this.drinkingHabitRepository = drinkingHabitRepository;
		// TODO Auto-generated constructor stub
	}

	

	public void inputMiliWater(InputDrinkingHabitDTO inputDrinkingHabitDTO) {
		Optional<DrinkingHabit> drinkingHabit = findHabitByCurrentDay(LocalDate.now());

		UserPrincipal currentLoginUser = UserHelper.getCurrentUserLoginInSystem();

		String id = RandomString.randomIdString();

		if (!drinkingHabit.isPresent()) {
			drinkingHabit = Optional.of(convertToEntity(inputDrinkingHabitDTO));
			drinkingHabit.get()
			.setCreateBy(currentLoginUser.getId());
			drinkingHabit.get().setId(id);
		} 

		drinkingHabit.get()
			.setUpdateBy(currentLoginUser.getId())
			.setUserId(currentLoginUser.getId())
			.setTotal(inputDrinkingHabitDTO.total);

		this.drinkingHabitRepository.save(drinkingHabit.get());
	}
	
	public <CDTO> CDTO convertToDTO(DrinkingHabit drinkingHabit, Class<CDTO> T) {
		ModelMapper modelMapper = new ModelMapper();
		CDTO dto = modelMapper.map(drinkingHabit, T);
		return dto;
	}

	public DrinkingHabit convertToEntity(Object dto) {
		ModelMapper modelMapper = new ModelMapper();
		DrinkingHabit entity = modelMapper.map(dto, DrinkingHabit.class);
		return entity;
	}
	

}
