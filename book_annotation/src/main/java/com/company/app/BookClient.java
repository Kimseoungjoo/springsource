package com.company.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookDTO;
import com.company.service.BookService;

public class BookClient {
	public static void main(String[] args) {
		//console창에 결과를 볼거야
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		// 서비스 호출 
		// BookService service = new BookServiceImpl();
		BookService service = (BookService)ctx.getBean("bookServiceImpl");
		// 전체 리스트 결과를 호출
		
		List<BookDTO> list = service.getList();
		
		System.out.println("코드		제목	   작가	  가격");
		System.out.println("================================");
		
		for(BookDTO dto : list) {
			System.out.print(dto.getCode()+"\t");
			System.out.print(dto.getTitle()+"\t");
			System.out.print(dto.getWriter()+"\t");
			System.out.print(dto.getPrice()+"\n");
		}
		
		
		
	}

}
