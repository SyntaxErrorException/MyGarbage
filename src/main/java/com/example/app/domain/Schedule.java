package com.example.app.domain;

import lombok.Data;

@Data
public class Schedule {
	private Integer id;
	private Integer userId;
	private Integer dayOfWeek;
	private Garbage garbage;
	private NonBurnableWaste nonBurnableWaste;
}
