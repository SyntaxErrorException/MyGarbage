package com.example.app.controller;

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
	public String userPage(Model model) {
		//予定表示
		Schedule schedule = service.getAll(1);
		model.addAttribute("schedule", schedule);
		return "userPage";
	}
	
	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
	
}