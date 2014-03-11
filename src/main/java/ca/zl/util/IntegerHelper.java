package ca.zl.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.logging.LogLevel;

import ca.zl.logging.SimpleMethodLogging;

import com.google.common.primitives.Ints;

public class IntegerHelper {
	@SimpleMethodLogging(level = LogLevel.INFO)
	public static int[] removeDuplicates(int[] sortedNumbers) {
		if (sortedNumbers.length < 1) {
			return new int[0];
		}
		List<Integer> resultList = new ArrayList<Integer>();
		for (int number : sortedNumbers) {
			Integer previous = resultList.isEmpty() ? null : resultList
					.get(resultList.size() - 1);
			if (previous == null || previous.intValue() != number) {
				resultList.add(number);
			}
		}
		return Ints.toArray(resultList);
	}
}
