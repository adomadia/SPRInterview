package org.horse.track.io.impl;

import java.util.Scanner;

import org.horse.track.io.IReader;

public class ConsoleReader implements IReader{
	
	private final Scanner scanner = new Scanner(System.in);

	/***
	 * Read a line from command prompt.
	 */
	@Override
	public String read() {
		return scanner.nextLine();
	}

}
