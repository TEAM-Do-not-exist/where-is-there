package com.ssafy.project1.dto;

public class PhotoDTO {
	private int pcode;
	private String pplace;
	private String purl;
	private String pname;
	private String psource;
	private int favorite_cnt;
	public PhotoDTO() {
	}
	public PhotoDTO(int pcode, String pplace, String purl, String pname, String psource) {
		super();
		this.pcode = pcode;
		this.pplace = pplace;
		this.purl = purl;
		this.pname = pname;
		this.psource = psource;
	}
	
	public PhotoDTO(int pcode, String pplace, String purl, String pname, String psource, int favorite_cnt) {
		super();
		this.pcode = pcode;
		this.pplace = pplace;
		this.purl = purl;
		this.pname = pname;
		this.psource = psource;
		this.favorite_cnt = favorite_cnt;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPplace() {
		return pplace;
	}
	public void setPplace(String pplace) {
		this.pplace = pplace;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPsource() {
		return psource;
	}
	public void setPsource(String psource) {
		this.psource = psource;
	}
	
	public int getFavorite_cnt() {
		return favorite_cnt;
	}
	public void setFavorite_cnt(int favorite_cnt) {
		this.favorite_cnt = favorite_cnt;
	}
	@Override
	public String toString() {
		return "PhotoDTO [pcode=" + pcode + ", pplace=" + pplace + ", purl=" + purl + ", pname=" + pname + ", psource="
				+ psource + "]";
	}
	
	
}
