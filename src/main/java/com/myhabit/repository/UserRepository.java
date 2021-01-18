package com.myhabit.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import com.myhabit.core.BaseRepository;
import com.myhabit.entities.User;

@Repository
public interface UserRepository extends BaseRepository<User> {
	
	public Optional<User> findUserByEmail(String email);
	
	public Optional<User> findUserByName(String userName);
	
	public int countUserByEmailOrName(String email, String name);
	
	public int countUserByEmail(String email);
	
}
