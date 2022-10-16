package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.domain.Schedule;
import com.example.app.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return mapper.selectByLoginId(username);//userNameだとエラーになる
	}

	@Override
	public Schedule getAll(int id) {
		return mapper.selectALL(id);
	}

}

