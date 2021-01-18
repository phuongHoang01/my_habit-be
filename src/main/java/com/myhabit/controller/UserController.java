package com.myhabit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myhabit.common.helper.ResponseMessage;
import com.myhabit.core.BaseController;
import com.myhabit.dto.user.RegisterUserDTO;
import com.myhabit.dto.user.UserDTO;
import com.myhabit.dto.user.UserProfileDTO;
import com.myhabit.entities.User;
import com.myhabit.service.UserService;
@RestController
@RequestMapping("/api/user")
public class UserController<D extends UserDTO > extends BaseController<D> {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/admin/get-all-user")
	public ResponseEntity<List<UserDTO>> findAll() {
		//return new ResponseEntity<List<UserDTO>>(userService.findAll(),HttpStatus.OK);
		return null;
	}
	
	@PutMapping("/update-user-profile")
	public ResponseEntity updateProfileUser(@RequestBody UserProfileDTO userProfileDTO ) {
		try {
			this.userService.updateUserProfile(userProfileDTO);
			return new ResponseEntity(ResponseMessage.UPDATE_SUCCESS,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
