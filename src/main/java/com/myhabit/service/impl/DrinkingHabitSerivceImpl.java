package com.myhabit.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myhabit.common.helper.RandomString;
import com.myhabit.common.helper.UserHelper;
import com.myhabit.dto.eating_habit.InputEatingHabitCaloDTO;
import com.myhabit.entities.DrinkingHabit;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.service.DrinkHabitService;
import com.myhabit.service.HabitService;
@Service
public class DrinkingHabitSerivceImpl extends HabitServiceImpl<DrinkingHabit> implements DrinkHabitService{

	/*
	 * public void inputMiliWater(InputEatingHabitCaloDTO inputEatingHabitCaloDTO) {
	 * Optional<EatingHabit> eatingHabit =
	 * super.findHabitByCurrentDay(LocalDate.now());
	 * 
	 * UserPrincipal currentLoginUser = UserHelper.getCurrentUserLoginInSystem();
	 * 
	 * String id = RandomString.randomIdString();
	 * 
	 * if(!eatingHabit.isPresent()) { eatingHabit =
	 * Optional.of(convertToEntity(inputEatingHabitCaloDTO)); eatingHabit.get()
	 * .setId(id); } else { eatingHabit .get()
	 * .setBreakfastCalo(inputEatingHabitCaloDTO.getBreakfastCalo())
	 * .setLunchCalo(inputEatingHabitCaloDTO.getLunchCalo())
	 * .setDinnerCalo(inputEatingHabitCaloDTO.getDinnerCalo()); }
	 * 
	 * eatingHabit.get() .setCreateBy(currentLoginUser.getId())
	 * .setUpdateBy(currentLoginUser.getId()) .setUserId(currentLoginUser.getId())
	 * .setTotal(updateTotalCalo( eatingHabit.get().getBreakfastCalo(),
	 * eatingHabit.get().getLunchCalo(), eatingHabit.get().getDinnerCalo()) );
	 * 
	 * this.eatingHabitRepository.save(eatingHabit.get()); }
	 */
}
