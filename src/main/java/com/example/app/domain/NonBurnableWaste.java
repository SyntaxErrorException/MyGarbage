package com.example.app.domain;

import java.util.List;

import lombok.Data;

@Data
public class NonBurnableWaste {
	private Integer user_id;
	private List<Integer> week;
	private Integer dayOfWeek;
}
