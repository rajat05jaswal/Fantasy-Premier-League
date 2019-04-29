package com.fpl.exception;

public class WeekException extends Exception{
	public WeekException(String message) {
		super("WeekException - "+message);
	}
	
	public WeekException(String message, Throwable clause) {
		super("WeekException - "+message, clause);
	}
}
