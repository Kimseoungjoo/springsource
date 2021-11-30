package com.company.service;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardService {
	public boolean isnertBoard(BoardDTO insertDto);
	public List<BoardDTO> allList();
	public BoardDTO readBoard(int bno);
	public boolean removeBoard(int bno);
	public boolean updateBoard(BoardDTO updateDto);
}
