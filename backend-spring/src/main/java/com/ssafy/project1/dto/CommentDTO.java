package com.ssafy.project1.dto;


/*create table comment(
		cid varchar(30) COMMENT '아이디',
		ccode int comment '코드' ,
		content varchar(1000) not null,
	    ctime datetime,
		foreign key(cid) references mem_p1(id) ,
		foreign key(ccode) references crawling(pcode),
	    primary key(cid,ccode)
	) comment='댓글';
*/

public class CommentDTO {
	private String cid;
	private int ccode;
	private String content;
	private String ctime;
	private String purl;
	
	public CommentDTO(int ccode, String cid, String content, String ctime) {
		super();
		this.cid = cid;
		this.ccode = ccode;
		this.content = content;
		this.ctime = ctime;
	}
	public CommentDTO(int ccode, String cid, String content, String ctime, String purl) {
		this.cid = cid;
		this.ccode = ccode;
		this.content = content;
		this.ctime = ctime;
		this.purl = purl;
		
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
	
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	@Override
	public String toString() {
		return "CommentDTO [cid=" + cid + ", ccode=" + ccode + ", content=" + content + ", ctime=" + ctime + ", purl="+purl+"]";
	}
	
	

}
