package com.myhabit.dto.user;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class RegisterUserDTO extends UserDTO {
	
	@NotEmpty(message = "Email không được bỏ trống")
	private String email;
	
	@Length(min = 3, max = 16, message = "Username ít nhất 3 kí tự và nhiều nhất 16 kí tự")
	private String name;
	
	@Length(min = 3, max = 16, message = "Mật khâu ít nhất 3 kí tự và nhiều nhất 16 kí tự")
	private String password;
	
	public String getName() {
		return name;
	}
	public RegisterUserDTO setName(String name) {
		this.name = name;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public RegisterUserDTO setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
