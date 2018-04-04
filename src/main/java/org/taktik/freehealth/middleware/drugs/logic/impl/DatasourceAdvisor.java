package org.taktik.freehealth.middleware.drugs.logic.impl;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class DatasourceAdvisor extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	private final StaticMethodMatcherPointcut pointcut = new
			StaticMethodMatcherPointcut() {
				@Override
				public boolean matches(Method method, Class<?> targetClass) {
					return targetClass.isAnnotationPresent(HibernateDatasource.class) || method.isAnnotationPresent(HibernateDatasource.class);
				}
			};

	private DatasourceInterceptor interceptor;

	public void setInterceptor(DatasourceInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	@Override
	public Advice getAdvice() {
		return this.interceptor;
	}
}