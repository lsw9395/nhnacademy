create database homework3;
drop database homework3;
use homework3;

create table Employee(
EmployeeNo integer primary key,
EmployeeName varchar(20) not null,
Age integer,
Salary float
);
drop table employee;
create table Work(
EmployeeNo integer,
DepartmentNo integer,
WorkTime integer
);
drop table work;
create table Department(
DepartmentNo integer primary key,
DepartmentBudget float,
DepartmentHeadNo integer
);
drop table department;
Alter table Department add Constraint fk_Department_Employee foreign key(DepartmentHeadNo) references Employee(EmployeeNo) on delete cascade;
Alter table Work add Constraint fk_Work_Employee foreign key(EmployeeNo) references Employee(EmployeeNo) on delete set null;
Alter table Work add Constraint fk_Work_Department foreign key(DepartmentNo) references Department(DepartmentNo) on delete set null;

insert into employee values (1,'Lee',21,3000);
insert into employee values (2,'Choi',25,4000);
insert into employee values (3,'Kim',28,4500);
insert into employee values (4,'Gang',29,4700);
insert into employee values (5,'Go',39,5000);
insert into employee values (6,'Choo',22,3500);
insert into employee values (7,'Nam',23,3600);
insert into employee values (8,'Woo',42,6000);
insert into employee values (9,'Joo',40,5500);
insert into employee values (10,'Im',30,4900);
select * from employee;
insert into Department values (1,10000,8);
insert into Department values (2,5000,9);
insert into Department values (3,2000,10);
insert into Department values (4,2500,10);
insert into Department values (5,6000,5);
select * from department;
insert into work values (1,1,4);
insert into work values (1,2,3);
insert into work values (2,3,2);
insert into work values (2,4,5);
insert into work values (3,1,4);
insert into work values (4,5,10);
insert into work values (4,1,7);
insert into work values (5,1,6);
insert into work values (6,1,9);
insert into work values (7,1,8);
insert into work values (1,3,3);
insert into work values (2,5,5);
insert into work values (5,2,5);
insert into work values (9,1,6);
insert into work values (10,1,2);
insert into work values (10,1,3);
insert into work values (9,4,7);
insert into work values (8,1,9);
insert into work values (6,3,11);
insert into work values (1,5,12);
-- A. 하드웨어 부서와 소프트웨어 부서에서 모두 근무하는 직원들의 이름과 나이를 출력하세요.
Select EmployeeName, age
From Employee as e inner join work as w on e.EmployeeNo = w.employeeNo
Where DepartmentNo = 1 and e.EmployeeNo = any (
	Select e.EmployeeNo
    From Employee as e inner join work as w on e.EmployeeNo = w.employeeNo
    Where DepartmentNo = 2
);
-- B. 20명 이상의 풀타임 직원이 있는 각 부서(즉 파트 타임 근무 직원과 풀타임 근무 직원들을 합하여 적어도 그만큼의 풀타임 직원에 해당하는 부서)별로, 부서번호와 그 부서에 근무하는 직원들의 수를 출력하세요.
-- 구할 수 없습니다.
-- C. 자신의 근무하는 어느 부서의 예산보다 급여를 많이 받는 직원들의 이름을 출력하세요.
Select
    EmployeeName
From
    Employee as e inner join Work as w on e.EmployeeNo = w.EmployeeNo
    inner join Department as d on d.DepartmentNo = w.DepartmentNo
Where
    DepartmentBudget < Salary;
-- D. 예산에 1억원이 넘는 부서를 관리하는 부서장의 이름을 구하세요
Select EmployeeName
From Employee
Where EmployeeNo = (
    Select
        DepartmentHeadNo
    From
        Department
    Where
        DepartmentBudget >= 10000);
-- E. 한 부서장이 여러 부서를 관리한다면 그는 결국 부서의 예산 총액을 관리하는 것입니다. 5000 만원 이상을 관장하는 부서장들의 번호를 구하세요.
Select DepartmentHeadNo
from Department
Group by DepartmentHeadNo
Having Sum(DepartmentBudget) > 5000;
-- F. 가장 많은 예산을 관리하는 부서장의 번호를 구하세요.
Select DepartmentHeadNo
from Department
Group by DepartmentHeadNo
Having Sum(DepartmentBudget) = (Select max(result)
from ( Select DepartmentHeadNo , SUM(DepartmentBudget) as result
            from Department
            Group by DepartmentHeadNo )as data
);
-- G. 모든 직원들이 5000만원 이상을 벌 수 있도록 직원 릴레이션에 대한 테이블 제약조건을 정의 하세요.
Alter table Employee add constraint Salary_Up_5000 check (Employee.Salary >= 50000);
-- I. 자신이 근무하는 부서들중에서 어느 한 곳에서라도 부서장보다 급여가 더 많은 직원들에 대한 모든 정보를 삭제하는 SQL문을 작성하세요. 이렇게 한 후에도 관련 무결성 제약조건은 만족되어야 합니다.
Delete from employee
	Where EmployeeNo = (
		Select EmployeeNo
        from
			Employee as e inner join Work as w on e.EmployeeNo = w.EmployeeNo
			inner join Department as d on d.DepartmentNo = w.DepartmentNo
		Group by EmployeeNo, department
        having Salary = (
        Select Salary
        From Employee as e inner join Department as d on d.departmentHeadNo = e.EmployeeNo
));
