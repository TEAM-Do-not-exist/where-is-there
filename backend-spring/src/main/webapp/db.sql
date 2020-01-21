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
insert into mem_p1 values('aaa','aaa','박명수','asdf@naver.com','01012345678');

select * from mem_p1;
select count(*) from mem_p1 where pw='ssafy' and id='ssafy';

create table photo(
	pcode int comment '코드' auto_increment primary key,
    pplace varchar(300) comment '장소',
    purl varchar(300) comment 'URL' unique,
    pname varchar(200) comment '상호명',
    psource varchar(200) comment '출처'
) comment='크롤링정보';

select * from photo;
select * from mem_p1;
select * from comment;
insert into photo values (0,'11','11','11','11');

create table comment(
	ccode int comment '코드' unique,
	cid varchar(30) COMMENT '아이디',
	content varchar(1000) not null,
    ctime datetime,
	foreign key(cid) references mem_p1(id) ,
	foreign key(ccode) references photo(pcode),
    primary key(cid,ccode)
) comment='댓글';
-- drop table comment;

insert into comment values (1,'aaa','asdf',now());

select * from comment;

create table favorite(
	fid varchar(30) COMMENT '아이디',
	fcode int comment '코드' ,
	foreign key(fid) references mem_p1(id) ,
	foreign key(fcode) references photo(pcode),
    primary key(fid,fcode)
) comment='좋아요';

