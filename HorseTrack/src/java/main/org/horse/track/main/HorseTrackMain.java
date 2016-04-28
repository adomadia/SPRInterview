package org.horse.track.main;


import org.horse.track.command.ICommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.exception.InvalidCommandException;
import org.horse.track.support.CommandProcessor;
import org.horse.track.support.CommandPrompt;

public class HorseTrackMain {
/*	public static void main(String[] args) {
	
		CommandPrompt cmd = new CommandPrompt();
		String command = "";
		
		do{
			try {
				command = cmd.input();
				System.out.println("Valid command: " + command);
			} catch (InvalidCommandException ex) {
				System.out.println(ex.getMessage());
			}
		}while(!"Q".equalsIgnoreCase(command));
	}*/
	
	public static void main(String[] args) {

		ICommand cmd = null;

		CommandPrompt shell = CommandPrompt.getInstance();
		CommandProcessor processor = CommandProcessor.getInstance();
		
		do{
			try {
				cmd = shell.readCommand();
				processor.process(cmd);
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}while(!(cmd instanceof QuitCommand));
	}

}
