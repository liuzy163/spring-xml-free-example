package ca.zl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import ca.zl.domain.algorithm.Algorithm;
import ca.zl.domain.algorithm.LeastMemoryAlgorithm;
import ca.zl.domain.algorithm.LinearAlgorithm;
import ca.zl.domain.algorithm.NlogNAlgorithm;
import ca.zl.logging.SimpleMethodLogger;

@Configuration
@EnableAspectJAutoProxy
public class PuzzleSolvingConfig {

	@Bean
	@Qualifier("nlogn")
	Algorithm getNLogNAlgorithm() {
		return new NlogNAlgorithm();
	}

	@Bean
	@Qualifier("n")
	Algorithm getLinearAlgorithm() {
		return new LinearAlgorithm();
	}

	@Bean
	@Qualifier("leastmemory")
	Algorithm getLeastMemoryAlgorithm() {
		return new LeastMemoryAlgorithm();
	}

	@Bean
	SimpleMethodLogger logger() {
		return new SimpleMethodLogger();
	}
}