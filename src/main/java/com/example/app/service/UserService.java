package com.example.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.app.domain.Schedule;

public interface UserService extends UserDetailsService{
	Schedule getAll(int id);
}
