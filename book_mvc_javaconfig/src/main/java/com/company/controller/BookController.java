package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	//insert.jsp 보여주는 컨트롤러 생성
	@GetMapping("/insert")
	public void insertGet() {
		log.info("insert 요청");
	}
	@PostMapping("/insert")
	public String insertPost(BookDTO insertDto) {
		log.info("도서입력"+insertDto);
		try {
			if(service.insertBook(insertDto)) {
				return "redirect:list";
			}
		} catch (Exception e) {
			return "insert";
		}
		return "insert";
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		
		List<BookDTO> list= service.allList();
		log.info("list 요청"+list);
		
		model.addAttribute("list",list); // ==request.setAttribute()
		// /book/list => list.jsp
	}
	
	// /book/read or /modify
	@GetMapping({"/read","/modify"})
	public void readGet(String code, Model model) {
		log.info("코드 넘어와라"+code);
		
		BookDTO readDto = service.readBook(code);
		
		model.addAttribute("readDto",readDto);
		
		// /book/read => 	/WEB-INF/views/book/read.jsp
		// /book/modify => /WEB-INF/views/book/modify.jsp
	}
	//  /book/modify + POST
	@PostMapping("/update")
	public String modifyPost(BookDTO modifyDto, RedirectAttributes rttr) {
		log.info("수정요청"+modifyDto);
		
		// 수정이 완료된 후 내용보기
		service.updateBook(modifyDto);
		
		rttr.addAttribute("code",modifyDto.getCode());
		return "redirect:/book/read";
	}
	
	// book/remove
	@GetMapping("/remove")
	public String deletePost(String code) {
		log.info("도서삭제요청"+code);
		service.deleteBook(code);
		return "redirect:/book/list";
	}
}
