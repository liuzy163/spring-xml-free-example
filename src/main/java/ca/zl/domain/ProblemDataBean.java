package ca.zl.domain;

import javax.validation.constraints.NotNull;

import ca.zl.validators.CheckIntegrity;

@CheckIntegrity
public class ProblemDataBean extends DataBean {
	@NotNull
	private String complexity;

	public ProblemDataBean() {
	}

	public ProblemDataBean(String numbers, int order, String complexity) {
		super(numbers, order);
		this.complexity = complexity;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

}
