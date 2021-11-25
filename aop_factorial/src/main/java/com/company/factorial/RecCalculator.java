package com.company.factorial;

import org.springframework.stereotype.Component;

import com.company.app.Calculator;

@Component("rec")
public class RecCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		// 재귀호출(자기 자신을 호출)
		if(num==0) {
			return 1;
		}else {
			return num * factorial(num-1);
		}
	}

}
