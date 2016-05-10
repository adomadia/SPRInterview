package org.horse.track.io.impl;

import org.horse.track.io.IWritable;

public class ConsoleWriter implements IWritable {

	/**
	 * Method accepts message and write it to console.
	 * @param message
	 */
	@Override
	public void write(String message) {
		System.out.printf(message);
	}

}
