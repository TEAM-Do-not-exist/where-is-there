package com.ssafy.project1.dto;

public class CommentDTO {
	private String cid;
	private int ccode;
	private String content;
	private String ctime;
	private String psource;

	public CommentDTO(int ccode, String cid, String content, String ctime) {
		super();
		this.cid = cid;
		this.ccode = ccode;
		this.content = content;
		this.ctime = ctime;
	}

	public CommentDTO(int ccode, String cid, String content, String ctime, String psource) {
		this.cid = cid;
		this.ccode = ccode;
		this.content = content;
		this.ctime = ctime;
		this.psource = psource;

	}

	public CommentDTO() {
		super();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getCcode() {
		return ccode;
	}

	public void setCcode(int ccode) {
		this.ccode = ccode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getPsource() {
		return psource;
	}

	public void setPsource(String psource) {
		this.psource = psource;
	}

	@Override
	public String toString() {
		return "CommentDTO [cid=" + cid + ", ccode=" + ccode + ", content=" + content + ", ctime=" + ctime
				+ ", psource=" + psource + "]";
	}

}
