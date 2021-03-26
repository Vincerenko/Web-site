package com.itstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	public User register(String username, String password, String email) {
		//1.необходимо проверить что username свободен 
		//Если username занят, выкидываем execetion который обрабатываем в контроллере
		//Если свободен продолжаем регистрацию
		//2,Закодируем пароль
		//Присвоить роль
		//Сохранить пользователя в базе
		User user = us.findByUsername(username);
		if(user==null || user.getEmail()!=email) {
			user=new User();
			user.setUsername(username);
			user.setPassword(pw.encode(password));
			user.setEmail(email);
			user.setRole("ROLE_USER");
			return us.save(user);
		}else {
			throw new UsernameExistsException(username);
		}
		
	}
	
	

	
}
