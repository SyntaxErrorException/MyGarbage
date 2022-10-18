package com.example.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Schedule;
import com.example.app.mapper.UserMapper;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return mapper.selectByLoginId(username);//userNameだとエラーになる
	}

	@Override
	public List<Schedule> getSchedule(int id) {
		return mapper.showSchedule(id);
	}

	@Override
	public Map<Integer,List<String>> mapTo(List<Schedule> schedules) {

		return null;
	}
}
