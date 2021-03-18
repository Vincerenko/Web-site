package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@GetMapping("/hello")
	public String hello(
			@RequestParam(name = "user") String user,
			@RequestParam(name = "city", required = false, defaultValue = "Earth") String city,
			Model model) {
		String msg = String.format("Hello, %s from %s!", user, city);
		model.addAttribute("message", msg);
		return "hello-view";
	}
}
