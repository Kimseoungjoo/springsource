package com.company.factorial;

import org.springframework.stereotype.Component;

import com.company.app.Calculator;

@Component("forc")
public class ForCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		long result =1;
		
		for(int i=1; i<=num; i++) {
			result*=i;
		}
		
		return result;
	}

}
