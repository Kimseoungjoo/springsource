package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.AddDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller// 객체를 생성해주는 annotation
@RequestMapping("/calc/*")
public class AddController {

	@GetMapping("/add")
	public void addGet(/* int x, int y */) {
		log.info("/add get 방식 >>>");
	}
	
//	@PostMapping("/add")
//	public void addPost(@RequestParam("num1") int x, @RequestParam("num2") int y) {
//		log.info("/calc/add post 요청>>");
//		System.out.println("num1 : " + x);
//		System.out.println("num2 : " + y);
//		
//	}
	@PostMapping("/add")
	public void addPost(AddDTO addDto, Model model) {
		log.info("/calc/add post 요청>> num1 : " + addDto.getNum1()+" num2 : "+addDto.getNum2());
		
		// 덧셈 작업을 하고 결과를  add.jsp 보여주기
		int result = addDto.getNum1()+addDto.getNum2();
		model.addAttribute("result",result);
		// /calc/add
	}
	
}
