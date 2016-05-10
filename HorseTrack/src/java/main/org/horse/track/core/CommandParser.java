package org.horse.track.core;

import org.horse.track.command.ICommandExecutable;
import org.horse.track.factory.CommandFactory;
import org.horse.track.io.IReader;

/**
 * CommandPrompt performs read and write.
 * @author Ashvin Domadia
 *
 */
public class CommandParser{

	private IReader reader;
	private CommandFactory factory;
	
	public CommandParser(IReader reader, CommandFactory factory){
		this.reader = reader;
		this.factory = factory;
	}
	
	/**
	 * Method reads a command from console and return command object.
	 * Method thows IllegalArgumentException if a command is not supported.
	 * 
	 * @return ICommand
	 */
	public final ICommandExecutable readCommand(){

		String cmdSyntax = reader.read();
		ICommandExecutable command =  factory.getCommand(cmdSyntax);
	
		if(command == null){
			//TODO: Create new exception class called NotFoundCommand.
			throw new IllegalArgumentException("Invalid Command: " + cmdSyntax.trim());
		}
		
		return command;
	}
}
