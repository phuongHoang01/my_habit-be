package com.myhabit.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E ,String> {
	
	public List<E> findAllByActive(boolean active);
	public Optional<E> findByIdAndActive(String id, boolean active);
}
