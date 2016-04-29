package org.horse.track.singleton;

import org.horse.track.command.ICommand;

public class CommandProcessor {
	
	private final static CommandProcessor INSTANCE = new CommandProcessor();
	
	private CommandProcessor(){
		
	}

	public static CommandProcessor getInstance() {
		return INSTANCE;
	}

	public void process(ICommand cmd) {
		cmd.execute();
	}

	

}
