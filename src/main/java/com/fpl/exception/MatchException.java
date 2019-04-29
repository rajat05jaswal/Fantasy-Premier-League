package com.fpl.exception;

public class MatchException extends Exception{
	public MatchException(String message) {
		super("MatchException - "+message);
	}
	
	public MatchException(String message, Throwable clause) {
		super("MatchException - "+message, clause);
	}
}
