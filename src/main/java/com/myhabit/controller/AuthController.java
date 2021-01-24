package com.myhabit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhabit.common.helper.JWTProvider;
import com.myhabit.dto.user.ForgetPasswordUserDTO;
import com.myhabit.dto.user.LoginUserDTO;
import com.myhabit.dto.user.RegisterUserDTO;
import com.myhabit.entities.User;
import com.myhabit.service.UserService;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
	
	final String WRONG_PASSWORD_OR_EMAIL = "Sai email hoac password";
	final String LOGIN_SUCCESS = "Đăng nhập thành công";
	final String EXIST_EMAIL_USERNAME = "username hay email đã tồn tại";
	final String UPDATE_SUCCESS = "Cập nhật mật khẩu thành công";
	final String NO_EMAIL = "Email không tồn tại";
	final String REGISTER_SUCCESFULLY = "Đăng kí thành công";
	
	UserService userService;
	
	
	AuthenticationManager authenticationManager;
	
	public AuthController(UserService userService,AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@Valid @RequestBody LoginUserDTO loginInfo) {
		Authentication authentication = null;
		try {
			Optional<User> found = this.userService.findUserByName(loginInfo.getUserName());
			if(!found.isPresent()) {
				return new ResponseEntity(WRONG_PASSWORD_OR_EMAIL,HttpStatus.BAD_REQUEST);
			}
			
			if(!BCrypt.checkpw(loginInfo.getPassword(), found.get().getPassword())) 
			{
				return new ResponseEntity(WRONG_PASSWORD_OR_EMAIL,HttpStatus.BAD_REQUEST);
			}
			
			authentication = this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							loginInfo.getUserName(), loginInfo.getPassword()));
			
			return new ResponseEntity(JWTProvider.generateToken(authentication) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register") 
	public ResponseEntity regiter(@Valid @RequestBody RegisterUserDTO registerUserDTO ) {
		try {
			int found = userService.countUserByEmailOrName(registerUserDTO.getEmail(), registerUserDTO.getName());
			if(found >= 1) {
				return new ResponseEntity(EXIST_EMAIL_USERNAME,HttpStatus.BAD_REQUEST);
			}
			this.userService.register(registerUserDTO);
			return new ResponseEntity(REGISTER_SUCCESFULLY,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/forget-password")
	public ResponseEntity forgotPassword(@Valid @RequestBody ForgetPasswordUserDTO forgetPasswordUserDTO ) {
		try {
			int found = userService.countUserByEmail(forgetPasswordUserDTO.getEmail());
			if(found == 1) {
				userService.forgetPassword(forgetPasswordUserDTO);
				return new ResponseEntity(UPDATE_SUCCESS,HttpStatus.ACCEPTED);
			}
			
			return new ResponseEntity(NO_EMAIL,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
