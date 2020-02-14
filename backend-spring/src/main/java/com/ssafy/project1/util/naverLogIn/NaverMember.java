package com.ssafy.project1.util.naverLogIn;

public class NaverMember {
	private String name;
	private String nickName;
	private String email;

	public NaverMember() {
	}

	public NaverMember(String name, String nickName, String email) {
		super();
		this.name = name;
		this.nickName = nickName;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "NaverMember [name=" + name + ", nickName=" + nickName + ", email=" + email + "]";
	}

}
