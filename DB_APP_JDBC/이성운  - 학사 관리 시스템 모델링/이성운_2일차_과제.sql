CREATE DATABASE 이성운_Academy_Management_System;
USE 이성운_Academy_Management_System;

CREATE TABLE Professors(
	Professor_id integer primary key,
    Professor_name varchar(20) not null,
    Professor_gender enum('M','F') not null,
    Professor_tel varchar(20),
    Professor_office_id integer,
    Professor_major varchar(20),
    Professor_email varchar(50) not null,
    Professor_department_id integer
);

CREATE TABLE Students(
	Student_id integer primary key,
    Student_name varchar(20) not null,
    Student_major varchar(20),
    Student_gender enum('M','F') not null,
    Student_tel varchar(20) not null,
    Student_email varchar(50),
    Student_department_id integer
);
CREATE TABLE Student_details(
	Student_id integer primary key,
	Student_parents varchar(20),
    Student_hometown varchar(50),
    Student_birth date,
    Student_birthplace varchar(50)
);
CREATE TABLE Departments(
	Department_id integer,
    Department_name varchar(30),
    Office_id integer,
    Establishment_dt date,
    constraint pk_Department primary key (Department_id)
);
CREATE TABLE Lectures(
	Lecture_id integer unique,
	Professor_id integer,
    Subject_id integer,
    Lecture_room_id integer,
    Lecture_plan text,
    constraint pk_Lecture primary key (Professor_id, Subject_id)
);
CREATE TABLE Lecture_rooms(
	Lecture_room_id integer,
    Building_id integer,
    Lecture_room_location varchar(30),
    constraint pk_LectureRoom primary key (Lecture_room_id,Building_id)
);

CREATE TABLE Offices(
	Office_id integer,
    Building_id integer,
    Office_name varchar(20),
    Office_location varchar(20),
    constraint pk_Office primary key (Office_id, Building_id)
);
CREATE TABLE Subjects(
	Subject_id integer primary key,
    Subject_name varchar(20),
    Subject_credit integer
);
CREATE TABLE Courses(
	Student_id integer,
    Subject_id integer,
    Grade varchar(20),
    WithDraw boolean default false,
    constraint pk_Course primary key (Student_id, Subject_id)
);
CREATE TABLE Advise(
	Professor_id integer,
    Student_id integer,
    Start_period Date,
    End_peroid Date,
    constraint pk_Advise primary key (Student_id, Professor_id)
);
CREATE TABLE Employee(
	Employee_id integer primary key,
    Employee_name varchar(20),
    Employee_gender enum('M','F'),
    Employee_tel varchar(20),
    Employee_email varchar(30)
);
CREATE TABLE Works(
	Office_id integer,
    Employee_id integer primary key
);
CREATE TABLE Buildings(
	Building_id integer,
    Building_name varchar(20),
    Building_location varchar(50),
    constraint pk_Building primary key (Building_id)
);
ALTER TABLE Advise add constraint fk_Advise_Professor foreign key(Professor_id) references Professors(Professor_id) on delete cascade;
ALTER TABLE Advise add constraint fk_Advise_Student foreign key(Student_id) references Students(Student_id) on delete cascade;

ALTER TABLE Lectures add constraint fk_Lecture_Professor foreign key(Professor_id) references Professors(Professor_id) on delete cascade;
ALTER TABLE Lectures add constraint fk_Lecture_Subject foreign key(Subject_id) references Subjects(Subject_id) on delete cascade;
ALTER TABLE Lectures add constraint fk_Lecture_LectureRoom foreign key(Lecture_room_id) references Lecture_rooms(Lecture_room_id) on delete set null;

ALTER TABLE Courses add constraint fk_Course_Student foreign key(Student_id) references Students(Student_id) on delete cascade;
ALTER TABLE Courses add constraint fk_Course_Subject foreign key(Subject_id) references Subjects(Subject_id) on delete cascade;

ALTER TABLE Professors add constraint fk_Professor_Department foreign key(Professor_Department_id) references Departments(Department_id) on delete set null;
ALTER TABLE Professors add constraint fk_Professor_Office foreign key(Professor_Office_id) references Offices(Office_id) on delete set null;

ALTER TABLE Students add constraint fk_Student_Department foreign key(Student_department_id) references Departments(Department_id) on delete set null;

ALTER TABLE Works add constraint fk_Work_Office foreign key(Office_id) references Offices(Office_id) on delete cascade;
ALTER TABLE Works add constraint fk_Work_Employee foreign key(Employee_id) references Employee(Employee_id) on delete cascade;

ALTER TABLE Departments add constraint fk_Department_Office foreign key(Office_id) references Offices(Office_id) on delete set null;

ALTER TABLE Student_details add constraint fk_Student foreign key(Student_id) references Students(Student_id) on delete cascade;

ALTER TABLE Offices add constraint fk_Building foreign key(Building_id) references Buildings(Building_id) on delete cascade;
ALTER TABLE Lecture_rooms add constraint fk_LectureRoom_Building foreign key(Building_id) references Buildings(Building_id) on delete cascade;