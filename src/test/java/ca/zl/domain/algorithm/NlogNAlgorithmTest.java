package ca.zl.domain.algorithm;

import org.junit.Assert;
import org.junit.Test;

import ca.zl.domain.DataBean;

public class NlogNAlgorithmTest {
	@Test
	public void testNlogNAlgorithm() {
		NlogNAlgorithm nlogNAlgorithm = new NlogNAlgorithm();
		DataBean result = nlogNAlgorithm.solve(new int[] { 1, 4, 5, 2, 5 }, 3);
		Assert.assertEquals("1;2;4;5", result.getNumbers());
		Assert.assertSame(4, result.getOrder());
	}
}
