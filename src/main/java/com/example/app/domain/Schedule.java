package com.example.app.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Schedule {
	private Integer id;
	private Integer userId;
	private LocalDate date;
	private Integer dayOfWeek;
	private List<String> type;
}
