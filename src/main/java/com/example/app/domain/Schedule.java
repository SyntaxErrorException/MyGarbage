package com.example.app.domain;

import java.util.List;

import lombok.Data;

@Data
public class Schedule {
	private Integer id;
	private Integer dayOfWeek;
	private List<String> garbages;
}
