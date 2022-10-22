package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.User;

@Mapper
public interface AdminMapper {
	List<User> showAllUsers();
	User showUserInfo(int id);
	void userDelete(int id);
}
