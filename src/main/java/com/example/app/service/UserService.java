package com.example.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.app.domain.Garbage;
import com.example.app.domain.Schedule;

public interface UserService extends UserDetailsService{
	List<Schedule> getSchedule(int id);
	List<Garbage> mapTo(List<Schedule> schedule);
}
