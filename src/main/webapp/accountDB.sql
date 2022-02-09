create table lol_account(
	a_id varchar2 (30 char) primary key,
	a_pw varchar2 (30 char) not null,
	a_nickname varchar2 (30 char) not null,
	constraint l_a_nickname_uk unique(a_nickname),
	a_puuid varchar2(150 char) not null
);



insert into LOL_ACCOUNT values('id', 'pw', 'nickname', 'puuid');


select * from lol_account;
update lol_account set a_pw='2' where a_id='1'
delete lol_account where a_id='1'


drop table lol_account cascade constraint purge;
