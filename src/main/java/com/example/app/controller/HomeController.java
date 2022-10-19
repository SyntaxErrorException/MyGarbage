package com.example.app.controller;

import java.time.DayOfWeek;
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
		List<Schedule> schedules = service.getSchedule(user.getId());
		StringBuilder[] strb = new StringBuilder[7];
		strb[0] = new StringBuilder();
		strb[1] = new StringBuilder();
		strb[2] = new StringBuilder();
		strb[3] = new StringBuilder();
		strb[4] = new StringBuilder();
		strb[5] = new StringBuilder();
		strb[6] = new StringBuilder();
		for (Schedule s : schedules) {
			System.out.println(s);
			var dow = s.getDayOfWeek();
			String str = s.getGarbage();
			switch (dow) {
			case 1:
				strb[0].append(str + " ");
				break;
			case 2:
				strb[1].append(str+ " ");
				break;
			case 3:
				strb[2].append(str+ " ");
				break;
			case 4:
				strb[3].append(str+ " ");
				break;
			case 5:
				strb[4].append(str+ " ");
				break;
			case 6:
				strb[5].append(str+ " ");
				break;
			case 7:
				strb[6].append(str+ " ");
				break;
			}
		}
		model.addAttribute("dow", DayOfWeek.class);
		model.addAttribute("strb",strb);
		model.addAttribute("schedules", schedules);
		model.addAttribute("today",LocalDate.now());
		return "userPage";
	}
	
	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
	
}