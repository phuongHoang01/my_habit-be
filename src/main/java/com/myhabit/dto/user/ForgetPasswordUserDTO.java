package com.myhabit.dto.user;

public class ForgetPasswordUserDTO extends UserDTO {
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public ForgetPasswordUserDTO setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public ForgetPasswordUserDTO setPassword(String password) {
		this.password = password;
		return this;
	}
}
