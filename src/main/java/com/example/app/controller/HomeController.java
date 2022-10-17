package com.example.app.controller;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.Schedule;
import com.example.app.domain.User;
import com.example.app.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	private UserService service;
	
	@GetMapping({"/", "/home"})
	public String home(@AuthenticationPrincipal  User user) {
		System.out.println(user);
		return "home";
	}
	
	// ログイン済みのユーザー用
	@GetMapping({"/user", "/user/home"})
	public String userPage(@AuthenticationPrincipal  User user, Model model) {
		//予定表示
		Locale locale = Locale.getDefault();
		List<Schedule> scheduleList = service.getAll(user.getId());
		Integer i =scheduleList.get(0).getDayOfWeek();
		System.out.println(DayOfWeek.of(i));
		System.out.println(DayOfWeek.of(i).getDisplayName(TextStyle.SHORT,locale));
		
		
		model.addAttribute("schedule", scheduleList);
		return "userPage";
	}
	
	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
	
}