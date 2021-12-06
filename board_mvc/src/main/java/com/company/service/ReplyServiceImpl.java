package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.ReplyDTO;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public boolean insertReply(ReplyDTO insertDto) {
		return replyMapper.insert(insertDto) > 0 ? true:false;
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
	public boolean deleteReply(int rno) {
		return replyMapper.delete(rno) > 0 ? true:false;
	}

	@Override
	public List<ReplyDTO> listReply(int bno) {
		return replyMapper.list(bno);
	}

}
