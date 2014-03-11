package ca.zl.domain.algorithm;

import org.apache.log4j.Logger;

import ca.zl.domain.DataBean;
import ca.zl.util.DataBeanHelper;
import ca.zl.util.IntegerHelper;

public class LeastMemoryAlgorithm implements Algorithm {
	static final Logger logger = Logger.getLogger(LeastMemoryAlgorithm.class);

	@Override
	public DataBean solve(int[] numbers, int index) {
		sort(numbers);
		int[] result = IntegerHelper.removeDuplicates(numbers);
		return new DataBean(DataBeanHelper.fromNumbersToString(result),
				result[index - 1]);
	}

	private void sort(int[] numbers) {
		HeapSort.sort(numbers);
	}

	/**
	 * See http://en.wikipedia.org/wiki/Heapsort The code below was copied from
	 * http://www.code2learn.com/2011/09/heapsort-array-based-implementation
	 * -in.html
	 */
	private static class HeapSort {
		private static int[] a;
		private static int n;
		private static int left;
		private static int right;
		private static int largest;

		public static void buildheap(int[] a) {
			n = a.length - 1;
			for (int i = n / 2; i >= 0; i--) {
				maxheap(a, i);
			}
		}

		public static void maxheap(int[] a, int i) {
			left = 2 * i;
			right = 2 * i + 1;
			if (left <= n && a[left] > a[i]) {
				largest = left;
			} else {
				largest = i;
			}

			if (right <= n && a[right] > a[largest]) {
				largest = right;
			}
			if (largest != i) {
				exchange(i, largest);
				maxheap(a, largest);
			}
		}

		public static void exchange(int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}

		public static void sort(int[] a0) {
			a = a0;
			buildheap(a);

			for (int i = n; i > 0; i--) {
				exchange(0, i);
				n = n - 1;
				maxheap(a, 0);
			}
		}
	}
}
