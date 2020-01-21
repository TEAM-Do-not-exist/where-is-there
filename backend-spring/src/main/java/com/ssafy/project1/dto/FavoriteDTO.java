package com.ssafy.project1.dto;

public class FavoriteDTO {
	private String fid;
	private int fcode;
	public FavoriteDTO(String fid, int fcode) {
		super();
		this.fid = fid;
		this.fcode = fcode;
	}
	public FavoriteDTO() {
		super();
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public int getFcode() {
		return fcode;
	}
	public void setFcode(int fcode) {
		this.fcode = fcode;
	}
	@Override
	public String toString() {
		return "FavoriteDTO [fid=" + fid + ", fcode=" + fcode + "]";
	}
	
	

}
