package ca.zl.service.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ca.zl.domain.algorithm.Algorithm;
import ca.zl.exception.UnsupportedComplexityException;
import ca.zl.logging.SimpleMethodLogging;

@Component
public class AlgorithmFactoryImpl implements AlgorithmFactory {

	public static final String LEASTMEMORY = "leastmemory";
	public static final String N = "n";
	public static final String NLOGN = "nlogn";

	@Autowired
	@Qualifier(NLOGN)
	private Algorithm nLogN;

	@Autowired
	@Qualifier(N)
	private Algorithm linear;

	@Autowired
	@Qualifier(LEASTMEMORY)
	private Algorithm leastMemory;

	@Override
	@SimpleMethodLogging()
	public Algorithm createAlgorithm(String complexity)
			throws UnsupportedComplexityException {
		switch (complexity) {
		case NLOGN:
			return nLogN;
		case N:
			return linear;
		case LEASTMEMORY:
			return leastMemory;
		default:
			throw new UnsupportedComplexityException(complexity
					+ " is not supported. Please select another complexity");
		}
	}

}
