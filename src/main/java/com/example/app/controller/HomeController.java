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
		
		//曜日の配列を作る
		for(int i = 1; i <= 7; i++) {
			strb[i-1] = new StringBuilder();
		}
		
		for (Schedule s : schedules) {
			//確認のためにコンソールに表示する
			System.out.println(s);
			
			//スケジュールから曜日とゴミの種類を変数に入れる
			var dow = s.getDayOfWeek();
			String str = s.getGarbage();
			
			//ゴミの種類を曜日の配列に格納する
			for(int i = 1; i <= 7; i++) {
				if(dow == i){
					strb[i-1].append(str + ",");
				}
			}
		}
		
		//最後の文字（カンマ）を除去する
		 for(int i = 0; i < 7; i++) {
			 if(strb[i].length()!=0) {
				 strb[i].delete(strb[i].length()-1,strb[i].length()); 
			 }
		 }
	
		//今日のゴミの種類を変数に入れる
		DayOfWeek dow = LocalDate.now().getDayOfWeek();
		String todayGarbage = strb[dow.getValue()-1].toString();
		
		LocalDate today = LocalDate.now();
		
		model.addAttribute("todayGarbage", todayGarbage);
		model.addAttribute("strb",strb);
		model.addAttribute("today",today);
		model.addAttribute("dow", dow);
		
		return "userPage";
	}
	
	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
	
}