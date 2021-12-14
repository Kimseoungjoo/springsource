create table sp_user(
	userid varchar2(50),
	email varchar2(100) not null,
	enabled char(1) default '1',
	password varchar2(100) not null
);

alter table sp_user add constraint sp_user_pk primary key(userid);

create table sp_user_authority(
	userid varchar2(50) not null,
	authority varchar2(50) not null
);
drop table sp_user_autority;
select * from sp_user_authority;
select * from sp_user;
-- 외래키 제약 조건 생성
alter table sp_user_authority add constraint sp_user_authority_fk
foreign key(userid) references sp_user(userid);

insert into sp_user(userid,email,password) values('1','user@test.com','1111');
insert into sp_user_authority(userid,authority) values('1','ROLE_USER');
insert into sp_user_authority(userid,authority) values('1','ROLE_ADMIN');

select s1.userid,email,password,authority from sp_user s1 join sp_user_authority s2 on s1.userid=s2.userid where s1.userid='1';

--remember-me 를 위한 table
create table persistent_logins(
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

