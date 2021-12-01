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













