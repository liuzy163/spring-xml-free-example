package ca.zl.service.algorithm;

import ca.zl.domain.algorithm.Algorithm;
import ca.zl.exception.UnsupportedComplexityException;

public interface AlgorithmFactory {
	Algorithm createAlgorithm(String complexity)
			throws UnsupportedComplexityException;
}
