package com.example.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.app.domain.Schedule;

public interface UserService extends UserDetailsService{
	List<Schedule> getSchedule(int id);
	Map<Integer,List<String>> mapTo(List<Schedule> schedule);
}
