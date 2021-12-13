package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller // 객체생성해주는 annotation
public class SampleController2 {
	
	@RequestMapping("/member/basic")
	public void basic() {
		log.info("/member/basic 요청.......");
		// view 리졸버 /sample/basic
	}
	

	// GET방식에만 응답하는 Controller
	//@RequestMapping(value="/member/login", method=RequestMethod.GET) // /sample/login
	@GetMapping("/member/login")
	public String login() {
		log.info("/member/login 요청.......");
		// view 리졸버 login
		return "login";
	}
	//POST 방식응답
	//@RequestMapping(value="/member/login", method=RequestMethod.POST)
//	@PostMapping("/member/login")
//	public String loginPost() {
//		log.info("/member/login Post 요청");
//		return "/sample/basic";
//	}
	
//	@RequestMapping(value="/admin/info",method=RequestMethod.GET)
	@GetMapping("/admin/info")
	public void method1() {
		log.info("/admin/info 요청...");
		// q뷰리졸버 : sample/info
	}
	
	//@RequestMapping(value="/admin", method =RequestMethod.GET)
	@GetMapping("/admin")
	public String method2() {
		log.info("/admin 요청...");
		
		//return "/sample/admin"; //ViewResolver : sample/admin
		return "admin"; // admin => /WEB-INF/views/admin.jsp
	}
	
}
