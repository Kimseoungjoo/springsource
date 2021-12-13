package com.company.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.app.MessageBean;
import com.company.app.MessageBeanEn;
import com.company.tvapp.AppleSpeaker;
import com.company.tvapp.LgTv;
import com.company.tvapp.TV;

// 환경설정이라는걸 알려주기 위해 annotaction
@Configuration // 클래스 파일이 환경설정 파일 
public class Config {
	// applicationConfig.xml 파일이랑 같은 환경설정
	//<bean id="en" class="com.company.app.MessageBeanEn"/>
	@Bean // 객체를 생성해서 컨테이너가 관리해줘 
	public MessageBean getMessageBean() {
		return new MessageBeanEn();
	}
	
	@Bean
	public TV getTv() {
		return new LgTv(new AppleSpeaker());
	}
}
