package com.itstep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itstep.exception.UsernameExistsException;
import com.itstep.model.User;
import com.itstep.repository.UserRepository;

@Service
public class UserService {
	private UserRepository us;
	private PasswordEncoder pw;
	
	@Autowired
	public UserService(UserRepository us, PasswordEncoder pw) {
		this.us = us;
		this.pw = pw;
	}
	public User register(String username,String lastname,int age, String password, String email) {
		User user = us.findByUsername(username);
		User user2= us.findByEmail(email);
		if(user==null &&  user2==null) {
			user=new User();
			user.setUsername(username);
			user.setLastname(lastname);
			user.setAge(age);
			user.setPassword(pw.encode(password));
			user.setEmail(email);
			user.setRole("ROLE_USER");
			user.setEnabled(false);
			return us.save(user);
		}
		
		else   {
			throw new UsernameExistsException(username);
		}
		
		
	}
	
	

	
}
