package com.fpl.exception;

public class UserException extends Exception{
	public UserException(String message) {
		super("UserException - "+message);
	}
	
	public UserException(String message, Throwable clause) {
		super("UserException - "+message, clause);
	}
}
