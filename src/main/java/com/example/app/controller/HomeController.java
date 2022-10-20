package com.example.app.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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

	@GetMapping({ "/", "/home" })
	public String home(@AuthenticationPrincipal User user) {
		System.out.println(user);
		return "home";
	}

	// ログイン済みのユーザー用
	@GetMapping({ "/user", "/user/home" })
	public String userPage(@AuthenticationPrincipal User user, Model model) {
		//DBから予定を取得する
		List<Schedule> schedules = service.getSchedule(user.getId());

		//ゴミ種類表示用の文字列配列を生成する
		StringBuilder[] strb = new StringBuilder[7];

		//曜日の配列を作る
		for (int i = 0; i < 7; i++) {
			strb[i] = new StringBuilder();
		}

		for (Schedule s : schedules) {
			//確認のためにコンソールに表示する
			//Schedule(id=1, dayOfWeek=2, garbage=可燃ごみ)
			System.out.println(s);

			//曜日とゴミの種類を変数に入れる
			int dow = s.getDayOfWeek();
			String str = s.getGarbage();

			//ゴミの種類を曜日の配列に格納する
			for (int i = 1; i <= 7; i++) {
				if (dow == i) {
					strb[i - 1].append(str + "・");
				}
			}
		}

		//余分な区切り文字を除去する
		for (int i = 0; i < 7; i++) {
			if (strb[i].length() != 0) {
				strb[i].delete(strb[i].length() - 1, strb[i].length());
			}
		}

		//午前8時前と以後でメッセージを変える
		DayOfWeek dow = LocalDate.now().getDayOfWeek();
		LocalTime now = LocalTime.now();
		String todayGarbage;
		if (now.isBefore(LocalTime.of(8, 0))) {
			todayGarbage = "今日は" + strb[dow.getValue() - 1].toString() + "の日です。";
		} else {
			todayGarbage = "明日は" + strb[dow.getValue()].toString() + "の日です。";
		}

		//表示用の30日分の文字列を用意する
		LocalDate today = LocalDate.now();
		//LocalDate today = LocalDate.of(2022, 12, 14);
		System.out.println(today);
		String[] collectionDate = new String[30];
		List<LocalDate> arrDate = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			int[] n = { 2, 4 };// 第n
			int m = 3;// ?曜日
			for (int j = 0; j < n.length; j++) {
				LocalDate[] nonBurnableWaste = new LocalDate[n.length];
				nonBurnableWaste[j] = today.plusDays(i).with(TemporalAdjusters.dayOfWeekInMonth(n[j], DayOfWeek.of(m)));
				if (today.plusDays(i).isEqual(nonBurnableWaste[j])) {
					arrDate.add(today.plusDays(i));
				}
			}
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd(E)");
		for (int i = 0; i < 30; i++) {
			String strDate = dtf.format(today.plusDays(i));
			for (LocalDate arr : arrDate) {
				if (today.plusDays(i).equals(arr)) {
					collectionDate[i] = strDate
							+ " " + strb[today.plusDays(i).getDayOfWeek().getValue() - 1] + "・不燃ごみ";
				} else {
					collectionDate[i] = strDate + " " + strb[today.plusDays(i).getDayOfWeek().getValue() - 1];
				}
			}
		}

		//確認のためコンソールに表示する
		for (String s : collectionDate) {
			System.out.println(s);
		}

		//表示に必要な変数をmodelに格納する
		model.addAttribute("todayGarbage", todayGarbage);
		model.addAttribute("collectionDate", collectionDate);

		return "userPage";
	}

	// 管理者用
	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}

}