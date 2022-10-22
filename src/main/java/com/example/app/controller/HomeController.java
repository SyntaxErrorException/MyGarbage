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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.NonBurnableWaste;
import com.example.app.domain.Schedule;
import com.example.app.domain.User;
import com.example.app.service.AdminService;
import com.example.app.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;

	@GetMapping({ "/", "/home" })
	public String home(@AuthenticationPrincipal User user) {
		System.out.println(user);
		return "home";
	}

	// ログイン済みのユーザー用
	@GetMapping({ "/user", "/user/home" })
	public String userPage(@AuthenticationPrincipal User user, Model model) {
		// DBから予定を取得する
		List<Schedule> schedules = userService.getSchedule(user.getId());

		// 曜日ごとのゴミの配列を作る
		StringBuilder[] strb = new StringBuilder[7];

		// 曜日の配列を作る
		for (int i = 0; i < 7; i++) {
			strb[i] = new StringBuilder();
		}

		// スケジュールを作る
		for (Schedule s : schedules) {
			// 確認のためにコンソールに表示する
			// 例：Schedule(id=1, dayOfWeek=2, garbage=可燃ごみ)
			System.out.println(s);

			// 曜日とゴミの種類を変数に入れる
			int dow = s.getDayOfWeek();
			String str = s.getGarbage();

			//曜日に対応したゴミの種類を連結していく
			for (int i = 1; i <= 7; i++) {
				if (dow == i) {
					strb[i - 1].append(str + "・");
				}
			}
		}

		// 余分な区切り文字を除去する
		for (int i = 0; i < 7; i++) {
			if (strb[i].length() != 0) {
				strb[i].delete(strb[i].length() - 1, strb[i].length());
			}
		}

		// 不燃ごみの日のリストを作る
		List<LocalDate> dayOfNonBurnableWaste = new ArrayList<>();
		LocalDate today = LocalDate.now();
		// LocalDate today = LocalDate.of(2022, 10, 25);
		// 不燃ごみ用の配列と変数を立てる
		Integer[] n = new Integer[2];// 第n
		Integer Question = 0;// ?曜日
		NonBurnableWaste nbw = schedules.get(0).getNonBurnableWaste();
		n[0] = nbw.getWeek1();
		n[1] = nbw.getWeek2();
		Question = nbw.getDayOfWeek();
		if (n[0] != null) {
			LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
			for (int j = 0; j < 2; j++) {
				//今月
				dayOfNonBurnableWaste.add(today.with(TemporalAdjusters.dayOfWeekInMonth(n[j], DayOfWeek.of(Question))));
				//来月
				dayOfNonBurnableWaste.add(
						firstDayOfNextMonth.with(TemporalAdjusters.dayOfWeekInMonth(n[j], DayOfWeek.of(Question))));
			}
		}

		// 表示用の30日分の文字列を用意する
		String[] dateAndGarbage = new String[30];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd(E)");
		for (int i = 0; i < 30; i++) {
			LocalDate tdy = today.plusDays(i);
			String strDate = dtf.format(tdy);
			String row = strDate + " " + strb[tdy.getDayOfWeek().getValue() - 1];
			for (LocalDate li : dayOfNonBurnableWaste) {
				if (tdy.isEqual(li)) {
					if (strb[tdy.getDayOfWeek().getValue() - 1].isEmpty()) {
						dateAndGarbage[i] = row + "不燃ごみ";
					} else {
						dateAndGarbage[i] = row + "・不燃ごみ";
					}
					break;// 不燃ごみの日に一致したらforから抜ける
				}
				dateAndGarbage[i] = strDate + " " + strb[tdy.getDayOfWeek().getValue() - 1];
			}
		}

		// 午前8時前と以後でメッセージを変える
		DayOfWeek dow = today.getDayOfWeek();
		LocalTime now = LocalTime.now();
		String todayGarbage;
		if (now.isBefore(LocalTime.of(8, 0))) {
			if (strb[dow.getValue() - 1].isEmpty()) {
				todayGarbage = "今日のゴミ収集はありません。";
			} else {
				todayGarbage = "今日は" + dateAndGarbage[0].replaceFirst("^.*\s", "") + "の日です。";
			}
		} else {
			if (strb[dow.getValue()].isEmpty()) {
				todayGarbage = "明日のゴミ収集はありません。";
			} else {
				todayGarbage = "明日は" + dateAndGarbage[1].replaceFirst("^.*\s", "") + "の日です。";
			}
		}

		// 確認のためコンソールに表示する
		for (String s : dateAndGarbage) {
			System.out.println(s);
		}

		// 表示に必要な変数をmodelに格納する
		model.addAttribute("todayGarbage", todayGarbage);
		model.addAttribute("dateAndGarbage", dateAndGarbage);

		return "userPage";
	}

	// 管理者用
	@GetMapping("/admin")
	public String adminPage(Model model) {
		List<User> userList = adminService.getUserList();
		model.addAttribute("userList", userList);
		return "adminPage";
	}

	@GetMapping("/admin/delete/{id}")
	public String userDelete(Model model,@PathVariable Integer id) {
		User userInfo = adminService.getUserById(id);
		model.addAttribute("userInfo", userInfo);
		return "delete";
	}

	@PostMapping("/admin/delete/{id}")
	public String userDelete(Model model,@PathVariable Integer id, RedirectAttributes ra) {
		adminService.userTerminate(id);
		ra.addFlashAttribute("msg", "会員を削除しました。");
		return "redirect:/admin";
	}

}