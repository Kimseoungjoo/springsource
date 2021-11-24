package com.company.app;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookDTO;
import com.company.service.BookService;

public class BookClient {
	private static BookService service;
	public static void main(String[] args) {
		//console창에 결과를 볼거야
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		// 서비스 호출 
		// BookService service = new BookServiceImpl();
		service = (BookService)ctx.getBean("bookServiceImpl");
		menu();
	}
	
	public static void menu() {
		
		boolean flag = true;
		
		while(flag) {
			System.out.println("====================================================");
			System.out.println("1. 도서 목록 전체조회");
			System.out.println("2. 도서 정보 조회");
			System.out.println("3. 도서 정보 추가");
			System.out.println("4. 도서 정보 삭제");
			System.out.println("5. 도서 정보 수정");
			System.out.println("6. 종료");
			System.out.println("====================================================");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("메뉴 선택 >>");
			int no = Integer.parseInt(sc.nextLine());
			
			switch (no) {
			case 1:
				List<BookDTO> list = service.getList();
				
				System.out.println("코드		제목	   작가	  가격");
				System.out.println("================================");
				
				for(BookDTO dto : list) {
					System.out.print(dto.getCode()+"\t");
					System.out.print(dto.getTitle()+"\t");
					System.out.print(dto.getWriter()+"\t");
					System.out.print(dto.getPrice()+"\n");
				}
				break;
			case 2:
				System.out.print("조회할 도서 코드 입력 >> ");
				String selectCode = sc.nextLine();
				BookDTO seleectDto = service.selectBook(selectCode);
				System.out.print(seleectDto.getCode()+"\t");
				System.out.print(seleectDto.getTitle()+"\t");
				System.out.print(seleectDto.getWriter()+"\t");
				System.out.print(seleectDto.getPrice()+"\n");
				break;
			case 3:
				BookDTO dto = new BookDTO();
				
				System.out.print("추가할 도서 코드 입력 >> ");
				dto.setCode(sc.nextLine());
				System.out.print("추가할 도서 제목 입력 >> ");
				dto.setTitle(sc.nextLine());
				System.out.print("추가할 도서 작가 입력 >> ");
				dto.setWriter(sc.nextLine());
				System.out.print("추가할 도서 가격 입력 >> ");
				dto.setPrice(Integer.parseInt(sc.nextLine()));
				
				System.out.print(service.insertBook(dto)? "추가완료":"추가실패");
				
				break;
			case 4:
				System.out.print("삭제할 도서 코드 입력 >> ");
				String deleteCode = sc.nextLine();
				System.out.println(service.deleteBook(deleteCode) ? "삭제완료!!": "삭제안돼!!!");
				break;
			case 5:
				System.out.print("수정할 도서 코드 입력 >> ");
				String updateCode = sc.nextLine();
				System.out.print("수정할 도서 가격 입력 >> ");
				int updatePrice =Integer.parseInt(sc.nextLine());
				
				System.out.print(service.updateBook(updateCode, updatePrice)? "수정완료":"수정실패");
				
				break;

			default:
				flag = false;
				break;
			}
		}
	}
}
