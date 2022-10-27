package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String home() {
		return "home";
	}

	@GetMapping("register")
	public String registerGet(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("register")
	public String registerPost(@Valid @ModelAttribute User user, Errors errors, RedirectAttributes ra)
			throws Exception {
		if (errors.hasErrors()) {
			return "redirect:/register";
		}
		for (User u : adminService.getUserList()) {
			if (user.getLoginId().equals(u.getLoginId())||user.getLoginId().equals("administrator")) {
				ra.addFlashAttribute("duplicatedId", "他のユーザとIDが重複しています。");
				return "redirect:/register";
			}
		}
		userService.addUser(user);
		return "redirect:/login";
	}

}