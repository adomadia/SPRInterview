package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IValidator;

public class RestockCommand implements ICommand, IValidator{

	public final static String COMMAND_KEYWORD = "R";
	
	private final String syntax;
	
	public RestockCommand(String syntax) {
		this.syntax = syntax.trim();
	}

	@Override
	public void validate() {

		StringTokenizer tokenizer = new StringTokenizer(syntax);
		
		if(tokenizer.countTokens() != 1){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
