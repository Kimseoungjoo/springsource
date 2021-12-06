package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	
	@Override
	public boolean isnertBoard(BoardDTO insertDto) {
		return mapper.insert(insertDto) > 0 ? true:false;
	}

	@Override
	public List<BoardDTO> allList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public BoardDTO readBoard(int bno) { 
		return mapper.read(bno);
	}

	@Override
	public boolean removeBoard(int bno) {
		return mapper.remove(bno) > 0 ? true:false;
	}

	@Override
	public boolean updateBoard(BoardDTO updateDto) {
		return mapper.update(updateDto) > 0 ? true:false;
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.totalCnt(cri);
	}


}
