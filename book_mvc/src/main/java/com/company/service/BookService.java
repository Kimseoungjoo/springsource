package com.company.service;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookService {
	public List<BookDTO> allList();
	public boolean insertBook(BookDTO insertDto);
	public boolean deleteBook(String code);
	public BookDTO readBook(String code);
	public boolean updateBook(BookDTO modifyDto);
}
