package com.company.service;

import java.util.List;

import com.company.domain.BookDTO;


public interface BookService {
	// 전체 도서 목록
	public List<BookDTO> getList();
}
