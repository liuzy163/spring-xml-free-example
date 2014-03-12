package ca.zl.domain.algorithm;

import java.util.Arrays;

import ca.zl.domain.DataBean;
import ca.zl.util.DataBeanHelper;
import ca.zl.util.NumbersListHelper;

public class NlogNAlgorithm implements Algorithm {
	@Override
	public DataBean solve(int[] numbers, int index) {
		dualPivotQuicksort(numbers);
		int[] result = NumbersListHelper.removeDuplicates(numbers);
		return new DataBean(DataBeanHelper.fromNumbersToString(result),
				result[index - 1]);
	}

	/**
	 * See {@link Arrays#sort}
	 */
	private void dualPivotQuicksort(int[] numbers) {
		Arrays.sort(numbers);
	}
}
