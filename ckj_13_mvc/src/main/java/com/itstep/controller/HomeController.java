package com.itstep.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itstep.exception.EmailException;
import com.itstep.exception.UsernameExistsException;
import com.itstep.model.ConfirmToken;
import com.itstep.model.User;
import com.itstep.repository.ConfirmTokenRepository;
import com.itstep.repository.UserRepository;
import com.itstep.service.MailService;
import com.itstep.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	private UserRepository ur;
	private UserService userService;
	private ConfirmTokenRepository tokenRepository;
	private MailService mailService;
	private PasswordEncoder pw;

	@GetMapping
	public String index(Principal prl, Model model) {
		if (prl != null) {
			User user = ur.findByUsername(prl.getName());
			String msg = "Hello, " + user.getUsername() + " on my website!";
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
	public String registerNewUser(@RequestParam("username") String username, @RequestParam("lastname") String lastname,
			@RequestParam("age") int age, @RequestParam("password") String password,
			@RequestParam("email") String email) {
		try {
			User user = userService.register(username, lastname, age, password, email);
			ConfirmToken token = new ConfirmToken(user);
			String link = "http:/localhost:8090/confirm?token=" + token.getValue();
			String text = "Please, continue registr yout acc! " + link;
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setSubject("Confirm your email");
			mail.setFrom("GreatCompany@mail.ru");
			mail.setTo(user.getEmail());
			mail.setText(text);
			tokenRepository.save(token);
			mailService.sendMail(mail);
			return "redirect:/";
		} catch (UsernameExistsException e) {
			return "redirect:/signup?eror";
		} catch (EmailException e) {
			return "redirect:/signup?eror";
		}

	}

	@GetMapping("/forgotPassword")
	public String forgotPassword(@RequestParam("email") String email) {
		User user = ur.findByEmail(email);
		String newPassword = UUID.randomUUID().toString();
		if (user != null) {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setSubject("Your new password !");
			mail.setFrom("Support@mail.com");
			mail.setTo(email);
			mail.setText("Your new password: " + newPassword);
			user.setPassword(pw.encode(newPassword));
			ur.save(user);
			mailService.sendMail(mail);
			return "redirect:/login";
		} else {
			return "redirect:/index";
		}

	}

	@GetMapping("/confirm")
	public String confirmEmailWithToken(@RequestParam("token") String token1) {
		ConfirmToken token = tokenRepository.findByValue(token1);
		User user = token.getUser();
		if (token.getValue().equals(token1)) {
			user.setEnabled(true);
			tokenRepository.delete(token);
		}
		return "/notes";
	}

}
