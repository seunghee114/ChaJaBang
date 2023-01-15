use ssafy_spring;

create table `user`(
	`userid` VARCHAR(16) NOT NULL,
	`username` VARCHAR(20) NOT NULL,
	`userpwd` VARCHAR(16) NOT NULL,
	`email` VARCHAR(20) NULL DEFAULT NULL,
	`joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`token` VARCHAR(1000) NULL DEFAULT NULL,
	PRIMARY KEY (`userid`)
);

insert into `user` (userid, username, userpwd, email) values('ariel', '이서은', 'asdf', 'aaaa@ssafy.com');
update `user` set userpwd="asdfasdf";
create table board(
	bno int auto_increment primary key,
    title varchar(100) not null,
    content varchar(200)  not null,
    writer varchar(16),
    write_date datetime default now(),
    read_count int default 0,
    foreign key (writer) references `user`(userid)
);
insert into board (title, content, writer) values("안녕하세요.", "사직동 어떤가요?", "ariel");
create table `comment`(
	cno int auto_increment primary key,
    bno int,
    ccontent varchar(200) not null,
    cwriter varchar(16),
    cwrite_date datetime default now(),
    foreign key (bno) references board(bno),
    foreign key (cwriter) references `user`(userid)
);

create table notice (
	nno int primary key auto_increment,
    ntitle varchar(100) not null,
    ncontent varchar(200) not null,
    nwrite_date datetime default now()
);

INSERT 
INTO NOTICE (NTITLE, NCONTENT) 
VALUES("비회원 이용 안내", "");

DELETE FROM NOTICE WHERE NNO = 0;

UPDATE NOTICE SET NTITLE = "", NCONTENT = "";

-- SELECT ALL
SELECT NNO, NTITLE, NCONTENT, NWRITE_DATE FROM NOTICE 
ORDER BY NNO DESC;

-- SELECT FOUR
SELECT NNO, NTITLE, NCONTENT, NWRITE_DATE FROM NOTICE 
ORDER BY NNO DESC
LIMIT 4;

-- SELECT ONE
SELECT NNO, NTITLE, NCONTENT, NWRITE_DATE FROM NOTICE
WHERE NNO = 0;

drop table interest;
create table interest(
	ino int primary key auto_increment,
    aptcode bigint,
    userid varchar(16),
    foreign key(aptcode) references houseinfo(aptCode),
    foreign key (userid) references `user`(userid) 
);
select * from interest;
select * from qna;
INSERT INTO INTEREST (APTCODE, USERID)
VALUES ("", "");

DELETE FROM INTEREST WHERE INO = 0;
select * from interest;
-- SELECT USER
SELECT APTCODE, USERID FROM INTEREST WHERE USERID = "";

-- 해당 아파트를 좋아요한 사람 수
SELECT COUNT(*) FROM INTEREST WHERE APTCODE = "";

create table qna(
	qno int primary key auto_increment,
    qtitle varchar(100) not null,
    qcontent varchar(200) not null,
    qwriter varchar(16) not null,
    qwrite_date datetime default now(),
    acontent varchar(200),
    awrite_date datetime,
    is_answered boolean default(0),
    is_checked boolean default(1),
	foreign key (qwriter) references `user`(userid)
);

	UPDATE QNA SET IS_CHECKED=1 WHERE QNO = 2;

select * from qna;
select * from interest;
INSERT INTO QNA (QTITLE, QCONTENT, QWRITER)
VALUES( "00아파트 관련 문의", "안녕하세요. 00 아파트 매물이 별로 없는 것 같은데 정보가 누락된 것 아닌지 궁금합니다.", "ariel");

DELETE FROM QNA WHERE QNO = 0;

-- USER
UPDATE QNA SET QTITLE = "", QCONTENT ="" WHERE QNO = 0;

-- MANAGER
UPDATE QNA SET ACONTENT="" WHERE QNO=0;

-- USER SELECT ALL
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
WHERE QWRITER = ""
ORDER BY QNO DESC;

-- USER SELECT 완료된 문의 중 확인 안한 문의
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
WHERE QWRITER = "" AND IS_ANSWERED = TRUE AND IS_CHECKED = FALSE
ORDER BY QNO DESC;

-- USER SELECT 완료된 문의
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
WHERE QWRITER = "" AND IS_ANSWERED = TRUE
ORDER BY QNO DESC;

-- USER SELECT 미완료된 문의
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
WHERE QWRITER = "" AND IS_ANSWERED = FALSE
ORDER BY QNO DESC;

-- manager select All
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
ORDER BY QNO DESC;

-- MANAGER 미완료 문의
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
WHERE IS_ANSWERED = FALSE
ORDER BY QNO;

-- select one
SELECT QNO, QTITLE, QCONTENT, QWRITER, QWRITE_DATE, ACONTENT, AWRITE_DATE, IS_ANSWERD, IS_CHECKED
FROM QNA
WHERE QNO = "";

select dealYear, count(*) from housedeal
group by dealYear;
