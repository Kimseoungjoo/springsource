package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.mapper.BoardAttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Autowired
	private BoardAttachMapper boardAttachMapper;
	
	@Transactional
	@Override
	public boolean isnertBoard(BoardDTO insertDto) {
		 
		// 게시물 등록
		boolean result = mapper.insert(insertDto) > 0 ? true:false;
		
		// 첨부파일 등록을 따로 해줘야 한다. 
		if(insertDto.getAttachList()==null || insertDto.getAttachList().size() <=0) {
			return false;
		}
		
		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return result;
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

	@Override
	public List<AttachFileDTO> findByBno(int bno) {
		return boardAttachMapper.read(bno);
	}
	

}
