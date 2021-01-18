package com.myhabit.dto.user;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class UserProfileDTO extends UserDTO {

	@NotEmpty(message = "Tên không để trống")
	private String lastName;
	
	@NotEmpty(message = "Họ không để trống")
	private String firstName;
	
	@Email(message = "Không đúng định dang email")
	private String email;
	
	@NotEmpty(message = "Giới tính không để trống")
	private boolean sex;
	
	private int weight;
	
	private float height;
	
	private String dateOfBirth;
	
	public String getLastName() {
		return lastName;
	}
	public UserProfileDTO setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public UserProfileDTO setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public boolean isSex() {
		return sex;
	}
	public UserProfileDTO setSex(boolean sex) {
		this.sex = sex;
		return this;
	}
	public int getWeight() {
		return weight;
	}
	public UserProfileDTO setWeight(int weight) {
		this.weight = weight;
		return this;
	}
	public float getHeight() {
		return height;
	}
	public UserProfileDTO setHeight(float height) {
		this.height = height;
		return this;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public UserProfileDTO setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
