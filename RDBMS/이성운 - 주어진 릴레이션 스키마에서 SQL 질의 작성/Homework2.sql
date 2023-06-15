Create database homework2;

use homework2;

create table Produce(
ProduceNo integer primary key,
ProduceName varchar(20) not null,
Age integer,
Salary float
);

create table Component(
ComponentNo integer primary key,
ComponentName varchar(20) not null,
Color varchar(10) not null
);
drop table Catelogue;
create table Catelogue(
ProduceNo integer,
ComponentNo integer,
Price float,

Constraint pk_ProduceNo_ComponentNo primary key(ProduceNo, ComponentNo)
);

alter table Catelogue add Constraint fk_Catelogue_Component foreign key(ComponentNo) references Component(ComponentNo);

alter table Catelogue add Constraint fk_Catelogue_Produce foreign key(ProduceNo) references Produce(ProduceNo);


use Homework2;
insert into Produce values (1,'Lee',20,3000000);
insert into Produce values (2,'Choi',20,3200000);
insert into Produce values (3,'Kang',20,2800000);
insert into Produce values (4,'Choo',20,3100000);
insert into Produce values (5,'Nam',20,2850000);
insert into Produce values (6,'Kim',20,2960000);
insert into Produce values (7,'Ko',20,2700000);
insert into Produce values (8,'Geom',20,3130000);
insert into Produce values (9,'Gyu',20,3240000);
insert into Produce values (10,'Park',20,3100000);
insert into Produce values (11,'Im',20,4000000);
insert into Produce values (12,'홍길동',20,4000000);

insert into Component values (1,'a','적');
insert into Component values (2,'b','적');
insert into Component values (3,'c','녹');
insert into Component values (4,'d','적');
insert into Component values (5,'e','녹');
insert into Component values (6,'f','녹');
insert into Component values (7,'g','녹');
insert into Component values (8,'h','녹');
insert into Catelogue values (1,7,10000);
insert into Catelogue values (1,8,7000);
insert into Catelogue values (12,7,8000);


insert into Catelogue values (1,1,1000);
insert into Catelogue values (1,2,2000);
insert into Catelogue values (1,3,3000);
insert into Catelogue values (1,4,5000);
insert into Catelogue values (1,5,4000);
insert into Catelogue values (1,6,6000);
insert into Catelogue values (2,1,7000);
insert into Catelogue values (2,3,8000);
insert into Catelogue values (3,1,9000);
insert into Catelogue values (4,3,6500);
insert into Catelogue values (5,1,5500);

-- a
Select
    distinct c.ComponentName
From
   Component as c Left Outer join Catelogue as cl on c.ComponentNo = cl.ComponentNo
Where ProduceNo is not null;
-- b
Select
    ProduceName
From
    Produce as p inner join Catelogue as c on p.ProduceNo = c.ProduceNo
Group by c.ProduceNo
Having count(ComponentNo) = (
    Select count(ComponentNo)
    From Component
    );
-- C
Select p.ProduceNo
from Catelogue as cl inner join Component as c on c.ComponentNo = cl.ComponentNo
inner join produce as p on p.ProduceNo = cl.ProduceNo
Where Color = '적'
group by p.ProduceNo
Having count(*) = (
	select count(Color)
    from Component
    Where Color = '적'
);
-- D
Select ComponentName
From
    Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
    inner join Component as c on c.ComponentNo = cl.ComponentNo
Where ProduceName = '홍길동'
Group by c.ComponentNo
having Count(c.ComponentNo) =1;
-- E
Select ProduceName
from Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
Where cl.price > any (
    Select Avg(price)
	from Catelogue
	group by ComponentNo
);
-- F
Select ComponentNo, ProduceName
from Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
Where cl.price in (
    Select Max(price)
    from Catelogue
    group by ComponentNo
);
-- G
Select *
From Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
    inner join Component as c on c.ComponentNo = cl.ComponentNo
Where p.ProduceNo not in (
	Select p.ProduceNo
	From Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
		inner join Component as c on c.ComponentNo = cl.ComponentNo
	Where color ='녹'
);
-- H
Select distinct p.ProduceName
From Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
    inner join Component as c on c.ComponentNo = cl.ComponentNo
Where Color = '녹' and p.ProduceNo in (
	Select p.ProduceNo
	From Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
		inner join Component as c on c.ComponentNo = cl.ComponentNo
	Where color ='적'
);
-- I
Select distinct p.ProduceName
From Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
    inner join Component as c on c.ComponentNo = cl.ComponentNo
Where Color = '녹' or Color = '적';