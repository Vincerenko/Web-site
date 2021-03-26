package com.itstep;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping("/admin_page")
	public String adminPage() {
		return "admin_page";
	}

	@GetMapping("/login")
	public String singIn() {
		return "login";
	}
	@GetMapping("/registr")
	public String registr() {
		return "registr";
	}
	
	
		
	
}
