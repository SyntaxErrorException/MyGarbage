package com.example.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.app.domain.Garbage;
import com.example.app.domain.NonBurnableWaste;
import com.example.app.domain.Schedule;
import com.example.app.domain.User;

public interface UserService extends UserDetailsService{
	List<Schedule> getSchedule(int id) throws Exception;
	void addUser(User user) throws Exception;
	void addSchedule(Schedule schedule) throws Exception;
	List<Garbage> getGarbageList() throws Exception;
	void addNonBurnable(NonBurnableWaste nonBurnableWaste) throws Exception;
	void removeSchedule(Schedule schedule) throws Exception;
	void removeNonBurnable(NonBurnableWaste nonBurnable) throws Exception;
	void removeAll(Integer userId) throws Exception;
}
