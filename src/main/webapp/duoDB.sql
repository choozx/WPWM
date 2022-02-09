create table duo(
	d_no number(10) primary key,
 	d_name varchar2 (30 char) not null,   
	d_position varchar2 (30 char) not null,
	d_type varchar2 (30 char) not null,
	d_memo varchar2 (100 char) not null,
	d_pw number(4) not null,
	d_date date not null,
	d_tier varchar2(30 char) not null,
	d_win number(10) not null,
	d_lose number(10) not null
);
create sequence duo_seq;

insert into duo values(duo_seq.nextval,'name', 'position', 'type', 'memo', '1234', sysdate, 'tier', '1', '2');


select * from duo

drop table duo cascade constraint purge;
drop sequence duo_seq;