create table spring_member(
	userid varchar2(50) not null primary key,
	userpw varchar2(100) not null,
	username varchar2(100) not null,
	regdate date default sysdate,
	updatedate date default sysdate,
	enabled char(1) default '1'
);

create table spring_member_auth(
	userid varchar(50) not null,
	auth varchar2(50) not null,    -- auth 는 userid에 여러개 들어갈 수 있기 때문에 table을 분리한다.
	constraint fk_member_auth foreign key(userid) references spring_member(userid)
);

select * from spring_member;
delete from SPRING_MEMBER_AUTH where auth='관리자'; 

select s1.userid, userpw, username, regdate updatedate, enabled,auth 
from SPRING_meMBER s1 left outer join SPRING_MEMBER_AUTH s2 on s1.userid=s2.userid
where s1.userid='admin90';

insert into spring_member_auth(userid,auth) values('admin90','ROLE_MEMBER');