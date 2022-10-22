package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.User;
import com.example.app.mapper.AdminMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper mapper;

	@Override
	public List<User> getUserList() {
		return mapper.showAllUsers();
	}


	@Override
	public User getUserById(int id) {
		return mapper.showUserInfo(id);
	}

	@Override
	public void userTerminate(int id) {
		mapper.userDelete(id);
	}

}
