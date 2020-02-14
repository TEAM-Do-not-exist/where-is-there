package com.ssafy.project1.dto;

public class MemberDTO {
	private String email;
	private String pw;
	private String nickname;
	private String name;
	private String phone;
	private int external;

	public MemberDTO() {
	}

	public MemberDTO(String email, String nickname, String name) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.name = name;
	}

	public MemberDTO(String email, String nickname) {
		super();
		this.email = email;
		this.nickname = nickname;
	}

	public MemberDTO(String email, String nickname, String name, int external) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.name = name;
		this.external = external;
	}

	public MemberDTO(String email, String nickname, int external) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.external = external;
	}

	public MemberDTO(String email, String pw, String nickname, String name, String phone) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.name = name;
		this.phone = phone;
	}

	public MemberDTO(String email, String pw, String nickname, String name, String phone, int external) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.name = name;
		this.phone = phone;
		this.external = external;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getExternal() {
		return external;
	}

	public void setExternal(int external) {
		this.external = external;
	}

	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", pw=" + pw + ", nickname=" + nickname + ", name=" + name + ", phone="
				+ phone + ", external=" + external + "]";
	}

}
