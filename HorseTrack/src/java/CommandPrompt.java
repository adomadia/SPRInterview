package org.horse.track.support;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.horse.track.exception.InvalidCommandException;

public final class CommandPrompt {

	public final static int MAX_COMMAND_LINE_ARGUMENTS = 2;

	private Scanner scanner = new Scanner(System.in);

	public String input() throws InvalidCommandException {

		String cmd = scanner.nextLine();

		if(!isCommandValid(cmd)){
			throw new InvalidCommandException("Invalid Command: " + cmd);
		}
		return cmd;
	}
	
	public boolean isCommandValid(String cmd){

		StringTokenizer tokenizer = new StringTokenizer(cmd);

		if (tokenizer.countTokens() > MAX_COMMAND_LINE_ARGUMENTS) {
			return false;
		}

		switch (tokenizer.countTokens()) {
		case 1:
			String arg = tokenizer.nextToken();
			if (!("Q".equalsIgnoreCase(arg) 
					|| "R".equalsIgnoreCase(arg))) {
				return false;
			}
			break;
		case 2:
			String[] args = {tokenizer.nextToken(), tokenizer.nextToken()};
			
			break;
		}

		return true;
	}
}
