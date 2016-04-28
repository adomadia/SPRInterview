package org.horse.track.exception;

public class InvalidCommandException extends Exception {

	private static final long serialVersionUID = -2275785513395043746L;

	public InvalidCommandException(String message) {
		super(message);
	}
}
