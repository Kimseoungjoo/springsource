package com.company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	// 내부적으로 DelegatingFilterProxy를 스프링에 등록시킴
	
	//filter ~~~ 
	
}
