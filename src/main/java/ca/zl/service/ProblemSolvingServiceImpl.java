package ca.zl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import ca.zl.domain.DataBean;
import ca.zl.domain.ProblemDataBean;
import ca.zl.domain.algorithm.Algorithm;
import ca.zl.exception.UnsupportedComplexityException;
import ca.zl.logging.SimpleMethodLogging;
import ca.zl.service.algorithm.AlgorithmFactory;
import ca.zl.util.DataBeanHelper;

@Service
public class ProblemSolvingServiceImpl implements ProblemSolvingService {

	private final AlgorithmFactory algorithmFactory;

	@Autowired
	public ProblemSolvingServiceImpl(AlgorithmFactory algorithmFactory) {
		this.algorithmFactory = algorithmFactory;
	}

	@Override
	@SimpleMethodLogging(level = LogLevel.INFO)
	public DataBean solve(ProblemDataBean problem)
			throws UnsupportedComplexityException {
		Algorithm algorithm = algorithmFactory.createAlgorithm(problem
				.getComplexity());
		return algorithm.solve(
				DataBeanHelper.fromStringToNumbers(problem.getNumbers()),
				problem.getOrder());
	}
}
