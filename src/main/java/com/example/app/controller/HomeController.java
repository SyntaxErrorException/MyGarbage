package com.example.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.User;


@Controller
public class HomeController {
	
	//@Autowired
	//private UserService service;
	
	@GetMapping({"/", "/home"})
	public String home(@AuthenticationPrincipal  User user) {
		System.out.println(user);
		return "home";
	}
	
	// ログイン済みのユーザー用
	@GetMapping({"/user", "/user/home"})
	public String userPage(@AuthenticationPrincipal  User user, Model model) {
		//予定表示
		//List<Schedule> scheduleList = service.getAll(user.getId());
		//model.addAttribute("schedule", scheduleList);
		model.addAttribute("today",LocalDate.now());
		
		List<String> g = new ArrayList<>();
		g.add("ビン");
		g.add("カン");
		g.add("プラスチック");
		System.out.println(g.size());
		model.addAttribute("garbageList", g);
		
		return "userPage";
	}
	
	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
	
}