package com.myhabit.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myhabit.core.BaseEntity;
import com.myhabit.core.BaseService;
import com.myhabit.dto.user.ForgetPasswordUserDTO;
import com.myhabit.dto.user.ListUserDTO;
import com.myhabit.dto.user.RegisterUserDTO;
import com.myhabit.dto.user.UserDTO;
import com.myhabit.dto.user.UserProfileDTO;
import com.myhabit.entities.User;
import com.myhabit.repository.UserRepository;

public interface UserService extends BaseService<User> {
	public void register(RegisterUserDTO registerUserDto);
	public Optional<User> findUserByName(String userName);
	public int countUserByEmailOrName(String email, String name);
	public void forgetPassword(ForgetPasswordUserDTO forgetPasswordDTO);
	public int countUserByEmail(String email);
	public void updateUserProfile(UserProfileDTO userProfileDTO);
}
