package com.github.tyru.jaxrshelloapp.hello.interceptor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Interceptor
@LoggingAround
@Dependent
public class LoggingAroundInterceptor {
	@Inject
	private Logger logger;

	@AroundInvoke
    public Object log(InvocationContext context) throws Exception {
		StringBuilder methodName = new StringBuilder();
		methodName.append(context.getMethod().getDeclaringClass().getName());
		methodName.append("#");
		methodName.append(context.getMethod().getName());
		methodName.append("()");
		try {
			logger.info("Entering " + methodName.toString());
			return context.proceed();
		} finally {
			logger.info("Leaving " + methodName.toString());
		}
    }
}