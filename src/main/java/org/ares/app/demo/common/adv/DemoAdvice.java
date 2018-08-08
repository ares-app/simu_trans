package org.ares.app.demo.common.adv;

import static org.ares.app.demo.common.cfg.Params.ERR_MSG_USER_NOT_AUTH;

import javax.annotation.Resource;

import org.ares.app.demo.common.danger.DemoDanger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAdvice {
	
	@Around("execution (* org.ares.app.demo..*Dao*.*(..))")
	public Object serviceExceptionHandle(ProceedingJoinPoint pjp) throws RuntimeException{
		Object r=null;
		try {
			if(dg.danger())
				return null;
			r=pjp.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(ERR_MSG_USER_NOT_AUTH);
		}
		return r;
	}
	
	@Resource DemoDanger dg;
}
