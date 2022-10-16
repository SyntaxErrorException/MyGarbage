package com.example.app.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Schedule;
import com.example.app.domain.User;

@Mapper
public interface UserMapper {
	User selectByLoginId(String loginId);
	Schedule selectALL(int id);
}
