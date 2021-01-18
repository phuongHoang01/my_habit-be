package com.myhabit.repository;

import java.util.Optional;

import com.myhabit.core.BaseRepository;
import com.myhabit.dto.role.RoleAddDTO;
import com.myhabit.entities.Role;

public interface RoleRepositiory extends BaseRepository<Role>  {

	
	public int countByName(String name);
	public Optional<Role> findByName(String name);
	
}
