package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.PageDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired 
	private BoardService service; 
	
	//  board/register.jsp
	@GetMapping("/register")
	public void registerGet() {
		log.info("register.jsp 요청");
	}
	
	//게시판 등록
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, RedirectAttributes rttr) {
		log.info("registerPost 데이터 가져오기"+insertDto);//

		// 첨부파일 확인하기 
//		if(insertDto.getAttachList()!=null) {
//			insertDto.getAttachList().forEach(attach -> log.info(attach+""));
//		}
		
		service.isnertBoard(insertDto);
		
		//log.info("bno" + insertDto.getBno());
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";
	}
	
	//read 
	@GetMapping({"/read","/modify"})
	// 받아야하는 data가 여러개일 경우 받는 방법 1) request / 2) 일일이 data받기 / 3) domain으로 받기(모델에 따로 담을 필요가 없다.자동 유지가 된다)
	public void readGet(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		
		BoardDTO readDto =  service.readBoard(bno);
		
		model.addAttribute("dto",readDto);
	}
	
	//post /modify
	@PostMapping("/modify")
	public String modifyPost(BoardDTO updateDto, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 수정"+updateDto+"  "+cri);
	
		
		// 수정 완료 후 리스트로 이동
		service.updateBoard(updateDto);
		
		
		//페이지 나누기 값
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		// 검색 값 
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result","success");
		return "redirect:/board/list";
	}
	
	//post /remove
	@PostMapping("/remove")
	public String removePost(int bno,Criteria cri ,RedirectAttributes rttr) {

		// 첨부파일 목록 가져오기
		List<AttachFileDTO> attacahList =  service.findByBno(bno);

		if(service.removeBoard(bno)) {
			deleteFiles(attacahList);
		
	    
	    
			//페이지 나누기 값
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			// 검색 값 
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
		
			rttr.addFlashAttribute("result","success");
		
		}
		//삭제 후 리스트로 이동
		return "redirect:/board/list";
	}
	
	// 게시판 전체 목록
	@GetMapping("/list")
	public void listGet(Model model, Criteria cri) {
		log.info("전체리스트 요청"+cri);
		
		List<BoardDTO> list =  service.allList(cri);
		
		// 페이지 나누기를 위한 정보 
		int totalCnt = service.getTotalCount(cri);
		
		
		model.addAttribute("pageDto",new PageDTO(cri, totalCnt));
		model.addAttribute("list",list);
		
	}
	
	// file 가져오기
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno){
		log.info("파일 첨부 가져오기"+bno );
		return new ResponseEntity<List<AttachFileDTO>>(service.findByBno(bno),HttpStatus.OK);
	}
	
	
	//==========================기본 메소드===================================
	
	// 파일 삭제 메소드
	private void deleteFiles(List<AttachFileDTO> attachList) {
		if(attachList == null || attachList.size()==0) {
			return;
		}
		log.info("파일 삭제 중...");
		
		attachList.forEach(attach -> {
			Path file = Paths.get("c:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
			
			try {
				// 일반파일, 이미지 원본 파일만 삭제 
				Files.delete(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("c:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					
					// 이미지 썸네일 삭제
					Files.delete(thumbNail);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
