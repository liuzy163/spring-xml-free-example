package ca.zl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class PuzzleApplication {
	public static void main(String[] args) {
		SpringApplication.run(PuzzleApplication.class, args);
	}
}
