drop database homework1;
create database homework1;

use homework1;

create table Student(
StudentNo integer primary key,
StudentName varchar(20) not null,
Major varchar(20) not null,
Age integer default 0 ,
Grade integer default 1
);

create table Subject(
SubjectName varchar(20) primary key,
SubjectHour time not null,
LectureRoom varchar(20) not null,
ProfessorNo integer 
);

create table SignUp(
StudentNo integer ,
SubjectName varchar(20),
Constraint pk_StudentNo_SubjectName primary key(StudentNo, SubjectName)
);

create table Professor(
ProfessorNo integer primary key,
ProfessorName varchar(20) not null,
MajorNo integer not null
);

alter table SignUp add constraint fk_SignUp_Student foreign key(StudentNo) references Student(StudentNo);

alter table SignUp add constraint fk_SignUp_Subject foreign key(SubjectName) references Subject(SubjectName);

alter table Subject add constraint fk_Subject_Professor foreign key(ProfessorNo) references Professor(ProfessorNo);

Insert into Student values (1,'Lee','IT',20,1);
Insert into Student values (2,'Choi','History',20,1);
Insert into Student values (3,'Kim','History',26,4);
Insert into Student values (4,'Choo','IT',22,3);
Insert into Student values (5,'Go','Science',22,3);
Insert into Student values (6,'Gang','Electric',20,1);
Insert into Student values (7,'Im','Architecture',21,2);
Insert into Student values (8,'Gong','Science',21,2);
Insert into Student values (9,'Jung','Architecture',23,4);
Insert into Student values (10,'Jang','Electric',25,3);
Insert into Student values (11,'Jang','Electric',25,3);
Insert into Student values (12,'Jang','Electric',25,4);

Insert into Professor values (1,'Lee',1);
Insert into Professor values (2,'Choi',2);
Insert into Professor values (3,'Kim',3);
Insert into Professor values (4,'Gang',4);
Insert into Professor values (5,'Jung',5);
Insert into Professor values (6,'이순신',6);

insert into Subject values ('a','16:30','1103',1);
insert into Subject values ('b','15:30','1102',1);
insert into Subject values ('f','15:30','1101',1);
insert into Subject values ('c','14:30','1101',6);
insert into Subject values ('d','14:30','1102',6);
insert into Subject values ('e','14:30','1103',6);

insert into SignUp values(1,'a');
insert into SignUp values(1,'b');
insert into SignUp values(1,'c');
insert into SignUp values(2,'c');
insert into SignUp values(3,'c');
insert into SignUp values(4,'c');
insert into SignUp values(5,'c');
insert into SignUp values(6,'c');
insert into SignUp values(7,'a');
insert into SignUp values(8,'a');
insert into SignUp values(9,'a');
insert into SignUp values(10,'a');
insert into SignUp values(4,'a');
insert into SignUp values(5,'a');
insert into SignUp values(6,'a');
insert into SignUp values(7,'b');


-- a
SELECT
    StudentName
From
    Professor as p inner join Subject as s on p.ProfessorNo = s.ProfessorNo
    inner join SignUp as su on su.SubjectName = s.SubjectName
    inner join Student as st on st.StudentNo = su.StudentNo
Where
    ProfessorName = '이순신' and Grade = 3;
-- b
SELECT
    max(Age)
From
    Professor as p inner join Subject as s on p.ProfessorNo = s.ProfessorNo
    inner join SignUp as su on su.SubjectName = s.SubjectName
    inner join Student as st on st.StudentNo = su.StudentNo
Where
    ProfessorName = '이순신' or Major = 'History';
-- c
Select s.SubjectName, LectureRoom, Count(s.SubjectName)
From
    SignUp as su inner join Subject as s on s.SubjectName = su.SubjectName
Group by s.SubjectName
Having count(s.SubjectName) >= 5 or LectureRoom = '1103';
-- d
Select
    st.StudentName
From
	SignUp as su inner join Subject as s on su.SubjectName = s.SubjectName
	inner join Student as st on st.StudentNo = su.StudentNo
	GROUP BY st.StudentNo
Having Count(subjectHour) >1;
-- e
Select ProfessorName, Count(LectureRoom)
From Subject as s inner join Professor as p on s.ProfessorNo = p.ProfessorNo
Group by p.ProfessorNo
having Count(LectureRoom) = (Select count(count)
from (Select LectureRoom as count
From Subject
group by LectureRoom) as data
);
-- f
Select ProfessorName
From Professor
Where
    ProfessorNo in (
    Select ProfessorNo
    From Subject as s inner join SignUp as su on s.SubjectName = su.SubjectName
    Group by s.SubjectName
    Having count(StudentNo)<5
);
-- g
Select
    AVG(Age), grade
From
    Student
Group by Grade;
-- h
Select
    AVG(Age), grade
From
    Student
Where
    not Grade = 3
Group by Grade;
-- i
Select s.StudentName
From SignUp as su inner join Student as s on su.StudentNo = s.StudentNo
Group by s.StudentNo
Having Count(SubjectName) = (Select max(result)
From (Select s.StudentName, Count(SubjectName) as result
From SignUp as su inner join Student as s on su.StudentNo = s.StudentNo
Group by s.StudentNo) as data);
-- j
Select StudentName
from Student left outer join SignUp on Student.StudentNo = Signup.StudentNo
where signup.SubjectName is null;
-- k
Select age, grade
from student
group by age, grade
Having count(*) = 
		(Select max(result)
		from(Select age, grade, count(*) as result
			from student
            Group by age, grade) as data
		Where data.age = Student.age
);