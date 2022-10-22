package com.example.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.app.domain.Schedule;
import com.example.app.domain.User;

public interface UserService extends UserDetailsService{
	List<Schedule> getSchedule(int id) throws Exception;
	void addUser(User user) throws Exception;
	
	
	
	//スケジュール登録メソッド未完成
	void addSchedule(int id,int dayOfWeek,int garbageId,int week1,int week2,int dow) throws Exception;
}
