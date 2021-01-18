package com.myhabit.entities;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.myhabit.core.BaseEntity;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "last_name", length = 100)
	private String lastName;
	
	@Column(name = "first_name", length = 100)
	private String firstName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true)
	private String email;
	
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private boolean sex;
	
	private int weight;
	
	private float height;
	
	@Column(name = "date_of_birth")
	@DateTimeFormat(pattern = "d/MM/yyyy")
	private LocalDate dateOfBirth;
	
	@OneToMany(mappedBy = "user")
	private List<EatingHabit> eatingHabits;
	
	@OneToMany(mappedBy = "user")
	private List<SleepingHabit> sleepingHabits;
	
	@OneToMany(mappedBy = "user")
	private List<DrinkingHabit> drinkHabits;
	
	@Column(name = "role_id")
	private String roleId;
	
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public User setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public User setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public boolean isSex() {
		return sex;
	}

	public User setSex(boolean sex) {
		this.sex = sex;
		return this;
	}

	public int getWeight() {
		return weight;
	}

	public User setWeight(int weight) {
		this.weight = weight;
		return this;
	}

	public float getHigh() {
		return height;
	}

	public User setHigh(float high) {
		this.height = high;
		return this;
	}
	
	public String getId() {
		return id;
	}

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public LocalDate getCreateAt() {
		return this.createAt;
	}

	public User setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public User setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public User setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public User setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public User setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public User active(boolean isActive) {
		this.active = isActive;
		return this;
	}


}
