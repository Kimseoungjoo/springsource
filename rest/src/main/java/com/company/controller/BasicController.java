package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SampleDTO;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
public class BasicController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/hello")
	public String hello() {
		log.info("hello");
		return "Hello World";
	}

	@GetMapping(value = "/sample.xml", produces = { MediaType.APPLICATION_XML_VALUE })
	public SampleDTO sample() {
		log.info("sample");

		SampleDTO sample = new SampleDTO();

		sample.setFisrtName("hong");
		sample.setLastName("dong");

		return sample;
	}

	@GetMapping(value = "/sample.json", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SampleDTO sampleJson() {
		log.info("sample");

		SampleDTO sample = new SampleDTO();

		sample.setFisrtName("hong");
		sample.setLastName("dong");

		return sample;
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SampleDTO> sampleJsonList() {
		log.info("sample");

		List<SampleDTO> list = new ArrayList<SampleDTO>();

		for (int i = 0; i < 10; i++) {
			SampleDTO sample = new SampleDTO();
			sample.setMno(i + "");
			sample.setFisrtName("hong");
			sample.setLastName("dong");
			list.add(sample);
		}

		return list;
	}
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat,@PathVariable("pid") String pid) {
		return new String[] {
				"category : "+cat,
				"productId : "+pid
		};
	}
}
