package ca.zl.service;

import ca.zl.domain.DataBean;
import ca.zl.domain.ProblemDataBean;
import ca.zl.exception.UnsupportedComplexityException;

public interface ProblemSolvingService {
	DataBean solve(ProblemDataBean problem)
			throws UnsupportedComplexityException;
}
