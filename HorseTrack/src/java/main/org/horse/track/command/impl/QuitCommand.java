package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommandExecutable;
import org.horse.track.command.ICommandValidator;

/**
 * Represent as quit command
 * A QuitCommand is used to exit out of the simulator by entering 'Q' or 'q'
 * @author Ashvin Domadia
 */
public class QuitCommand implements ICommandExecutable, ICommandValidator {
	
	public final static String COMMAND_KEYWORD = "Q";
	
	private final String syntax;
	
	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public QuitCommand(String syntax) {
		this.syntax = syntax.trim();
	}

	/**
	 * Method validates the Q command syntax.
	 */
	@Override
	public void validate() {
		StringTokenizer tokenizer = new StringTokenizer(syntax);
		if(tokenizer.countTokens() != 1){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}
	}

	/**
	 * Empty implementation of an execute method.
	 * Nothing to execute for this command.
	 */
	@Override
	public boolean execute() {
		return false;
	}
}
