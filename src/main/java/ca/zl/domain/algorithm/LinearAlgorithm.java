package ca.zl.domain.algorithm;

import ca.zl.domain.DataBean;
import ca.zl.util.DataBeanHelper;
import ca.zl.util.NumbersListHelper;

public class LinearAlgorithm implements Algorithm {

	@Override
	public DataBean solve(int[] numbers, int index) {
		bucketSort(numbers);
		int[] result = NumbersListHelper.removeDuplicates(numbers);
		return new DataBean(DataBeanHelper.fromNumbersToString(result),
				result[index - 1]);
	}

	/**
	 * Bucket sort uses near linear complexity See
	 * http://en.wikipedia.org/wiki/Bucket_sort
	 * 
	 * Code copied from
	 * http://www.cs.hmc.edu/~keller/courses/cs60/s98/examples/Sort
	 * /bucketsort.java
	 * */

	private void bucketSort(int[] numbers) {

		int length = numbers.length;
		int min = numbers[0];
		int max = min;
		for (int i = 1; i < length; i++) {
			// Find the minimum and maximum
			if (numbers[i] > max)
				max = numbers[i];
			else if (numbers[i] < min)
				min = numbers[i];
		}

		int bucket[] = new int[max - min + 1]; // Create buckets

		for (int i = 0; i < length; i++) {
			// "Fill" buckets
			bucket[numbers[i] - min]++;
		}

		int i = 0;
		for (int b = 0; b < bucket.length; b++) {
			for (int j = 0; j < bucket[b]; j++) {
				numbers[i++] = b + min;
			}
		}
	}
}
