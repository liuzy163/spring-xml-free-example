package ca.zl.domain.algorithm;

import org.junit.Assert;
import org.junit.Test;

import ca.zl.domain.DataBean;

public class LeastMemoryAlgorithmTest {
	@Test
	public void testLeastMemoryAlgorithm() {
		LeastMemoryAlgorithm leastMemoryAlgorithm = new LeastMemoryAlgorithm();
		DataBean result = leastMemoryAlgorithm.solve(
				new int[] { 1, 4, 5, 2, 5 }, 3);
		Assert.assertEquals("1;2;4;5", result.getNumbers());
		Assert.assertSame(4, result.getOrder());
	}
}
