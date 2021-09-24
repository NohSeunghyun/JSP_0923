drop table dog;

create table dog(
	id number primary key,--상품아이디 auto_increment는 mySql에서만 사용가능
	kind nvarchar2(20) not null,--개품종 nvarchar2 : 한글은 1개에 3바이트이나 nvarchar2로하면 한글로 20자리까지 들어감
	country nvarchar2(20) not null,--원산지
	price number not null,--개 가격
	height number,--평균 개 신장
	weight number,--평균 개 몸무게
	content nvarchar2(400),--개 설명
	image varchar2(40) not null,--개 이미지
	readcount number--조회수
);

drop sequence dog_seq;

create sequence dog_seq;

insert into dog values(dog_seq.nextval, '푸들', '프랑스', 1000, 1, 20, '털 많다', 'pu.jpg', 0);
insert into dog values(dog_seq.nextval, '불독', '독일', 2000, 1, 20, '못생겼다', 'bul.jpg', 0);
insert into dog values(dog_seq.nextval, '진돗개', '대한민국', 3000, 1, 20, '최고다', 'jin.jpg', 0);
insert into dog values(dog_seq.nextval, '허스키', '북극', 4000, 1, 20, '멋지다', 'h.jpg', 0);

commit

select * from dog;