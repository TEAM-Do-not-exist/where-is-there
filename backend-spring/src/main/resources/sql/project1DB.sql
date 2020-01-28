create database ssafy_project1;
use ssafy_project1;
create table mem_p1(
  id varchar(30) COMMENT '아이디',
  pw varchar(30) not null COMMENT '비밀번호',
  name varchar(30) not null COMMENT '이름',
  email varchar(30) COMMENT '주소',
  phone varchar(16) COMMENT '폰번호',
  primary key(id)
) COMMENT='회원정보';

create table photo(
	pcode int comment '코드' auto_increment primary key,
    pplace varchar(300) comment '장소',
    purl varchar(500) comment 'URL' unique,
    pname varchar(200) comment '상호명',
    psource varchar(500) comment '출처'
) comment='크롤링정보';

create table photo_check(
	pcode int comment '코드' auto_increment primary key,
    purl varchar(500) comment 'URL' unique
) comment='크롤링정보-관리자가 검열할 전체 사진';

create table comment(
	cid varchar(30) COMMENT '아이디',
	ccode int comment '코드' ,
	content varchar(1000) not null,
    ctime datetime,
	constraint `fk_comment` foreign key(cid) references mem_p1(id) ,
	constraint `fk_comment2` foreign key(ccode) references photo(pcode),
    primary key(cid,ccode)
) comment='댓글';

create table favorite(
	fid varchar(30) COMMENT '아이디',
	fcode int comment '코드' ,
	constraint `fk_favorite1` foreign key(fid) references mem_p1(id),
    constraint `fk_favorite2` foreign key(fcode) references photo(pcode),
    primary key(fid,fcode)
) comment='좋아요';

create table email_auth(
  id varchar(30) COMMENT '아이디',
  email varchar(30) not null COMMENT '이메일',
  auth_key varchar(30) not null COMMENT '인증키',
  time_expire datetime COMMENT '인증만료시간',
  auth_check bool default false COMMENT '인증확인',
  primary key(id)
) COMMENT='이메일 인증';
