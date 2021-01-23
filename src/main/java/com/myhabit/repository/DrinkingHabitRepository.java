package com.myhabit.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.myhabit.entities.DrinkingHabit;
@Repository
@Qualifier(value = "drinkingHabitRepository")
public interface DrinkingHabitRepository extends HabitRepository<DrinkingHabit>  {

}
