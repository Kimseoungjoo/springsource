package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		
		return "home";
	}
	
	@GetMapping("/all")
	public void all() {
		log.info("all");
		
	}
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@GetMapping("/member")
	public void member() {
		log.info("member");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void admin() {
		log.info("admin");
	}
	
	//403 에러 일때 동작
	@GetMapping("/accessError")
	public void accessError() {
	}
	
	// Authentication 객체에 담긴 정보 확인
	@ResponseBody
	@GetMapping("/auth")
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
