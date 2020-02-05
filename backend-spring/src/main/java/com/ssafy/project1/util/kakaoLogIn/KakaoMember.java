package com.ssafy.project1.util.kakaoLogIn;

public class KakaoMember {
	private String email;
	private String nickName;
	public KakaoMember() {
		super();
	}
	public KakaoMember(String email, String nickName) {
		super();
		this.email = email;
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "KakaoMember [email=" + email + ", nickName=" + nickName + "]";
	}
	
	
}
