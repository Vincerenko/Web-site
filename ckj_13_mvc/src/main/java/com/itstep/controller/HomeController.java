package com.itstep.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
	public String registerNewUser(
			@RequestParam("username") String username,
			@RequestParam("lastname") String lastname,
			@RequestParam("age") int age,
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		try {
			User user = userService.register(username,lastname,age, password, email);
			ConfirmToken token = new ConfirmToken(user);
			String link = "http:/localhost:8090/confirm?token=" + token.getValue();
			String text ="Please, continue registr yout acc!" + link;
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setSubject("Confirm yout email");
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
	@GetMapping("/confirm")
	public String  confirmEmailWithToken(@RequestParam("token")String token1) {
		//извлечь значение токена из запроса
		//проверить есть ли в базе такой токен
		//если есть, isEnable =true
		// удалить токен
		User user = new User();
		//user=ur.findByUsername(userService.);
		ConfirmToken token =tokenRepository.findByValue(token1);
		
        if(token!=null) {
        	user.setEnabled(true);
        }
        tokenRepository.delete(token);
		return"/notes";
	}

}
