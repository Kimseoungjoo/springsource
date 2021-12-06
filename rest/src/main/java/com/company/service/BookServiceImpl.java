package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;
	
	@Override
	public List<BookDTO> allList() {
		return mapper.list();
	}

	@Override
	public boolean insertBook(BookDTO insertDto) {
		return mapper.insert(insertDto) > 0 ? true:false;
	}

	@Override
	public boolean deleteBook(String code) {
		return mapper.remove(code) > 0 ? true:false;
	}

	@Override
	public BookDTO readBook(String code) {
		return mapper.read(code);
	}

	@Override
	public boolean updateBook(BookDTO modifyDto) {
		return mapper.update(modifyDto) > 0 ? true:false;
	}

}
