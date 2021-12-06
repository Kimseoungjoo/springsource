package com.company.service;

import java.util.List;

import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;

public interface BoardService {
	// 게시판
	public boolean isnertBoard(BoardDTO insertDto);
	public List<BoardDTO> allList(Criteria cri);
	public BoardDTO readBoard(int bno);
	public boolean removeBoard(int bno);
	public boolean updateBoard(BoardDTO updateDto);
	public int getTotalCount(Criteria cri);
	
	
}
