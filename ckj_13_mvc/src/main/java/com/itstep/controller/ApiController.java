package com.itstep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstep.model.User;
import com.itstep.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

	private UserRepository UserRepository;
	
	@GetMapping("/users")
	public Iterable<User> Users() {
		return UserRepository.findAll();
	}
}
