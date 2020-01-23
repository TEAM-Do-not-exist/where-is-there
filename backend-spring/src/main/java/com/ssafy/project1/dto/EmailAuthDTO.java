package com.ssafy.project1.dto;

public class EmailAuthDTO {
	private String id;
	private String email;
	private String auth_key;
	private String time_expire;
	private boolean auth_check;
	
	public EmailAuthDTO() {
		super();
	}

	public EmailAuthDTO(String id, String email, String auth_key, String time_expire, boolean auth_check) {
		super();
		this.id = id;
		this.email = email;
		this.auth_key = auth_key;
		this.time_expire = time_expire;
		this.auth_check = auth_check;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public boolean isAuth_check() {
		return auth_check;
	}

	public void setAuth_check(boolean auth_check) {
		this.auth_check = auth_check;
	}

	@Override
	public String toString() {
		return "EmailAuthDTO [id=" + id + ", email=" + email + ", auth_key=" + auth_key + ", time_expire=" + time_expire
				+ ", auth_check=" + auth_check + "]";
	}
	
	
}
