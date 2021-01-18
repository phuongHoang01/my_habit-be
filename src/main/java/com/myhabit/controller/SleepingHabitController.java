package com.myhabit.controller;

import java.time.DateTimeException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myhabit.common.helper.ResponseMessage;
import com.myhabit.dto.habit.StaisticalHabitDTO;
import com.myhabit.dto.sleeping_habit.InputSleepingHourDTO;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.SleepingHabit;
import com.myhabit.service.SleepingHabitService;
@RestController
public class SleepingHabitController {

	private SleepingHabitService sleepingHabitService;
	
	public SleepingHabitController(SleepingHabitService sleepingHabitService) {
		this.sleepingHabitService = sleepingHabitService;
	}
	
	
	@GetMapping("/api/sleeping-habit/statistical-sleeping-hour-in-week")
	public ResponseEntity<List<StaisticalHabitDTO>> getSleepingHabitInWeek(@RequestParam(required = true) String week) {
		try {
			final List<StaisticalHabitDTO> result = this.sleepingHabitService.getTotalInWeek(week);
			if(result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST,HttpStatus.OK);
			}	
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (DateTimeException e) {
			e.printStackTrace();
			return new ResponseEntity(ResponseMessage.DATE_FORMAT_NOT_CORRECT,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/api/sleeping-habit/statistical-sleeping-hour-in-month")
	public ResponseEntity<List<StaisticalHabitDTO>> getEatingHabitTotalCaloInMonth(@RequestParam(required = true) String month) {
		try {
			final List<StaisticalHabitDTO> result = this.sleepingHabitService.getTotalInMonth(month);
			if(result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST,HttpStatus.OK);
			}	
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (DateTimeException e) {
			e.printStackTrace();
			return new ResponseEntity(ResponseMessage.DATE_FORMAT_NOT_CORRECT,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/api/admin/sleeping-habit/statistical-sleeping-hour-in-month-by-user-id")
	public ResponseEntity<List<StaisticalHabitDTO>> getSleepingHabitOfUserFollowByMonth(@RequestParam(required = true) String month, @RequestParam(required = true) String userId) {
		try {
			final List<StaisticalHabitDTO> result = this.sleepingHabitService.getTotalInMonthByUserId(month, userId);
			if(result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST,HttpStatus.OK);
			}	
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (DateTimeException e) {
			return new ResponseEntity(ResponseMessage.DATE_FORMAT_NOT_CORRECT,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/api/admin/sleeping-habit/statistical-sleeping-hour-in-week-by-user-id")
	public ResponseEntity<List<StaisticalHabitDTO>> getSleepingHabitOfUserFollowByWeek(@RequestParam(required = true) String week, @RequestParam(required = true) String userId) {
		try {
			final List<StaisticalHabitDTO> result = this.sleepingHabitService.getTotalInWeekByUserId(week, userId);
			if(result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST,HttpStatus.OK);
			}	
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (DateTimeException e) {
			return new ResponseEntity(ResponseMessage.DATE_FORMAT_NOT_CORRECT,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add-sleeping-hour")
	public ResponseEntity addSleepingHour(@RequestBody InputSleepingHourDTO inputSleepingHourDTO) {
		try {
			this.sleepingHabitService.inputSleepingHabit(inputSleepingHourDTO);
			return new ResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/api/admin/sleeping-habit/get-all-user-habit")
	public ResponseEntity<List<SleepingHabit>> findAllUserHabit() {
		try {
			final List<SleepingHabit> result = this.sleepingHabitService.findAllByActive(Boolean.TRUE);
			if(result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST,HttpStatus.OK);
			}	
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
