package com.myhabit.dto.user;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoginUserDTO extends UserDTO {
	
	private String userName;
	
	@Column(name = "password", nullable = false)
	@NotEmpty(message = "Mật khẩu không để trống")
	@Length(min = 5, max = 16, message = "Mật khẩu trong khoảng 5 đến 16 kí tư")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public LoginUserDTO setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public LoginUserDTO setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
}
