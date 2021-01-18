package com.myhabit.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myhabit.core.BaseRepository;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.Habit;
@NoRepositoryBean
public interface HabitRepository<E extends Habit> extends BaseRepository<E>{
	
	@Query("SELECT h "
			+ "FROM User u, com.myhabit.entities.Habit h "
			+ "WHERE u.id = :userId AND u.id = h.id")
	public List<E> findHabitByUserId(@Param(value = "userId") String userId);
	
	
	@Query("SELECT h "
			+ "FROM com.myhabit.entities.Habit h "
			+ "WHERE createAt = :now")
	public Optional<E> findHabitByCurrentDay(LocalDate now);
	
	@Query("SELECT h "
			+ "FROM User u, com.myhabit.entities.Habit h "
			+ "WHERE h.createAt BETWEEN :fromDate AND :toDate AND u.id = :userId")
	public List<E> findHabitByCreateAtBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("userId") String userId);
}

