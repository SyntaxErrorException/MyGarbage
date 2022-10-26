package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.User;
import com.example.app.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/home" })
	public String home() {
		return "home";
	}
	
	@GetMapping("register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@PostMapping("register")
	public String registerPost(@Valid @ModelAttribute User user,Errors errors) throws Exception {
		if(errors.hasErrors()) {
			return "register";
		}
		userService.addUser(user);
		return "redirect:/login";
	}
	
}