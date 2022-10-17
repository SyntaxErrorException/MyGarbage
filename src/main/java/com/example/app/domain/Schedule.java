package com.example.app.domain;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class Schedule {
	private Integer id;
	private Integer userId;
	private Integer daysAgo;
	private LocalTime time;
	private Integer dayOfWeek;
	private List<String> type;
	
	public String getDayOfWeekJa() {
		String[] days = {"月", "火","水","木","金","土","日",};
		return days[dayOfWeek-1];
	}
}
