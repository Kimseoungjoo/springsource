package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean insertReply(ReplyDTO insertDto) {
		// spring_board replycnt +1 
		boardMapper.updateReplyCnt(insertDto.getBno(),1);
		return replyMapper.insert(insertDto) > 0 ? true:false;
	}
	@Transactional
	@Override
	public boolean deleteReply(int rno) {
		// spring_board replycnt -1
		// bno 알아내기 위해서 
		ReplyDTO reply = replyMapper.read(rno);
		
		boardMapper.updateReplyCnt(reply.getBno(), -1);
		return replyMapper.delete(rno) > 0 ? true:false;
	}

	@Override
	public ReplyDTO readReply(int rno) {
		return replyMapper.read(rno);
	}

	@Override
	public boolean updateReply(ReplyDTO updateDto) {
		return replyMapper.update(updateDto) > 0 ? true:false;
	}


	@Override
	public ReplyPageDTO listReply(Criteria cri ,int bno) {
		return new ReplyPageDTO(replyMapper.getCountByBno(bno), replyMapper.list(cri,bno));
	}

}
