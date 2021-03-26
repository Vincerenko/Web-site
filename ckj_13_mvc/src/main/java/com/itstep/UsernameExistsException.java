package com.itstep;

public class UsernameExistsException extends RuntimeException {
public UsernameExistsException(String username) {
	super("This name already exists" + username);
}
}
