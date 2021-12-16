package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;

public interface ReplyService {
	// 댓글 
	public boolean insertReply(ReplyDTO insertDto);
	public ReplyDTO readReply(int rno); // 댓글 하나 가져오기 
	public boolean updateReply(ReplyDTO updateDto);
	public boolean deleteReply(int rno);
	public ReplyPageDTO listReply(Criteria cri, int bno);

	
}
