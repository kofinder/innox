package com.finder.innox.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.finder.innox.utils.JsonUtil;


@Component
@Aspect
public class RestApiLogging {

	protected Logger signatureLogger;
	protected Logger logAroundLogger;
	protected Logger returnLogger;
	protected Logger errorLogger;

	public RestApiLogging() {
		this(LoggerFactory.getLogger("rest.web.message.tracing.signature"),
				LoggerFactory.getLogger("rest.web.message.tracing.logAround"),
				LoggerFactory.getLogger("rest.web.message.tracing.return"),
				LoggerFactory.getLogger("rest.web.message.tracing.error"));
	}

	public RestApiLogging(Logger signatureLogger, Logger logAroundLogger, Logger returnLogger, Logger errorLogger) {
		this.signatureLogger = signatureLogger;
		this.logAroundLogger = logAroundLogger;
		this.returnLogger = returnLogger;
		this.errorLogger = errorLogger;
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)"
			+ " || within(@org.springframework.stereotype.Component *)")
	protected void allComponent() {
	}

	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}

	@Before("allComponent() && allMethod()")
	public void onExecute(JoinPoint jp) {
		signatureLogger.debug("" + jp.getSignature());
	}

	@AfterReturning(pointcut = "allComponent() && allMethod()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		returnLogger.debug("{}", JsonUtil.toJSON(result));
	}

	@AfterThrowing(pointcut = "allComponent() && allMethod()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		errorLogger.error(new StringBuilder().append("Thrown In").append(joinPoint.getSignature().getName())
				.append("()").append("Cause").append(exception.getCause()).toString());
	}

	@Around("allComponent() && allMethod()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		try {
			logAroundLogger.debug(new StringBuilder().append(joinPoint.getSignature().getDeclaringTypeName())
					.append(".").append(joinPoint.getSignature().getName()).append("()").append("execution time :")
					.append(System.currentTimeMillis() - start).append("ms").toString());

			return joinPoint.proceed();
		} catch (IllegalArgumentException e) {
			errorLogger
					.error(new StringBuilder().append("Illegal argument").append(Arrays.toString(joinPoint.getArgs()))
							.append("in").append("joinPoint.getSignature().getName()").append("()").toString());
			throw e;
		}
	}
}
