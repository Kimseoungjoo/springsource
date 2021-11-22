package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.persistence.BookDAO;

// BookServiceImpl 객체 생성 단, id 명은 bookServiceImpl

@Service // == @Component
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO dao;
	
	@Override
	public List<BookDTO> getList() {
		return dao.list();
	}

}
