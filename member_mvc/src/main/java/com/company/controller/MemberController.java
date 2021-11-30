package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member/*")
// 로그인 로그아웃 회원탈퇴 회원수정
public class MemberController {

	@Autowired
	private MemberService service;
	
	// signin 페이지 들어옴
	@GetMapping("/signin")
	public void readGet() {
		log.info("로그인 요청");
		// /member/signin/
	}
	
	// 로그인 시 확인 작성 
	@PostMapping("/signin")
	public String loginPost(LoginDTO logDto, HttpSession session) {
		log.info("login 요청"+logDto);
			
		LoginDTO loginDto = service.login(logDto); 
	
		session.setAttribute("loginDto", loginDto);
			
		return "redirect:/";
	}
	
	// 로그아웃 시 
	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("로그아웃");
		session.invalidate();
		return "redirect:/";
	}
	
	// 비밀번호 변경시 jsp 이동
	@GetMapping("/changePwd")
	public void changeGet() {
		log.info("비밀번호 변경(changePwd.jsp) 요청");
	}
	
	// 비밀번호 변경 
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDto, HttpSession session) {
		log.info("비밀번호 변경 요청"+ changeDto);
		//userid 가져오기
		LoginDTO loginDto = (LoginDTO) session.getAttribute("loginDto");
		changeDto.setUserid(loginDto.getUserid());
		
		if(service.changePwd(changeDto)) {
			session.invalidate();
			
			return "redirect:/member/signin";
		}
		return "redirect:/member/changePwd";
	}
	
	// 회원탈퇴 창 띄우기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원탈퇴.jsp");
	}
	
	@PostMapping("/leave")
	public String leavePost(LoginDTO leaveDto,HttpSession session) {
		log.info("회원 탈퇴 요청"+leaveDto);
		LoginDTO loginDto = (LoginDTO) session.getAttribute("loginDto");
		leaveDto.setUserid(loginDto.getUserid());
		if(service.leaveLog(leaveDto)) {
			session.invalidate();
			return "redirect:/";
		}
		return "redierect:/member/leave";  // 탈퇴 실패시
		
	}
}
