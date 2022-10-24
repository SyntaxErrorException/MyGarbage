package com.example.app.domain;

import lombok.Data;

@Data
public class ScheduleForm {
	private Integer id;
	private Integer userId;
	private Integer garbageId;
	private Integer dayOfWeek;

}
