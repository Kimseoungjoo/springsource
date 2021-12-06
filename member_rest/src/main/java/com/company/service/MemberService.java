package com.company.service;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;

public interface MemberService {
	public boolean register(MemberDTO memberDto); // 
	public MemberDTO overlabCheck(String userid); // 회원가입 아이디 중복체크
	public LoginDTO login(LoginDTO logDto); // 로그인
	public boolean changePwd(ChangeDTO changeDto);
	public boolean leaveLog(LoginDTO leaveDto);
}
