package ch3;

public class SamsungTv implements TV {
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
