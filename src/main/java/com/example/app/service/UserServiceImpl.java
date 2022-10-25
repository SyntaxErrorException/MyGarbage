package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Garbage;
import com.example.app.domain.NonBurnableWaste;
import com.example.app.domain.Schedule;
import com.example.app.domain.User;
import com.example.app.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return mapper.selectByLoginId(username);//userNameだとエラーになる
	}

	@Override
	public List<Schedule> getSchedule(int id) throws Exception {
		return mapper.showSchedule(id);
	}
	
	//スケジュール登録メソッド未完成
	@Override
	public void addSchedule(Schedule schedule) throws Exception {
		mapper.insertSchedule(schedule);
		//mapper.insertGarbageId(schedule.getId());
	}

	@Override
	public void addUser(User user) throws Exception {
		String pass = BCrypt.hashpw(user.getLoginPass(), BCrypt.gensalt());
		user.setLoginPass(pass);
		mapper.registUser(user);
		mapper.insertRole(user.getId());
	}

	@Override
	public List<Garbage> getGarbageList() throws Exception {
		return mapper.selectGarbages();
	}

	@Override
	public void addNonBurnable(NonBurnableWaste nonBurnableWaste) throws Exception {
		mapper.insertNonBurnable(nonBurnableWaste.getWeek(), nonBurnableWaste.getDayOfWeek(), nonBurnableWaste.getUserId());
	}


}
