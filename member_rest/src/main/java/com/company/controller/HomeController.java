package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j2
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		log.info("/index 요청");
		return "/index";
	}
	
	@GetMapping("/insert")
	public String isnert() {
		log.info("insert 요청");
		return "/rest/insert";
	}
	
	// 비밀번호 변경시 jsp 이동
		@GetMapping("/changePwd")
		public String changeGet() {
			log.info("비밀번호 변경(changePwd.jsp) 요청");
			return "/rest/changePwd";
		}
		// 회원탈퇴 창 띄우기
		@GetMapping("/leave")
		public String leaveGet() {
			log.info("회원탈퇴.jsp");
			return "/rest/leave";
		}
	
}
