package com.myhabit.entities;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "sleeping_habits")
public class SleepingHabit extends Habit {
	
	
	@Column(name = "from_hour")
	private LocalDateTime fromHour;
	
	@Column(name = "to_hour")
	private LocalDateTime toHour;
	
	@Column(name = "user_id")
	private String userId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	public LocalDateTime getFromHour() {
		return fromHour;
	}

	public SleepingHabit setFromHour(LocalDateTime fromHour) {
		this.fromHour = fromHour;
		return this;
	}

	public LocalDateTime getToHour() {
		return toHour;
	}

	public SleepingHabit setToHour(LocalDateTime toHour) {
		this.toHour = toHour;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreateBy() {
		return createBy;
	}

	public SleepingHabit setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public SleepingHabit active(boolean isActive) {
		this.active = isActive;
		return this;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public SleepingHabit setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}
	
	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public SleepingHabit setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
		return this;
	}
	
	public String getId() {
		return id;
	}

	public SleepingHabit setId(String id) {
		this.id = id;
		return this;
	}
}
