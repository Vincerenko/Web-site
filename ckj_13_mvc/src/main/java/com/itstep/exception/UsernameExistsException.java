package com.itstep.exception;

public class UsernameExistsException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = -7461783639778764569L;

public UsernameExistsException(String username) {
	super("This name already exists" + username);
}
}
