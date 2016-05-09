package org.horse.track.core;

import org.horse.track.command.ICommand;

/**
 * Process commands by invoking execute method.
 * 
 * @author Ashvin Domadia
 *
 */
public class CommandProcessor {
	
	private final static CommandProcessor INSTANCE = new CommandProcessor();
	
	private CommandProcessor(){
		
	}

	public static CommandProcessor getInstance() {
		return INSTANCE;
	}

	/**
	 * execute command.
	 * 
	 * @param cmd
	 */
	public void process(ICommand cmd) {
		cmd.execute();
	}
}
