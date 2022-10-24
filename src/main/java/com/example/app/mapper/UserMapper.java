package com.example.app.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Garbage;
import com.example.app.domain.NonBurnableWaste;
import com.example.app.domain.Schedule;
import com.example.app.domain.User;

@Mapper
public interface UserMapper {
	User selectByLoginId(String loginId);
	List<Schedule> showSchedule(int id) throws Exception;
	void registUser(User user) throws Exception;
	void insertRole(Integer role) throws Exception;
	//スケジュール登録メソッド未完成
	void insertSchedule(Schedule schedule) throws Exception;
	void insertGarbageId() throws Exception;
	List<Garbage> selectGarbages() throws Exception;
	void insertNonBurnable(NonBurnableWaste nonBurnableWaste) throws Exception;
	
}
