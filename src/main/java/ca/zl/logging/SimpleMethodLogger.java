package ca.zl.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SimpleMethodLogger {
	@Before(value = "@annotation(simpleMethodLogging)", argNames = "joinPoint,simpleMethodLogging")
	public void basicBeforeAdvice(JoinPoint joinPoint,
			SimpleMethodLogging simpleMethodLogging) throws Throwable {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		Logger logger = Logger.getLogger(clazz);
		Level level = Level.toLevel(simpleMethodLogging.level().toString());
		boolean logLevelEnabled = Logger.getLogger(clazz).isEnabledFor(level);
		if (logLevelEnabled) {
			String message = simpleMethodLogging.beforeMessage();
			if (message.isEmpty()) {
				message = "Entering " + joinPoint.getSignature().getName()
						+ " with args " + joinPoint.getArgs();
			}
			logger.log(level, message);
		}
	}

	@Around("execution(* ca.zl.domain.algorithm.Algorithm.solve(..))")
	public void algorithmAroundAdvice(ProceedingJoinPoint joinPoint)
			throws Throwable {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		Logger logger = Logger.getLogger(clazz);
		logger.log(Level.INFO,
				"About to solve the puzzle of " + joinPoint.getArgs());
		joinPoint.proceed();
		logger.log(Level.INFO, "Problem solved");
	}

	@AfterReturning(value = "@annotation(simpleMethodLogging)", argNames = "joinPoint,simpleMethodLogging, result", returning = "result")
	public void afterReturningMethod(JoinPoint joinPoint,
			SimpleMethodLogging simpleMethodLogging, Object result)
			throws Throwable {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		Logger logger = Logger.getLogger(clazz);
		Level level = Level.toLevel(simpleMethodLogging.level().toString());
		boolean logLevelEnabled = Logger.getLogger(clazz).isEnabledFor(level);
		if (logLevelEnabled) {
			logger.log(level, "Returned " + result);
		}
	}

	@AfterThrowing(value = "@annotation(SimpleMethodLogging)", throwing = "error")
	public void afterThrowing(JoinPoint joinPoint, Throwable error)
			throws Throwable {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		Logger logger = Logger.getLogger(clazz);
		logger.log(Level.ERROR, "Error occurred " + error);
	}

}
