create table Suggestion(
	s_no number(20) primary key,
	s_headname varchar2 (30 char) not null,
	s_name varchar2 (30 char) not null,
	s_title varchar2 (50 char) not null,
	s_pw varchar2 (30 char) not null,
	s_text varchar2 (200 char) not null,
	s_date date not null
);

create sequence suggestion_seq;

insert into SUGGESTION values(SUGGESTION_seq.nextval,'머릿말', '작성자', '제목', '비밀번호', '내용', sysdate);

select * from SUGGESTION




drop table Suggestion cascade constraint purge;
