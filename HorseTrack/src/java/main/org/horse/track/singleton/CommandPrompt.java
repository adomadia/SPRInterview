package org.horse.track.singleton;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IMessage;
import org.horse.track.command.impl.BlankCommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.command.impl.RestockCommand;
import org.horse.track.command.impl.WagerCommand;
import org.horse.track.command.impl.WinnerCommand;
import org.horse.track.exception.InvalidCommandException;
import org.horse.track.util.StringUtils;

public class CommandPrompt implements IMessage{

	private final Scanner scanner = new Scanner(System.in);
	
	private final static CommandPrompt INSTANCE = new CommandPrompt(); 

	private CommandPrompt(){
		
	}
	
	/**
	 * Singleton implementation
	 * @return CommandPrompt
	 */
	public static CommandPrompt getInstance(){
		return INSTANCE;
	}

	/**
	 * readCommand method reads a command from console and return command object.
	 * this method thows IllegalArgumentException if a command is not supported.
	 * 
	 * @return ICommand
	 */
	public final ICommand readCommand(){

		String cmdSyntax = scanner.nextLine();
		
		StringTokenizer tokenizer = new StringTokenizer(cmdSyntax);
		
		//return BlankCommand object if its empty line.
		if(tokenizer.countTokens() == 0){
			return new BlankCommand(cmdSyntax);
		}
		
		String cmd = tokenizer.nextToken();
		
		// return RestockCommand boject if its Q command
		if(QuitCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			QuitCommand quitCommand = new QuitCommand(cmdSyntax);
			quitCommand.validate();
			return quitCommand;
		}

		// return RestockCommand object if its R command.
		if(RestockCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			RestockCommand restockCommand = new RestockCommand(cmdSyntax);
			restockCommand.validate();
			return restockCommand;
		}
		
		// return RestockCommand object if its W command.
		if(WinnerCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			WinnerCommand winnerCommand = new WinnerCommand(cmdSyntax);
			winnerCommand.validate();
			return winnerCommand;
		}
		
		
		// return WagerCommand object if its Wager command.
		if(StringUtils.isNaturalNumber(cmd) && Integer.parseInt(cmd) > 0){
			WagerCommand wagerCommand = new WagerCommand(cmdSyntax, getInstance());
			wagerCommand.validate();
			return wagerCommand;
		}
		
		//Throw exception if system doesn't support command
		throw new IllegalArgumentException("Invalid Command: " + cmdSyntax.trim());
	}
	
	/**
	 * write method accepts message parameter and write message to console.
	 * @param message
	 */
	public void write(String message) {
		System.out.printf(message);
	}


}
