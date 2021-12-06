package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BookDTO;

public interface BookMapper {
	public List<BookDTO> list();
	public int insert(BookDTO insertDto);
	public int remove(String code);
	public BookDTO read(String code);
	public int update(BookDTO updateDto);
}
