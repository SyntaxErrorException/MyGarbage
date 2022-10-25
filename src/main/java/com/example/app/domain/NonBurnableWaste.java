package com.example.app.domain;

import java.util.List;

import lombok.Data;

@Data
public class NonBurnableWaste {
	private Integer userId;
	private List<Integer> week;
	private Integer dayOfWeek;
}
