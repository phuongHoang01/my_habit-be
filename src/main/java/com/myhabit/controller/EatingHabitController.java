package com.myhabit.controller;

import java.time.DateTimeException;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myhabit.common.helper.ResponseMessage;
import com.myhabit.dto.eating_habit.EatingHabitByIUserIdDTO;
import com.myhabit.dto.eating_habit.EatingHabitDTO;
import com.myhabit.dto.eating_habit.InputEatingHabitCaloDTO;
import com.myhabit.dto.habit.StaisticalHabitDTO;
import com.myhabit.entities.EatingHabit;
import com.myhabit.entities.SleepingHabit;
import com.myhabit.service.EatingHabitService;


@RestController
public class EatingHabitController {
	
	
	private EatingHabitService eatingHabitService;

	public EatingHabitController(EatingHabitService eatingHabitService) {
		this.eatingHabitService = eatingHabitService;
	}
	
	@GetMapping("/api/admin/eating-habits/get-eating-habit-by-user-id/{userId}")
	public ResponseEntity<List<EatingHabitByIUserIdDTO>> getHabitByUserId(@PathVariable String userId) {
		try {
			List<EatingHabitByIUserIdDTO> eatingHabitsDTO = this.eatingHabitService.findEatingHabitByIUserId(userId);
			return new ResponseEntity<List<EatingHabitByIUserIdDTO>>(eatingHabitsDTO,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PostMapping("/api/eating-habits/input-calo") 
	public ResponseEntity InputEatingHabitCaloDTO(@RequestBody InputEatingHabitCaloDTO inputEatingHabitCaloDTO) {
		try {
			this.eatingHabitService.inputCalo(inputEatingHabitCaloDTO);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/api/eating-habits/statistical-total-calo-in-week")
	public ResponseEntity<List<StaisticalHabitDTO>> getEatingHabitTotalCaloInWeek(@RequestParam(required = true) String week) {
		try {
			final List<StaisticalHabitDTO> result = this.eatingHabitService.getTotalInWeek(week);
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
	
	@GetMapping("/api/eating-habits/statistical-total-calo-in-month")
	public ResponseEntity<List<StaisticalHabitDTO>> getEatingHabitTotalCaloInMonth(@RequestParam(required = true) String month) {
		try {
			final List<StaisticalHabitDTO> result = this.eatingHabitService.getTotalInMonth(month);
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
	
	@GetMapping("/api/admin/eating-habits/get-all-user-habit")
	public ResponseEntity<List<EatingHabit>> findAllUserHabit() {
		try {
			final List<EatingHabit> result = this.eatingHabitService.findAllByActive(Boolean.TRUE);
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
