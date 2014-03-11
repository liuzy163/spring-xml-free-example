package ca.zl.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.logging.LogLevel;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.CONSTRUCTOR })
public @interface SimpleMethodLogging {
	boolean entry() default true;

	boolean exit() default true;

	LogLevel level() default LogLevel.DEBUG;

	String beforeMessage() default "";

	String afterMessage() default "";
}
