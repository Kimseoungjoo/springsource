package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BoardDTO;
import com.company.domain.Criteria;

public interface BoardMapper {
	public int insert(BoardDTO insertDto);
	public List<BoardDTO> list(Criteria cri);
	public BoardDTO read(int bno);
	public int remove(int bno);
	public int update(BoardDTO updateDto);
	public int totalCnt(Criteria cri);
	
	// 댓글 갯수 추가
	public int updateReplyCnt(@Param("bno") int bno,@Param("amount") int amount);
}
