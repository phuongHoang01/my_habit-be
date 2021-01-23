package com.myhabit.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.myhabit.entities.SleepingHabit;
@Repository
@Qualifier(value = "sleepingHabitResponsitory")
public interface SleepingHabitResponsitory extends HabitRepository<SleepingHabit> {

}
