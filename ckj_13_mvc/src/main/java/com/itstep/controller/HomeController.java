package com.itstep.controller;

import java.net.http.HttpRequest;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itstep.EmailExistsException;
import com.itstep.UserService;
import com.itstep.UsernameExistsException;
import com.itstep.model.User;
import com.itstep.repository.UserRepository;

@Controller
public class HomeController {
	private UserRepository us;
	private UserService userService;
	
	
	@Autowired
	public HomeController(UserRepository us, UserService userService) {
		this.us = us;
		this.userService = userService;
	}


	@GetMapping
	public String index( Principal prl, Model model) {
		if(prl!=null) {
		User user = us.findByUsername(prl.getName());
		String msg = "Hello, "+ user.getUsername();
		model.addAttribute("message", msg);
		}
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
	@GetMapping("/signup")
	public String registr() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String registerNewUser(
			@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("email")String email,
			HttpServletRequest request){
		try {
		userService.register(username, password, email);
		request.login(username, password);
		return "redirect:/login";
		}catch(UsernameExistsException e) {
			return "redirect:/signup?eror";
		} catch (ServletException e) {
			e.printStackTrace();
			return "redirect:/login";
		}
		catch(EmailExistsException i) {
			i.printStackTrace();
			return "redirect:/signup";
		}
			
		}
	
}
	
	
	
	
		
	
