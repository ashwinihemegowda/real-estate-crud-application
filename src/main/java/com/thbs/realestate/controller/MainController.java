package com.thbs.realestate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	//User login
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	//default page
	@GetMapping("/")
	public String index() {
		return "index";
	}

	//View propertylist
	@GetMapping("/home")
	public String home() {
		return "home";
	}

	//Admin login
	@GetMapping("/adminLogin")
	public String adminLogin() {
		return "adminLogin";
	}

	//About Us
	@GetMapping("/about")
	public String aboutUs() {
		return "about";
	}

	//Contact Us
	@GetMapping("/contactUs")
	public String contactUs() {
		return "contacts";
	}


}
