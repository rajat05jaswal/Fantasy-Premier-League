package com.fpl.exception;

public class TeamException extends Exception{
	public TeamException(String message) {
		super("TeamException - "+message);
	}
	
	public TeamException(String message, Throwable clause) {
		super("TeamException - "+message, clause);
	}
}
