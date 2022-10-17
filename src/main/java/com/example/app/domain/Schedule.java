package com.example.app.domain;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Schedule {
	private Integer id;
	private Integer userId;
	private Integer daysAgo;
	private LocalTime time;
	private Integer dayOfWeek;
	private String type;
}
