package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service; 
	
	// / board/register.jsp
	@GetMapping("/register")
	public void registerGet() {
		log.info("register.jsp 요청");
	}
	//게시판 등록
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, RedirectAttributes rttr) {
		log.info("registerPost 데이터 가져오기"+insertDto);
		
		service.isnertBoard(insertDto);
		
//		log.info("bno" + insertDto.getBno());
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";
	}
	
	//read 
	@GetMapping({"/read","/modify"})
	public void readGet(int bno, Model model) {
		BoardDTO readDto =  service.readBoard(bno);
		
		model.addAttribute("dto",readDto);
	}
	
	//post /modify
	@PostMapping("/modify")
	public String modifyPost(BoardDTO updateDto) {
		log.info("게시글 수정"+updateDto);
		service.updateBoard(updateDto);
		
		return "redirect:/board/list";
	}
	//post /remove
	@PostMapping("/remove")
	public String removePost(int bno) {
		service.removeBoard(bno);
		
		//삭제 후 리스트로 이동
		return "redirect:/board/list";
	}
	
	// 게시판 전체 목록
	@GetMapping("/list")
	public void listGet(Model model) {
		List<BoardDTO> list =  service.allList();
		
		
		model.addAttribute("list",list);
	}
}
