create database ssafy_project1;
show databases;
use ssafy_project1;
create table mem_p1(
   id varchar(30) COMMENT '아이디',
  pw varchar(30) not null COMMENT '비밀번호',
  name varchar(30) not null COMMENT '이름',
  email varchar(30) COMMENT '주소',
  phone varchar(16) COMMENT '폰번호',
  primary key(id)
) COMMENT='회원정보';
insert into mem_p1 values('ssafy','ssafy','정성원','biro119@naver.com','01012345678');
select * from mem_p1;
select count(*) from mem_p1 where pw='ssafy' and id='ssafy';

create table crawling(
	pcode int comment '코드' auto_increment primary key,
    pplace varchar(300) comment '장소',
    purl varchar(300) comment 'URL' unique,
    pname varchar(200) comment '상호명',
    psource varchar(200) comment '출처'
) comment='크롤링정보';

select * from crawling;
create table comment(
	cid varchar(30) COMMENT '아이디',
	ccode int comment '코드' ,
	content varchar(1000) not null,
    ctime datetime,
	foreign key(cid) references mem_p1(id) ,
	foreign key(ccode) references crawling(pcode),
    primary key(cid,ccode)
) comment='댓글';

create table favorite(
	id varchar(30) COMMENT '아이디',
	pcode int comment '코드' ,
	constraint `fk_favorite1` foreign key(id) references mem_p1(id),
    constraint `fk_favorite2` foreign key(pcode) references crawling(pcode),
    primary key(id,pcode)
) comment='좋아요';

