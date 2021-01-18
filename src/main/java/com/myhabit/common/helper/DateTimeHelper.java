package com.myhabit.common.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class DateTimeHelper {
	
	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Ho_Chi_Minh");
	private static final LocalDate TODAY = LocalDate.now( ZONE_ID );
	private static final DateTimeFormatter dtf = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern(("dd/MM/yyyy")))
            .toFormatter();
	
	private static final DateTimeFormatter monthFormat = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern(("MM/yyyy")))
            .toFormatter();
	public static final DateTimeFormatter dateTimeFormat = new 	DateTimeFormatterBuilder()
			.appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern(("dd/M/yyyy HH:mm")))
            .toFormatter();
			

	public static LocalDate getMondayDateInThisWeek(String week) {
		LocalDate currentTime = LocalDate.parse(week, dtf);
		LocalDate monday = currentTime.with( TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY) );
		return monday;
	}
	
	public static LocalDate getSundayDateInThisWeek(String week) {
		LocalDate currentTime = LocalDate.parse(week, dtf);
		LocalDate sunday = currentTime.with( TemporalAdjusters.nextOrSame( DayOfWeek.SUNDAY ) );
		return sunday;
	}
	
	public static LocalDateTime convertStringToLocalDateTime(String time) {
		LocalDateTime converted = LocalDateTime.parse(time, dateTimeFormat);
		return converted;
	}
	
	public static LocalDate convertStringToLocalDate(String date) {
		LocalDate convertedDate = LocalDate.parse(date, dtf);
		return convertedDate;
	}
	
	public static LocalDate getFirstDayOfMonth(String month) {
		YearMonth currentMonth = YearMonth.parse(month, monthFormat);
		LocalDate firstDateOfMonth = currentMonth.atDay(1);
		return firstDateOfMonth;
	}
	
	public static LocalDate getLastDateOfMonth(String month) {
		YearMonth currentMonth = YearMonth.parse(month, monthFormat);
		LocalDate lastDateOfMonth = currentMonth.atEndOfMonth();
		return lastDateOfMonth;
	}
	
}
