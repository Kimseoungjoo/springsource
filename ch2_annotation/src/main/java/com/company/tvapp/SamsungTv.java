package com.company.tvapp;

import org.springframework.stereotype.Component;

//@Component  // samsungTv 객체 생성
@Component("samsung") // samsung으로 객체 생성
public class SamsungTv implements TV { 
	
	public SamsungTv() {
		System.out.println("SamsungTv 객체 생성");
	}
	@Override
	public void turnOn() {
		System.out.println("SamsungTv - 전원 On");
	}
	@Override
	public void turnOff() {
		System.out.println("SamsungTv - 전원 Off");
	}
	@Override
	public void soundUp() {
		System.out.println("SamsungTv - 볼륨 Up");
	}
	@Override
	public void soundDown() {
		System.out.println("SamsungTv - 볼륨 Down");
	}
}
