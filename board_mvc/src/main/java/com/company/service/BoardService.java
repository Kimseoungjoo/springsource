package com.company.service;

import java.util.List;

import com.company.domain.BoardDTO;
import com.company.domain.Criteria;

public interface BoardService {
	public boolean isnertBoard(BoardDTO insertDto);
	public List<BoardDTO> allList(Criteria cri);
	public BoardDTO readBoard(int bno);
	public boolean removeBoard(int bno);
	public boolean updateBoard(BoardDTO updateDto);
	public int getTotalCount();
}
