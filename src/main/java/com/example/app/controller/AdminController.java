package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.User;
import com.example.app.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	// 管理者用
	@GetMapping("/admin")
	public String adminPage(Model model) {
		List<User> userList = adminService.getUserList();
		model.addAttribute("userList", userList);
		return "admin/adminPage";
	}

	@GetMapping("/admin/delete/{id}")
	public String userDelete(Model model,@PathVariable Integer id) {
		User userInfo = adminService.getUserById(id);
		model.addAttribute("userInfo", userInfo);
		return "admin/delete";
	}

	@PostMapping("/admin/delete/{id}")
	public String userDelete(Model model,@PathVariable Integer id, RedirectAttributes ra) {
		adminService.userTerminate(id);
		ra.addFlashAttribute("msg", "会員を削除しました。");
		return "redirect:/admin";
	}
}
