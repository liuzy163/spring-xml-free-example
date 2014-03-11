package ca.zl.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import ca.zl.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class DataBeanTest {
	@Autowired
	private LocalValidatorFactoryBean localValidatorFactory;

	@Test
	public void testCrossPropertyIntegrity() {
		ProblemDataBean data = new ProblemDataBean("1;2;3", 4, "a");

		Set<ConstraintViolation<ProblemDataBean>> constraintViolations = localValidatorFactory
				.validate(data);
		assertEquals(1, constraintViolations.size());
		assertEquals("The value of index must be less than the list size",
				constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testIndexField() {
		ProblemDataBean data = new ProblemDataBean("1;2;3", 0, "a");

		Set<ConstraintViolation<ProblemDataBean>> constraintViolations = localValidatorFactory
				.validate(data);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testComplexityField() {
		ProblemDataBean data = new ProblemDataBean("1;2;3", 2, null);

		Set<ConstraintViolation<ProblemDataBean>> constraintViolations = localValidatorFactory
				.validate(data);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidData() {
		ProblemDataBean data = new ProblemDataBean("1;2;3", 2, "a");
		Set<ConstraintViolation<ProblemDataBean>> constraintViolations = localValidatorFactory
				.validate(data);
		assertEquals(0, constraintViolations.size());
	}

}
