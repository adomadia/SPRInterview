package org.horse.track.util;

public final class StringUtils {

	public static boolean isNaturalNumber(String str) {
		return str.matches("\\d+");
	}
	
	/*public static boolean isAlpha(String str) {
		return str.matches("[a-zA-Z]+");
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}*/
}
