package com.ssafy.project1.dto;

public class ReportDTO {
	private int rnum;
	private int rcode;
	private String rid;
	private String rreason;

	public ReportDTO() {
		super();
	}

	public ReportDTO(int rnum, int rcode, String rid, String rreason) {
		super();
		this.rnum = rnum;
		this.rcode = rcode;
		this.rid = rid;
		this.rreason = rreason;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getRcode() {
		return rcode;
	}

	public void setRcode(int rcode) {
		this.rcode = rcode;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRreason() {
		return rreason;
	}

	public void setRreason(String rreason) {
		this.rreason = rreason;
	}

	@Override
	public String toString() {
		return "ReportDTO [rnum=" + rnum + ", rcode=" + rcode + ", rid=" + rid + ", rreason=" + rreason + "]";
	}

}
