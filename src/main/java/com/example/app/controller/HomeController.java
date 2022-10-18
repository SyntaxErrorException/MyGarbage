package com.example.app.controller;

import java.time.LocalDate;
import java.util.List;

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
		List<Schedule> schedule = service.getSchedule(user.getId());
		for (Schedule s : schedule) {
			System.out.println(s);
		}
		model.addAttribute("today",LocalDate.now());
		model.addAttribute("schedule", schedule);
		return "userPage";
	}
	
	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
	
}