package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IValidator;

/**
 * Represent blank input line command
 * A BlankCommand is used to ignore blank input line.
 * @author Ashvin Domadia
 */
public class BlankCommand implements ICommand, IValidator  {

	public final static String COMMAND_KEYWORD = "";
	private final String syntax;


	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public BlankCommand(String syntax) {
		this.syntax = syntax.trim();
	}
	
	
	/**
	 * Method validates the blank input line syntax.
	 */
	@Override
	public void validate() {
		StringTokenizer tokenizer = new StringTokenizer(syntax);
		if(tokenizer.countTokens() != 0){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}
	}

	/**
	 * Empty implementation of an execute method.
	 */
	@Override
	public void execute() { 
		
	}
}
