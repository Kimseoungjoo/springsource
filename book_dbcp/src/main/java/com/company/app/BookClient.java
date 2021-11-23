package com.company.app;

import java.util.List;
import java.util.Scanner;

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

		// 새로운 도서 입력 
//		System.out.println(service.insertBook(new BookDTO("1006","스프링","잘하자뜽두야",3000))? "입력성공" : "입력실패");
		
		
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
		
		// 도서 정보 삭제
		Scanner sc = new Scanner(System.in);
//		System.out.print("삭제할 코드 입력 : ");
//		String code = sc.next();
//		
//		System.out.println(service.deleteBook(code) ? "삭제완료!!": "삭제안돼!!!");
		
//		// 도서 정보 수정
//		System.out.print("업데이트할 코드 입력 : ");
//		String code1 = sc.next();
//		System.out.print("\n변경할 가격 입력");
//		int price = sc.nextInt();
//		
//		System.out.println(service.updateBook(code1,price) ? "수정완료!!": "수정안돼!!!");
		
		
	}

}
