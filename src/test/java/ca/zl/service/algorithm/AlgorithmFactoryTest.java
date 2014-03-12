package ca.zl.service.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import ca.zl.PuzzleSolvingConfig;
import ca.zl.TestConfig;
import ca.zl.domain.algorithm.Algorithm;
import ca.zl.exception.UnsupportedComplexityException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class, PuzzleSolvingConfig.class })
public class AlgorithmFactoryTest {
	@Autowired
	@Qualifier(AlgorithmFactoryImpl.NLOGN)
	private Algorithm nLogN;

	@Autowired
	@Qualifier(AlgorithmFactoryImpl.N)
	private Algorithm linear;

	@Autowired
	@Qualifier(AlgorithmFactoryImpl.LEASTMEMORY)
	private Algorithm leastMemory;

	private AlgorithmFactory factory;

	@Before
	public void setup() {
		factory = new AlgorithmFactoryImpl();
		ReflectionTestUtils.setField(factory, "nLogN", nLogN);
		ReflectionTestUtils.setField(factory, "linear", linear);
		ReflectionTestUtils.setField(factory, "leastMemory", leastMemory);
	}

	@Test
	public void testAlgorithmFactory() throws UnsupportedComplexityException {
		Assert.assertSame(nLogN,
				factory.createAlgorithm(AlgorithmFactoryImpl.NLOGN));
		Assert.assertSame(linear,
				factory.createAlgorithm(AlgorithmFactoryImpl.N));
		Assert.assertSame(leastMemory,
				factory.createAlgorithm(AlgorithmFactoryImpl.LEASTMEMORY));
	}
}
