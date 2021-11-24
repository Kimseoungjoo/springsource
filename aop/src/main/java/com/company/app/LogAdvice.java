package com.company.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // aop 어노테이션 설정방식
@Component("log")
public class LogAdvice {
	
	@Before(value="execution(* com.company.app.Product.getInfo())")
	public void beforeLog() {
		System.out.println("[공통로그] 비지니스 로직 수행 전 호출");
	}
	
	@After(value="execution(* com.company.app.Product.getInfo())")
	public void afterLog() {
		System.out.println("[공통로그] 비지니스 로직 수행 후 호출 - 예외와 상관없이 호출");
	}
	
	@AfterThrowing(value="execution(* com.company.app.Product.getInfo())")
	public void afterThrowLog() {
		System.out.println("[공통로그] 비지니스 로직 수행 후 호출 - 예외 발생 시 호출");
	}
	
	@AfterReturning(value="execution(* com.company.app.Product.getInfo())")
	public void afterReturnLog() {
		System.out.println("[공통로그] 비지니스 로직 수행 후 호출 - 정상 수행 시 호출");
	}
	
	@Around(value="execution(* com.company.app.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비지니스 로직 수행 전 호출");
		try {
			// 실제 수행할 메소드 호출!!!ㅈㄴ 중요하다 이자슥아
			pjp.proceed(); // ****** getInfo 메소드를 실행시키는 구문(무조건 있어야한다.)
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("[공통로그] 비지니스 로직 수행 후 호출");
	}
}
