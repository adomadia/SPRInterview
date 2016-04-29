package org.horse.track.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class StringUtilsTest {

	public final static String ALPHA_STRING = "alpha";
	public final static String NOT_ALPHA_STRING = "alpha";
	public final static String NATURAL_NUMBER_STRING = "1255";
	public final static String NEGATIVE_NUMBER_STRING = "-1255";
	public final static String NUMERIC_STRING_ONE = "10.25";
	public final static String NUMERIC_STRING_TWO = "-10.25";
	public final static String NUMERIC_STRING_THREE = "10";
	public final static String NUMERIC_STRING_FOUR = "-10";
	
	/*@Test
	public void testStringIsAlpha() {
		assertThat("String should have all letters",StringUtils.isAlpha(ALPHA_STRING), is(true));
	}
	
	@Test
	public void testStringIsNotAlpha() {
		assertThat("String should not have all letters",StringUtils.isAlpha(NATURAL_NUMBER_STRING), is(false));
	}*/
	
	@Test
	public void testStringIsNaturalNumber() {
		assertThat("String should be a natural number", StringUtils.isNaturalNumber(NATURAL_NUMBER_STRING), is(true));
	}
	
	public void testStringIsNotNaturalNumber(){
		assertThat("String should not be a natural number", StringUtils.isNaturalNumber(NEGATIVE_NUMBER_STRING), is(false));
		assertThat("String should not be a natural number", StringUtils.isNaturalNumber(NUMERIC_STRING_ONE), is(false));
	}
	
	/*@Test
	public void testStringIsNumeric() {
		assertThat("String should have numeric value", StringUtils.isNumeric(NUMERIC_STRING_ONE), is(true));
		assertThat("String should have numeric value", StringUtils.isNumeric(NUMERIC_STRING_TWO), is(true));
		assertThat("String should have numeric value", StringUtils.isNumeric(NUMERIC_STRING_THREE), is(true));
		assertThat("String should have numeric value", StringUtils.isNumeric(NUMERIC_STRING_FOUR), is(true));
	}
	
	@Test
	public void testStringIsNotNumeric() {
		assertThat("String should not be a numeric", StringUtils.isNumeric(NOT_ALPHA_STRING), is(false));
	}
*/
}
