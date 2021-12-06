package com.company.service;

import java.util.List;

import com.company.domain.ReplyDTO;

public interface ReplyService {
	// 댓글 
	public boolean insertReply(ReplyDTO insertDto);
	public ReplyDTO readReply(int rno); // 댓글 하나 가져오기 
	public boolean updateReply(ReplyDTO updateDto);
	public boolean deleteReply(int rno);
	public List<ReplyDTO> listReply(int bno);
	
}
