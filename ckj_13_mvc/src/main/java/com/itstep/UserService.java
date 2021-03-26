package com.itstep;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
	private UserRepository us;
	private PasswordEncoder pw;
	
	public UserService(UserRepository us) {
		super();
		this.us = us;
	}

	public boolean saveUser(User user) {
        User userFromDB = us.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRole("ROLE_USER");
        user.setUsername(user.getUsername());
        user.setPassword(pw.encode(user.getPassword()));
        return true;
    }
}
