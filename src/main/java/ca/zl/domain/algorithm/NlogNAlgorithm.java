package ca.zl.domain.algorithm;

import java.util.Arrays;

import org.apache.log4j.Logger;

import ca.zl.domain.DataBean;
import ca.zl.util.DataBeanHelper;
import ca.zl.util.IntegerHelper;

public class NlogNAlgorithm implements Algorithm {
	static final Logger logger = Logger.getLogger(NlogNAlgorithm.class);

	@Override
	public DataBean solve(int[] numbers, int index) {
		dualPivotQuicksort(numbers);
		int[] result = IntegerHelper.removeDuplicates(numbers);
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
