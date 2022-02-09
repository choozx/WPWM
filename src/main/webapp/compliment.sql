create table compliment(
	c_no number(10),
	c_name varchar2 (30 char) not null,
	c_tier varchar2(30 char) not null,
	c_memo varchar2(100 char) not null,
	c_likecount number(10) not null,
	c_pw number(4) not null,
	c_date date not null,
	c_username varchar2 (30 char),
	CONSTRAINT compliment_pk PRIMARY KEY (c_no, c_username)
	);
	
create sequence compliment_seq;

insert into compliment values(compliment_seq.nextval,'name','tier','memo','0','1234', sysdate, 'username');

select * from compliment

drop table compliment cascade constraint purge;
drop sequence compliment_seq;
												
-- 추천테이블 : 좋아요 누른 사람, 좋아요 누른 게시판 번호
create table likey(                             
	l_username varchar2(30 char),
	l_no number(10),
	CONSTRAINT likey_pk PRIMARY KEY (l_username, l_no)
	);
	
insert into LIKEY values('ㅂㅈㄷ','0')
select * from likey;

drop table likey cascade constraint purge;
