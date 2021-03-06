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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;

/**
 * Handles requests for the application home page.
 */
/**
 * Handles requests for the application home page.
 */

@Log4j2
@Controller
public class HomeController {
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/doA")
	public String doA(RedirectAttributes rttr) {
		log.info("/doA 요청");
		
		// RedirectAttributes : sendRedirect 움직일 때 값을 전달하는 객체
		// addAttribute : 주소줄에 파라메터로 보내짐
		// addFlashAttribute : Session 객체에 이용하나, 잠시만 사용(다음 해당 페이지까지만 사용가능하다)
		
		rttr.addAttribute("age", 20); //http://localhost:8080/doB?age=20
		rttr.addFlashAttribute("name","홍길동"); //
		
		return "redirect:/doB";
	}
	
	@GetMapping("/doB")
	public void doB() {
		log.info("/doB 요청");
	}
	
	@GetMapping("/doC")
	public void doC() {
		log.info("doC 요청");
	}
}
	