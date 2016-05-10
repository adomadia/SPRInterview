package org.horse.track.core;

import org.horse.track.command.ICommandExecutable;

/**
 * Process commands by invoking execute method.
 * 
 * @author Ashvin Domadia
 *
 */
public class CommandProcessor {
	
	/**
	 * execute command.
	 * 
	 * @param cmd
	 */
	public boolean process(ICommandExecutable cmd) {
		return cmd.execute();
	}
}
