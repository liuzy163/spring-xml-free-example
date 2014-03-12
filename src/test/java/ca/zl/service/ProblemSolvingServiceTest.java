package ca.zl.service;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import ca.zl.domain.DataBean;
import ca.zl.domain.ProblemDataBean;
import ca.zl.domain.algorithm.Algorithm;
import ca.zl.exception.UnsupportedComplexityException;
import ca.zl.service.algorithm.AlgorithmFactory;
import ca.zl.util.DataBeanHelper;

public class ProblemSolvingServiceTest {
	@Test
	public void testSolve() throws UnsupportedComplexityException {

		String numbers = "1;2;3;2";
		int order = 2;
		String complexity = "foo";
		ProblemDataBean problem = new ProblemDataBean(numbers, order,
				complexity);

		Algorithm algorithm = EasyMock.createMock(Algorithm.class);
		DataBean result = new DataBean("1;2;3", order);
		EasyMock.expect(
				algorithm.solve(EasyMock.aryEq(DataBeanHelper
						.fromStringToNumbers(numbers)), EasyMock.anyInt()))
				.andReturn(result);
		EasyMock.replay(algorithm);

		AlgorithmFactory algorithmFactory = EasyMock
				.createMock(AlgorithmFactory.class);
		EasyMock.expect(algorithmFactory.createAlgorithm(complexity))
				.andReturn(algorithm);
		EasyMock.replay(algorithmFactory);

		ProblemSolvingService problemSolvingService = new ProblemSolvingServiceImpl(
				algorithmFactory);
		Assert.assertEquals("Should be equal", result,
				problemSolvingService.solve(problem));
	}
}
