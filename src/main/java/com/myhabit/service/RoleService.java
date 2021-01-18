package com.myhabit.service;

import com.myhabit.core.BaseService;
import com.myhabit.dto.role.RoleAddDTO;
import com.myhabit.dto.role.RoleDTO;
import com.myhabit.entities.Role;
import com.myhabit.repository.RoleRepositiory;


public interface RoleService extends BaseService<Role>{
	
	public int countByName(String name);
	public void addRole(RoleAddDTO roleAddDTO);
}
