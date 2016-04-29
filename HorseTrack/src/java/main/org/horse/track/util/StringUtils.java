package org.horse.track.util;

/**
 * Utility Implementation
 * @author Ashvin Domadia
 *
 */
public final class StringUtils {

	/**
	 * Verify if string has natural number.
	 * @param str
	 * @return
	 */
	public static boolean isNaturalNumber(String str) {
		return str.matches("\\d+");
	}
}
