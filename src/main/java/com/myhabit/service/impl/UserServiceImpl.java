package com.myhabit.service.impl;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.myhabit.common.helper.DateTimeHelper;
import com.myhabit.common.helper.RandomString;
import com.myhabit.core.BaseRepository;
import com.myhabit.core.BaseServiceImpl;
import com.myhabit.dto.user.ForgetPasswordUserDTO;
import com.myhabit.dto.user.LoginUserDTO;
import com.myhabit.dto.user.RegisterUserDTO;
import com.myhabit.dto.user.UserDTO;
import com.myhabit.dto.user.UserProfileDTO;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.Role;
import com.myhabit.entities.SleepingHabit;
import com.myhabit.entities.User;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.repository.HabitRepository;
import com.myhabit.repository.RoleRepositiory;
import com.myhabit.repository.UserRepository;
import com.myhabit.service.UserService;



@Service
public class UserServiceImpl extends BaseServiceImpl<User>
												implements UserService{
	
	UserRepository userRepository;
	RoleRepositiory roleRepositiory;
	
	public UserServiceImpl(
			BaseRepository<User> repository,
			UserRepository userRepository,
			RoleRepositiory roleRepositiory
							) {
		super(repository);
		this.userRepository = userRepository;
		this.roleRepositiory = roleRepositiory;
	}

	@Override
	public void register(RegisterUserDTO registerUserDto) {
		// TODO Auto-generated method stub
		String uuid = RandomString.randomIdString();
		User user = convertToEntity(registerUserDto);
		Optional<Role> role_user = this.roleRepositiory.findByName("ROLE_USER");
		String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		
		user.active(Boolean.TRUE)
			.setPassword(hashPassword)
			.setId(uuid)
			.setCreateBy(uuid)
			.setUpdateBy(uuid)
			.setRoleId(role_user.get().getId());

		this.userRepository.save(user);
	}
	
	public void login(LoginUserDTO loginUserDTO) {
		final Optional<User> found = this.userRepository.findUserByName(loginUserDTO.getUserName());
	}
	
	public void updateUserProfile(UserProfileDTO userProfileDTO) {
		UserPrincipal currentLoginUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = this.userRepository.findById(currentLoginUser.getId());
		
		user.get()
			.setFirstName(userProfileDTO.getFirstName())
			.setLastName(userProfileDTO.getLastName())
			.setWeight(userProfileDTO.getWeight())
			.setSex(userProfileDTO.isSex()) // LOL
			.setWeight(userProfileDTO.getWeight())
			.setDateOfBirth(DateTimeHelper.convertStringToLocalDate(userProfileDTO.getDateOfBirth()))
			.setEmail(userProfileDTO.getEmail());
		
		this.userRepository.save(user.get());	
	}

	@Override
	public Optional<User> findUserByName(String userName) {
		return this.userRepository.findUserByName(userName);
	}

	@Override
	public int countUserByEmailOrName(String email, String name) {
		return this.userRepository.countUserByEmailOrName(email, name);
	}
	
	@Override
	public int countUserByEmail(String email) {
		return this.userRepository.countUserByEmail(email);
	}
	

	@Override
	public void forgetPassword(ForgetPasswordUserDTO forgetPasswordUserDTO) {
		Optional<User> found = userRepository.findUserByEmail(forgetPasswordUserDTO.getEmail());
		User user = found.get();
		
		String hashPassword = BCrypt.hashpw(forgetPasswordUserDTO.getPassword(), BCrypt.gensalt(12));
		
		user
			.setPassword(hashPassword);
		userRepository.save(user);

	}
	
	public <CDTO extends UserDTO> CDTO convertToDTO(User user, Class<CDTO> T) {
		ModelMapper modelMapper = new ModelMapper();
		CDTO dto = modelMapper.map(user, T);
		return dto;
	}

	public User convertToEntity(UserDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		User entity = modelMapper.map(dto, User.class);
		return entity;
	}
}
