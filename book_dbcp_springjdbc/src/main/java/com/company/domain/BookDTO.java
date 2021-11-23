package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor	
@AllArgsConstructor // 생성자
@ToString
@Setter
@Getter
// 얘는 annotation을 사용하지 않는다 
public class BookDTO {
	private String code;
	private String title;
	private String writer;
	private int price;
}
