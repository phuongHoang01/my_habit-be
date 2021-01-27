package com.myhabit.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myhabit.core.BaseRepository;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.Habit;
@Repository
public interface HabitRepository<E extends Habit> extends BaseRepository<E>{
	
	@Query("SELECT h "
			+ "FROM User u, Habit h "
			+ "WHERE u.id = :userId AND u.id = h.id")
	public List<E> findHabitByUserId(@Param(value = "userId") String userId);
	
	public Optional<E> findHabitByCreateAt(LocalDate now);
	
	@Query("SELECT h "
			+ "FROM User u, #{#entityName} h "
			+ "WHERE h.createAt BETWEEN :fromDate AND :toDate AND u.id = :userId AND u.id = h.userId")
	public List<E> findHabitByCreateAtBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("userId") String userId);
}

