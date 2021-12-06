package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/book/*")
public class BookRestController {
	
	@Autowired
	private BookService service;
	
	// 새 도서 읿력
		@PostMapping("/insert-rest")
		//@ResponseBody // ResponseEntity<String> 써버렸기 떄문에 의미가 없다
		public String insertPost(@RequestBody BookDTO insertDto) {
			log.info("도서입력"+insertDto);
			try {
				if(service.insertBook(insertDto)) {
					return "success";
				}
			} catch (Exception e) {
				return "fail";
			}
			return "fail";
		}
		
	
		@GetMapping(value = "/{code}", produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<BookDTO> row(@PathVariable("code") String code){
			log.info("책하나 가져오기" + code);
			
			BookDTO dto = service.readBook(code);
			
			return new ResponseEntity<BookDTO>(dto,HttpStatus.OK);
		}
		
		@GetMapping(value="/rest-list",produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<List<BookDTO>> listGet(){
			// RessponseEntity 쓰는 목적 : 상태와 데이터를 확인하기 위해서 보낸다
			log.info("rest 방식 list 요청");
		
			return new ResponseEntity<List<BookDTO>>(service.allList(),HttpStatus.OK);
		}
		
		// /book/{code} + DELETE
		@DeleteMapping("/{code}")
		public ResponseEntity<String> delete(@PathVariable("code") String code){
			log.info("reat delete"+code);
			
			if(service.deleteBook(code)) {
				return new ResponseEntity<String>("success",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
			}
		}
		
		// /book/update + 수정내용 + put(patch)
		@PutMapping("/update")
		public ResponseEntity<String> modify(@RequestBody BookDTO updateDto){
			log.info("rest 수정"+updateDto);
			
			if(service.updateBook(updateDto)) {
				return new ResponseEntity<String>("success",HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
			
		}
		
}
