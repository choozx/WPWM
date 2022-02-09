create table message(
	m_no number(10) primary key,
	m_sendnick varchar2 (30 char) not null,
	m_recvnick varchar2 (30 char) not null,
	m_content varchar2(1000 char) not null,
	m_sendtime date not null

);
create table sendmessage(
	sm_no number(10) primary key,
	sm_sendnick varchar2 (30 char) not null,
	sm_recvnick varchar2 (30 char) not null,
	sm_content varchar2(1000 char) not null,
	sm_sendtime date not null
);


create sequence message_seq;
create sequence sendmessage_seq;

select * from message

drop table message cascade constraint purge;