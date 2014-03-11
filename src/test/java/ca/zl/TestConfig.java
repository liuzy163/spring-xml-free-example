package ca.zl;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class TestConfig {

	@Bean
	public LocalValidatorFactoryBean validator() {
		final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		return bean;
	}
}
