package com.itstep;

public class EmailExistsException extends RuntimeException {
	public EmailExistsException(String email) {
		super("This email already regitred" + email);
	}
}
