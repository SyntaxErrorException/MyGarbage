package com.example.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.app.domain.Schedule;

public interface UserService extends UserDetailsService{
	List<Schedule> getAll(int id);
}
