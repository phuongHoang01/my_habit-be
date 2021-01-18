package com.myhabit.dto.role;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class RoleAddDTO extends RoleDTO{

	@NotEmpty(message = "Tên không để trống")
	private String name;
	
	@Length(min = 0,max = 100, message = "Ki tự trong khoảng từ 0 đến 100")
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
