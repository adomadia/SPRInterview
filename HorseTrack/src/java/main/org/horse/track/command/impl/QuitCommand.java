package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IValidator;

public class QuitCommand implements ICommand, IValidator {
	
	public final static String COMMAND_KEYWORD = "Q";
	
	private final String syntax;
	
	public QuitCommand(String syntax) {
		this.syntax = syntax.trim();
	}

	//Validate the command syntax
	@Override
	public void validate() {
		StringTokenizer tokenizer = new StringTokenizer(syntax);
		if(tokenizer.countTokens() != 1){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}
	}

	@Override
	public void execute() {

	}
}
