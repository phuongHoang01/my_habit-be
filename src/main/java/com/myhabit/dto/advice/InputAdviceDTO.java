package com.myhabit.dto.advice;

import com.myhabit.common.helper.HabitType;

public class InputAdviceDTO extends AdivceDTO{

	private String name;
	
	private String description;
	
	private HabitType type;
	
	private float point;

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

	public HabitType getType() {
		return type;
	}

	public void setType(HabitType type) {
		this.type = type;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}
}
