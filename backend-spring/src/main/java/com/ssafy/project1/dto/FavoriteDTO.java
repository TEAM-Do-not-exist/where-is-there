package com.ssafy.project1.dto;

public class FavoriteDTO {
	private String fid;
	private int fcode;
	private String psource;

	public FavoriteDTO(String fid, int fcode) {
		super();
		this.fid = fid;
		this.fcode = fcode;
	}

	public FavoriteDTO(String fid, int fcode, String psource) {
		super();
		this.fid = fid;
		this.fcode = fcode;
		this.psource = psource;
	}

	public String getPsource() {
		return psource;
	}

	public void setPsource(String psource) {
		this.psource = psource;
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
		return "FavoriteDTO [fid=" + fid + ", fcode=" + fcode + ", psource=" + psource + "]";
	}

}
