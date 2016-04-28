package org.horse.track.main;


import org.horse.track.exception.InvalidCommandException;
import org.horse.track.support.CommandPrompt;

public class HorseTrackMain {
	public static void main(String[] args) {
	
		CommandPrompt cmd = new CommandPrompt();
		String command = "";
		
		do{
			try {
				command = cmd.input();
			} catch (InvalidCommandException ex) {
				System.out.println(ex.getMessage());
			}
		}while(!"Q".equalsIgnoreCase(command));
	}

}
