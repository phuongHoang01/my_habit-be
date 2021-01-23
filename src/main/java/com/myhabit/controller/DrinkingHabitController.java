package com.myhabit.controller;

import java.time.DateTimeException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myhabit.common.helper.ResponseMessage;
import com.myhabit.dto.drinking_habit.InputDrinkingHabitDTO;
import com.myhabit.dto.habit.StaisticalHabitDTO;
import com.myhabit.entities.DrinkingHabit;
import com.myhabit.service.DrinkingHabitService;

@RestController
public class DrinkingHabitController {

	private DrinkingHabitService drinkHabitService;

	public DrinkingHabitController(DrinkingHabitService drinkHabitService) {
		this.drinkHabitService = drinkHabitService;
	}

	@PostMapping("/api/drinking-habits/input-mili-water")
	public ResponseEntity InputdrinkingHabitDTO(@RequestBody InputDrinkingHabitDTO inputdrinkingHabitCaloDTO) {
		try {
			this.drinkHabitService.inputMiliWater(inputdrinkingHabitCaloDTO);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/api/drinking-habits/statistical-total-calo-in-week")
	public ResponseEntity<List<StaisticalHabitDTO>> getdrinkingHabitTotalCaloInWeek(
			@RequestParam(required = true) String week) {
		try {
			final List<StaisticalHabitDTO> result = this.drinkHabitService.getTotalInWeek(week);
			if (result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST, HttpStatus.OK);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (DateTimeException e) {
			return new ResponseEntity(ResponseMessage.DATE_FORMAT_NOT_CORRECT, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/drinking-habits/statistical-total-calo-in-month")
	public ResponseEntity<List<StaisticalHabitDTO>> getdrinkingHabitTotalCaloInMonth(
			@RequestParam(required = true) String month) {
		try {
			final List<StaisticalHabitDTO> result = this.drinkHabitService.getTotalInMonth(month);
			if (result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST, HttpStatus.OK);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (DateTimeException e) {
			return new ResponseEntity(ResponseMessage.DATE_FORMAT_NOT_CORRECT, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/admin/drinking-habits/get-all-user-habit")
	public ResponseEntity<List<DrinkingHabit>> findAllUserHabit() {
		try {
			final List<DrinkingHabit> result = this.drinkHabitService.findAllByActive(Boolean.TRUE);
			if (result.size() == 0) {
				return new ResponseEntity(ResponseMessage.EMPTY_LIST, HttpStatus.OK);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
