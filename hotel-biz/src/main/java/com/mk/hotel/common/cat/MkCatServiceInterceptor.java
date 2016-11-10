package com.mk.hotel.common.cat;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MkCatServiceInterceptor {

	@Pointcut("execution(* com.mk.hotel..service..*.*(..))")
	public void servicePointcut() {
	}

	@Around("servicePointcut()")
	public Object around(ProceedingJoinPoint proceeding) throws Throwable {

		String className = proceeding.getTarget().getClass().getSimpleName();
		String methodName = proceeding.getSignature().getName();

		Transaction t = Cat.newTransaction("hotelService", className + "." + methodName);

		try {
			Object resultObj = proceeding.proceed();
			t.setStatus(Transaction.SUCCESS);
			return resultObj;
		} catch (Exception ex) {
			t.setStatus(ex);
			throw ex;
		} finally {
			t.complete();
		}

	}
}
