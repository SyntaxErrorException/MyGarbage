package com.example.app.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Garbage;
import com.example.app.domain.Schedule;
import com.example.app.domain.User;

@Mapper
public interface UserMapper {
	User selectByLoginId(String loginId);
	List<Schedule> showSchedule(int id) throws Exception;
	void registUser(User user) throws Exception;
	void insertRole(Integer role) throws Exception;
	void insertSchedule(Schedule schedule) throws Exception;
	void insertGarbageId() throws Exception;
	List<Garbage> selectGarbages() throws Exception;
	void insertNonBurnable(@Param("weeks") List<Integer> weeks, @Param("youbi") Integer youbi, @Param("userId") Integer userId) throws Exception;
	void deleteSchedule(@Param("userId") Integer userId,@Param("garbage")  Integer garbage,@Param("youbi")  List<Integer> youbi) throws Exception;
	void deleteNonBurnable(@Param("userId") Integer userId,@Param("weeks") List<Integer> weeks, @Param("youbi") Integer youbi) throws Exception;
	void deleteAll(Integer userId) throws Exception;
	void deleteNonBurnableNoCheck(Integer userId) throws Exception;
}
