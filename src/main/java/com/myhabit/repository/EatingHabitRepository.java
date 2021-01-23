package com.myhabit.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myhabit.core.BaseRepository;
import com.myhabit.entities.EatingHabit;
@Repository
@Qualifier(value = "eatingHabitRepository")
public interface EatingHabitRepository extends HabitRepository<EatingHabit> {

	
	public List<EatingHabit> findEatingHabitByCreateAtBetween(LocalDate fromDate, LocalDate toDate);

}
