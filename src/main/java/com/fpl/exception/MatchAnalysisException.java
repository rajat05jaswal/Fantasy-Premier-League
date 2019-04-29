package com.fpl.exception;

public class MatchAnalysisException extends Exception{
	public MatchAnalysisException(String message) {
		super("MatchAnalysisException - "+message);
	}
	
	public MatchAnalysisException(String message, Throwable clause) {
		super("MatchAnalysisException - "+message, clause);
	}
}
