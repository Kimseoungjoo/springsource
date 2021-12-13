package com.company.app;

public class MessageBeanEn implements MessageBean {
	public MessageBeanEn() {
		System.out.println("messageEN 객체 생성");
	}
	@Override
	public void sayHello(String name) {
		System.out.println("Hello!! "+name);
	}

}
