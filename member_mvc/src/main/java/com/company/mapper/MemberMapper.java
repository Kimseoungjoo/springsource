package com.company.mapper;

import org.apache.ibatis.annotations.Param;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;

public interface MemberMapper {
	
	public int insert(MemberDTO memberDto);
	public MemberDTO overlap(String userid);
	public LoginDTO login(LoginDTO logDto);
	public int changePwd(ChangeDTO changeDto);
	public int leave(LoginDTO leaveDto);
}
