package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller // 객체생성해주는 annotation
@RequestMapping("/sample/*")
public class SampleController {
	
	@RequestMapping("/basic")
	public void basic() {
		log.info("/basic 요청.......");
		// view 리졸버 /sample/basic
	}
	
	// 기본 GET / POST 둘다 응답
//	@RequestMapping("/login") // /sample/login
//	public String login() {
//		log.info("/login 요청.......");
//		// view 리졸버 login
//		return "login";
//	}
	// GET방식에만 응답하는 Controller
	@RequestMapping(value="/login", method=RequestMethod.GET) // /sample/login
	public String login() {
		log.info("/login GET 요청.......");
		// view 리졸버 login
		return "login";
	}
//	//POST 방식응답
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String loginPost() {
//		log.info("/login Post 요청");
//		return "/sample/basic";
//	}
	

	// POST 방식 응답 + 사용자의 입력값 가져오기
	// HttpServletRequest 이용 -- 잘안씀
	// 바인딩 변수 사용 --***
	// 바인딩 객체 사용 --***
//	@RequestMapping(value="/login",method=RequestMethod.POST)
	//HttpServletRequest 방식
//	public String loginPost(HttpServletRequest req) {
//		
//		log.info("/member/login Post 요청");
//		
//		log.info("userid : "+req.getParameter("userid"));
//		log.info("pasword : "+req.getParameter("password"));
//		
//		
//		return "/sample/basic";
//	}
	
	//바인딩 변수사용
	// Action 이라고 생각하자
//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public String loginPost(String userid, @RequestParam("pwd") String password,Model model) { // jsp에서 넘어오는 변수 name 명과 동일하게 작성해주어야한다.
//		
//		log.info("/member/login Post 요청");
//		
//		log.info("userid : "+userid);
//		log.info("pasword : "+password);
//		
//		model.addAttribute("userid", userid);	  // request.setAttribute
//		model.addAttribute("password", password); // request.setAttribute
//		
//		return "/sample/basic";
//	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String loginPost(@ModelAttribute("login") UserDTO userDto) {
		
		log.info("/member/login Post 요청");
		
		log.info("userid : "+userDto.getUserid());
		log.info("pasword : "+userDto.getPassword());
		log.info("name : "+userDto.getName());
		
		
		return "/sample/basic";
	}
	
}
