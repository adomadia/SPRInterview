package org.horse.track.support;

import org.horse.track.command.ICommand;

public class CommandProcessor {
	
	private final static CommandProcessor INSTANCE = new CommandProcessor();

	public static CommandProcessor getInstance() {
		return INSTANCE;
	}

	public void process(ICommand cmd) {
		cmd.execute();
	}

	

}
