package com.myhabit.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myhabit.common.helper.RandomString;
import com.myhabit.core.BaseServiceImpl;
import com.myhabit.dto.role.RoleAddDTO;
import com.myhabit.dto.role.RoleDTO;
import com.myhabit.dto.user.UserDTO;
import com.myhabit.entities.Role;
import com.myhabit.entities.User;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.repository.RoleRepositiory;
import com.myhabit.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	private RoleRepositiory roleRepositiory;
	
	public RoleServiceImpl(RoleRepositiory roleRepositiory) {
		this.roleRepositiory = roleRepositiory;
	}

	
	public void addRole(RoleAddDTO roleAddDTO) {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		roleAddDTO.setName("ROLE_"+ roleAddDTO.getName());
		Role role = convertToEntity(roleAddDTO);
		
		role.setCreateBy(userPrincipal.getId())
			.setUpdateBy(userPrincipal.getId())
			.setId(RandomString.randomIdString());
		
		roleRepositiory.save(role);
	}
	
	public Optional<Role> findByName(String name) {
		return this.roleRepositiory.findByName(name);
	}
	
	public int countByName(String name) {
		return roleRepositiory.countByName(name);
	}
	
	public <CDTO extends RoleDTO> CDTO convertToDTO(Role role, Class<CDTO> T) {
		ModelMapper modelMapper = new ModelMapper();
		CDTO dto = modelMapper.map(role, T);
		return dto;
	}

	public Role convertToEntity(RoleDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Role entity = modelMapper.map(dto, Role.class);
		return entity;
	}

	
	

}
