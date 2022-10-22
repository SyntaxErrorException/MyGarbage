package com.example.app.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Schedule;
import com.example.app.domain.User;

@Mapper
public interface UserMapper {
	User selectByLoginId(String loginId);
	List<Schedule> showSchedule(int id) throws Exception;
	void registUser(User user) throws Exception;
	
	
	//スケジュール登録メソッド未完成
	void insertSchedule(int id,int dayOfWeek,int garbageId,int week1,int week2,int dow);
}
