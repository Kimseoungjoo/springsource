package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		
		System.out.println("=============Spring 컨테이너 구동 전 =================");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationConfig.xml");
		// classpath ==> src/main/resource
		
		System.out.println("======= Spring 컨테이너 구동 =================");
		
		//Spring 컨테이너로부터 필요한 객체를 요청하는 구문
		
		MessageBean msg = (MessageBean)ctx.getBean("ko");
		msg.sayHello("홍길동");
	}
}
