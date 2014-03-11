package ca.zl.domain;

import javax.validation.constraints.Min;

public class DataBean {
	// No constrain added in this demo because client side validation already
	// used
	private String numbers;
	@Min(1)
	private Integer order;

	public DataBean() {
	}

	public DataBean(String numbers, int order) {
		this.numbers = numbers;
		this.order = order;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}