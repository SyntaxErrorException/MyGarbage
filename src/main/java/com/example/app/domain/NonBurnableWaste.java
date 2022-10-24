package com.example.app.domain;

import lombok.Data;

@Data
public class NonBurnableWaste {
	private Integer user_id;
	private Integer week1;
	private Integer week2;
	private Integer dayOfWeek;
	private String strWeeks;
}
