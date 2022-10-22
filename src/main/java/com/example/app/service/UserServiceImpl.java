package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Schedule;
import com.example.app.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return mapper.selectByLoginId(username);//userNameだとエラーになる
	}

	@Override
	public List<Schedule> getSchedule(int id) throws Exception {
		return mapper.showSchedule(id);
	}

	@Override
	public void addSchedule(int id, int dayOfWeek, int garbageId, int week1, int week2, int dow) throws Exception {
		mapper.insertSchedule(id,dayOfWeek,garbageId,week1,week2,dow);
	}

}
