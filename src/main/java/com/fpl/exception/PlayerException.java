package com.fpl.exception;

public class PlayerException extends Exception{
	public PlayerException(String message) {
		super("PlayerException - "+message);
	}
	
	public PlayerException(String message, Throwable clause) {
		super("PlayerException - "+message, clause);
	}
}
