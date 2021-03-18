package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	
	@GetMapping("/hello")
	public String hello(@RequestParam(name="user")String user,@RequestParam(name="age")String age, Model model) {
		String msg = String.format("%s , %s", user,age);
		model.addAttribute("message", msg);
		System.out.println("Request geted!");
		return "hello-view";
	}
/*	@GetMapping("/calc")
	public String calc(@RequestParam(name="one")int one,@RequestParam(name="two")int two,@RequestParam(name="oper")String oper,Model model) {
		
		String msg = String.format("Result: %d"+"%s"+"%d =" + q , one,oper,two);
		
		model.addAttribute("cal", msg);
		return "hello-view";
	}*/
	
}
