package org.horse.track.support;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.impl.BlankCommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.command.impl.RestockCommand;
import org.horse.track.exception.InvalidCommandException;
import org.horse.track.util.StringUtils;

public class CommandPrompt {

	
	
/*	public final static int MAX_COMMAND_LINE_ARGUMENTS = 2;
	public final static String COMMAND_Q = "Q";
	public final static String COMMAND_R = "R";
	public final static String COMMAND_W = "W";
*/
	private Scanner scanner = new Scanner(System.in);
	
	private final static CommandPrompt INSTANCE = new CommandPrompt(); 
	
	public final ICommand readCommand(){

		String cmdSyntax = scanner.nextLine();
		
		StringTokenizer tokenizer = new StringTokenizer(cmdSyntax);
		
		//return BlankCommand size is 0
		if(tokenizer.countTokens() == 0){
			return new BlankCommand();
		}
		
		String cmd = tokenizer.nextToken();
		
		if(QuitCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			QuitCommand quitCommand = new QuitCommand(cmdSyntax);
			quitCommand.validate();
			return quitCommand;
		}


		if(RestockCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			RestockCommand restockCommand = new RestockCommand(cmd);
			restockCommand.validate();
			return restockCommand;
		}
		
		throw new IllegalArgumentException("Invalid Command: " + cmdSyntax.trim());
	}
	
	public static CommandPrompt getInstance(){
		return INSTANCE;
	}

	/*public String input() throws InvalidCommandException {

		String cmd = scanner.nextLine();

		if (!isCommandValid(cmd)) {
			throw new InvalidCommandException("Invalid Command: " + cmd);
		}
		return cmd.trim();
	}

	// this function verify params is valid command.
	public boolean isCommandValid(String cmd) {

		StringTokenizer tokenizer = new StringTokenizer(cmd);

		if (tokenizer.countTokens() > MAX_COMMAND_LINE_ARGUMENTS) {
			return false;
		}

		switch (tokenizer.countTokens()) {
		case 1:
			String arg = tokenizer.nextToken();
			if (!(COMMAND_Q.equalsIgnoreCase(arg) 
					|| COMMAND_R.equalsIgnoreCase(arg))) {
				return false;
			}
			break;
		case 2:
			String[] args = { tokenizer.nextToken(), tokenizer.nextToken() };
				if(StringUtils.isAlpha(args[0]) 
						&& COMMAND_W.equalsIgnoreCase(args[0]) 
						&& StringUtils.isNaturalNumber(args[1]) 
						&& Integer.parseInt(args[1]) != 0){
					return true;
				}
				if(StringUtils.isNaturalNumber(args[0]) 
						&& Integer.parseInt(args[0]) != 0 
						&& StringUtils.isNumeric(args[1])){
					return true;
				}
				return false;
		}

		return true;
	}
*/
}
