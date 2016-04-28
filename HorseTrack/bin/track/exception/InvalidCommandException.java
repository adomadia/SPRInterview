package org.horse.track.exception;

public class InvalidCommandException extends Exception {
	
	private static final long serialVersionUID = -2283196639413873418L;

	public InvalidCommandException() {

	}
	
	public InvalidCommandException(String message){
		super(message);
	}
}
