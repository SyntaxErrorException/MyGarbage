package com.example.app.service;

import java.util.List;

import com.example.app.domain.User;

public interface AdminService {
	List<User> getUserList();
	User getUserById(int id);
	void userTerminate(int id);
}
