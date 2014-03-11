package ca.zl.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import ca.zl.domain.ProblemDataBean;
import ca.zl.logging.SimpleMethodLogging;
import ca.zl.util.DataBeanHelper;

@Component
public class DataBeanIntegrityValidator implements
		ConstraintValidator<CheckIntegrity, ProblemDataBean> {

	@Override
	public void initialize(CheckIntegrity constraintAnnotation) {
	}

	@Override
	@SimpleMethodLogging(level = LogLevel.INFO)
	public boolean isValid(ProblemDataBean value,
			ConstraintValidatorContext context) {
		int order = value.getOrder();
		boolean isValid = order < DataBeanHelper.fromStringToNumbers(value
				.getNumbers()).length;
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					"The value of index must be less than the list size")
					.addConstraintViolation();
		}
		return isValid;
	}
}
