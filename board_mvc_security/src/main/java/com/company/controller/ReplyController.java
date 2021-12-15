package com.company.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;
import com.company.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

	@Autowired
	private ReplyService service;

	@PostMapping("/new")
	public ResponseEntity<String> insert(@RequestBody ReplyDTO insertDto) {
		log.info("댓글 추가 " + insertDto);
		return service.insertReply(insertDto) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 댓글 하나 가져오기 /replies/{rno} + GET => return 댓글내용(JSON)
	// /replies/1 + GET
	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReplyDTO> readGet(@PathVariable("rno") int rno) {
		log.info("댓글 가져오기 번호" + rno);

		return new ResponseEntity<ReplyDTO>(service.readReply(rno), HttpStatus.OK);

	}

	// 댓글 수정 /replies/{rno} + PUT + 수정댓글내용(JSON) => return success or fail
	@PutMapping("/{rno}")
	public ResponseEntity<String> updatePut(@PathVariable int rno, @RequestBody ReplyDTO updateDto) {
		log.info("댓글 수정" + updateDto);
		updateDto.setRno(rno);
		return service.updateReply(updateDto) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 댓글 삭제 /replies/{rno} + DELETE => return success or fail
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> removeDelete(@PathVariable int rno) {
		log.info("댓글 삭제" + rno);

		return service.deleteReply(rno) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

//	//댓글 전체 가져오기 /replies/pages/{bno}/{page} + GET => return 댓글내용(JSON)
//	@GetMapping("/pages/{bno}/{page}")
//	public ResponseEntity<List<ReplyDTO>> listGet(@PathVariable int bno,@PathVariable int page){
//		log.info("전체 리스트 요청"+bno);
//		Criteria cri = new Criteria(page,10);
//		
//		return new ResponseEntity<List<ReplyDTO>>(service.listReply(cri,bno),HttpStatus.OK);
//	}
//}

	// 페이지 나누기 댓글 처리
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> listGet(@PathVariable int bno, @PathVariable int page) {
		log.info("전체 리스트 요청" + bno);
		Criteria cri = new Criteria(page, 10);
				
		return new ResponseEntity<ReplyPageDTO>(service.listReply(cri, bno), HttpStatus.OK);
	}
}
