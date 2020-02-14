package com.ssafy.project1.dto;

public class PhotoCheckDTO {
	private int pcode;
	private String purl;

	public PhotoCheckDTO() {
	}

	public PhotoCheckDTO(int pcode, String purl) {
		super();
		this.pcode = pcode;
		this.purl = purl;
	}

	public int getPcode() {
		return pcode;
	}

	public void setPcode(int pcode) {
		this.pcode = pcode;
	}

	public String getpurl() {
		return purl;
	}

	public void setpurl(String purl) {
		this.purl = purl;
	}

	@Override
	public String toString() {
		return "PhotoCheckDTO [pcode=" + pcode + ", purl=" + purl + "]";
	}

}
