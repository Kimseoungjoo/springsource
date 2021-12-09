select * from spring_board;

insert into spring_board(bno,title,content,writer)
(select seq_board.nextval,title,content,writer from spring_board);

select count(*) from spring_board;


--- 페이지 나누기
--- rownum(가상 행번호)
select rownum, bno from spring_board;

-- rownum 부여시 주의할 점 => order by 저로가 같이 올 떄 (order by 구문에 index 값이 쓰이지 않는 경우)
-- ex) order by re_ref desc, re_lev asc;
-- ex) index(pk 만들면 index 로 생성됨)

-- 서브 쿼리 이용하기
select rownum, bno, title
from (select bno,title from spring_board where bno>0 order by bno desc)
where rownum <= 10;

-- 오라클 힌트 이용
select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum,bno,title
from spring_board
where rownum<=10;
-- 페이지 나누기 
-- 1: 최신글 10개 가지고 나오기
select rn,bno,title
from  (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title
	   from spring_board
   	   where rownum<=10)
where rn>0;
-- 2: 그 다음 최신글 10개 가지고 나오기
select rn,bno,title
from  (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title
	   from spring_board
   	   where rownum<=20)
where rn>10;

-- 검색

-- 제목 /내용 / 작성자 단일항목 검색
select rn,bno,title
from  (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title
	   from spring_board
   	   where  (title like '%되%' or content like '%history%')  and rownum<=20)
where rn>10;
-- 제목 or 내용 / 제목 or 작성자 / 제목 or 내용 or 작성자 다중항목 검색

-- 댓글 테이블 만들기 
create table spring_reply(
	rno number(10,0) constraint pk_reply primary key,						-- 댓글 글번호
	bno number(10,0) not null,												-- 원본 글번호
	reply varchar2(1000) not null,											-- 댓글 내용
	replyer varchar2(50) not null,											-- 댓글 작성자
	replydate date default sysdate,											-- 댓글 작성일
	updatedate date default sysdate,										-- 댓글 수정일
	constraint fk_reply_board foreign key(bno) references spring_board(bno) -- 외래키 설정
);

create sequence seq_reply;

ALTER TABLE spring_reply RENAME COLUMN replydate to replaydate;
select * from SPRING_REPLY;

-- 인덱스 생성
create index idx_reply on spring_reply(bno desc, rno asc);

select rno,bno,reply,replyer,replydate, updatedate
from(select /*+INDEX(spring_reply idx_reply)*/rownum rn,rno,bno,reply,replyer,replydate, updatedate
from spring_reply
where bno=2799 and rno>0 and rownum<=20)
where rn > 10;

-- spring_board 테이블에 댓글 수를 저장할 컬럼 추가 
alter table spring_board add(replycnt number default 0);


-- 이미 들어간 댓글 수 삽입하기 
update SPRING_BOARD
set replycnt = (select count(rno) 
				from SPRING_REPLY 
				where SPRING_BOARD.bno=spring_reply.bno);

select * from spring_board where bno= 2799;

-- 첨부파일 테이블 																					
create table spring_attach(
	uuid varchar2(100) not null,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) default 'I',
	bno number(10,0)
);

alter table spring_attach add constraint pk_attach primary key(uuid);
alter table spring_attach add constraint fk_board_attach foreign key(bno)
references spring_board(bno);

select * from spring_attach;

delete from SPRING_ATTACH where bno=2801;