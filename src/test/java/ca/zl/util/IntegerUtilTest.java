package ca.zl.util;

import org.junit.Assert;
import org.junit.Test;

public class IntegerUtilTest {
	@Test
	public void basicTest() {
		int[] result = IntegerHelper
				.removeDuplicates(new int[] { 1, 2, 3, 3, 4 });
		Assert.assertArrayEquals(new int[] { 1, 2, 3, 4 }, result);
	}

	@Test
	public void emptyInputTest() {
		int[] result = IntegerHelper.removeDuplicates(new int[0]);
		Assert.assertArrayEquals(new int[0], result);
	}
}
