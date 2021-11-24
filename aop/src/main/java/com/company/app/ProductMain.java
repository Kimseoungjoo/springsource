package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		Product product = (Product) ctx.getBean("product");
		
		product.setCompany("LEGO");
		product.setPname("LEGO 블럭");
		product.setPrice("100000");
		// getInfo 전에 LogAdvice안에 메소드를 먼저 실행시키는 작업을 한다. (spring을 이용해서)
		product.getInfo();
	}
}
