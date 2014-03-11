package ca.zl.util;

import org.springframework.boot.logging.LogLevel;

import ca.zl.logging.SimpleMethodLogging;

/**
 * TODO This class is not needed if JSON is properly implemented
 */
public class DataBeanHelper {
	private static final String SEMICOLON = ";";

	@SimpleMethodLogging
	public static int[] fromStringToNumbers(String numbers) {
		String[] tokens = numbers.split(SEMICOLON);
		int[] result = new int[tokens.length];
		for (int index = 0; index < tokens.length; index++) {
			result[index] = (Integer.valueOf(tokens[index]));
		}
		return result;
	}

	@SimpleMethodLogging(level = LogLevel.INFO)
	public static String fromNumbersToString(int[] numbers) {
		StringBuilder builder = new StringBuilder();
		for (int number : numbers) {
			builder.append(number).append(SEMICOLON);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
}
