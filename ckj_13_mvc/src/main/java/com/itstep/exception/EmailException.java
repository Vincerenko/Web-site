package com.itstep.exception;

public class EmailException extends RuntimeException{
	
	

	public EmailException (String email) {
		super("This email is busy" + email);
	}
		
}
