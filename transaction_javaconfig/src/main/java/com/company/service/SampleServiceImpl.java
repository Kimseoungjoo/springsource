package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.mapper.SampleMapper;

@Service("sample")
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper mapper1;

	@Autowired
	private SampleMapper mapper2;
	
	@Transactional
	@Override
	public void addData(String data) {
		mapper1.insertCol1(data);
		
		mapper2.insertCol1(data);
	}

}
