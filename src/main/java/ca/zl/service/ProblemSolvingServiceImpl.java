package ca.zl.service;

import org.apache.log4j.Logger;
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
	static final Logger logger = Logger
			.getLogger(ProblemSolvingServiceImpl.class);

	private final AlgorithmFactory algorithmFactory;

	@Autowired
	public ProblemSolvingServiceImpl(AlgorithmFactory algorithmFactory) {
		this.algorithmFactory = algorithmFactory;
	}

	@Override
	@SimpleMethodLogging(level = LogLevel.INFO)
	public DataBean solve(ProblemDataBean problem)
			throws UnsupportedComplexityException {
		String complexity = problem.getComplexity();
		String numbers = problem.getNumbers();
		Integer order = problem.getOrder();

		Algorithm algorithm = algorithmFactory.createAlgorithm(complexity);
		return algorithm.solve(DataBeanHelper.fromStringToNumbers(numbers),
				order);
	}
}
