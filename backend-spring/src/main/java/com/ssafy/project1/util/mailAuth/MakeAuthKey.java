package com.ssafy.project1.util.mailAuth;

public class MakeAuthKey {
	String key;
	public void makeKey() {	//6자리의 숫자를 랜덤 생성 후 문자열로 반환 6자리보다 적을 수도 있음
		System.out.println("make key inner");
		double random = Math.random();
		int ranNum = (int)(random*1000000);
		key = ""+ranNum;
	}
	public String getKey() {
		return key;
	}
}
