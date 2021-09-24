drop table dog;

create table dog(
	id int auto_increment primary key,
	kind varchar(12) not null,
	price int not null,
	image varchar(20) not null,
	country varchar(12) not null,
	height int,
	weight int,
	content varchar(400),
	readcount int
);

insert into dog values(1, '푸들', 1000, 'pu.jpg', '프랑스', 1, 20, '털 많다', 0);
insert into dog values(2, '불독', 1000, 'bul.jpg', '독일', 1, 20, '못생겼다', 0);
insert into dog values(3, '진돗개', 1000, 'jin.jpg', '대한민국', 1, 20, '최고다', 0);
insert into dog values(4, '허스키', 1000, 'h.jpg', '북극', 1, 20, '멋지다', 0);

select * from dog;