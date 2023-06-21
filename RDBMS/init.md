## 1일차
릴레이션 스키마가 다음과 같을 때 질문에 답하세요.


직원 (직원번호: 정수, 직원이름: 문자열, 나이: 정수, 급여: 실수)
근무 (직원번호: 정수, 부서번호: 정수, 군무시간: 정수)
부서 (부서번호: 정수, 부서이름: 문장녈, 예산: 실수, 부서장번호: 정수)
부서 릴레이션에 대한 외래 키 제약조건의 예를 설명하세요. 사용자가 부서의 한 투플을 갖게 하려고 할 때 이 제약조건들을 집행하는 방식에는 어떤 것들이 있는지 설명하세요.
위의 릴레이션을 생성하는 SQL 문장을 작성하세요. 기본 키 및 외래 키 제약조건을 모두 명세해야 합니다.
모든 부서에는 부서장이 있어야 하도록 부서 릴레이션을 작성하는 SQL 문을 작성하세요.
모든 직원들이 급여를 10% 인상하는 SQL 질의를 작성하세요.
‘HR’ 부서를 삭제하는 SQL 문을 작성하세요. 참조 무결성 제약조건에 따라 발생할 수 있는 일
들을 설명하세요

정답
릴레이션 스키마가 다음과 같을 때 질문에 답하세요.

직원 (직원번호: 정수, 직원이름: 문자열, 나이: 정수, 급여: 실수)
근무 (직원번호: 정수, 부서번호: 정수, 군무시간: 정수)
부서 (부서번호: 정수, 부서이름: 문장녈, 예산: 실수, 부서장번호: 정수)

부서 릴레이션에 대한 외래 키 제약조건의 예를 설명하세요. 사용자가 부서의 한 투플을 갖게 하려고 할 때 이 제약조건들을 집행하는 방식에는 어떤 것들이 있는지 설명하세요.
부서 릴레이션은 부서 정보를 저장하고, 부서번호를 기본 키로 지정했습니다.
부서 (부서번호: 정수(기본키), 부서이름: 문장열, 예산: 실수, 부서장번호: 정수

직원 릴레이션은 직원의 정보를 저장하기 위해 설계되었으며, 직원 번호를 기본키로 지정했습니다.
직원 (직원번호: 정수(기본키), 직원이름: 문자열, 나이: 정수, 급여: 실수)

부서 릴레이션은 직원의 직원 번호 정보를 부서 릴레이션의 각 투플에 적용하기 위해 직원 릴레이션을 참조할 수 있습니다. 부서 릴레이션은 부서의 부서장번호 분류를 위해 직원 릴레이션의 직원번호 필드를 참조합니다.
부서 (부서번호: 정수(기본키), 부서이름: 문장열, 예산: 실수, 부서장번호*:* 정수*(외래키)*)

근무 릴레이션은 직원의 직원 번호 정보를 근무 릴레이션의 각 투플에 적용하기 위해 직원 릴레이션을 참조할 수 있습니다. 근무 릴레이션은 직원의 직원 번호 분류를 위해 직원 릴레이션의 직원 번호 필드를 참조합니다.
그리고 근무 릴레이션은 부서의 부서 번호 정보를 근무 릴레이션의 각 투플에 적용하기 위해 부서 릴레이션을 참조할 수 있습니다. 근무 릴레이션은 부서의 부서 번호 분류를 위해 부서 릴레이션의 부서 번호 필드를 참조합니다.
근무 (직원번호*:* 정수*(외래키), 부서번호:* 정수*(외래키)*, 근무시간: 정수)

이때 직원 데이터의 무결성을 위해, 직원 릴레이션에 존재하지 않는 데이터는 부서와 근무 릴레이션에 존재하지 않아야 합니다. 만약 직원 릴레이션의 직원 번호에 1번이 없다면, 부서와 근무 릴레이션에는 1번이라는 부서의 부서장번호와 근무의 직원번호는 존재할 수 없어야 합니다. 또한 직원의 직원 번호가 변경되었다면 참조하고 있는 부서의 부서장 번호와 근무의 직원번호도 변경되어야 합니다.
그리고 근무는 부서 데이터의 무결성을 위해, 부서 릴레이션에 존재하지 않는 데이터는 근무 릴레이션에 존재하지 않아야 하며, 부서의 부서번호가 변경되었다면 참조하고 있는 근무의 부서번호도 변경되어야 합니다.

1. 직원 릴레이션에 없는 직원 번호 값을 가진 부서 투플을 갖을 경우
    -직원 릴레이션에 없는 직원 번호 값을 가진 부서는 없기 때문에 찾을 수 없습니다.
 2. 직원 릴레이션에 있는 직원 번호 값을 가진 부서 투플을 갖을 경우
    -직원 릴레이션에 있는 직원 번호 값을 가진 부서는 가지고 있기 때문에 찾을 수 있습니다.
 3. 부서 릴레이션에 있는 부서 번호 값을 가진 경우
    -부서 번호를 통해서 부서 투풀을 찾을 수 있습니다.

2. 위의 릴레이션을 생성하는 SQL 문장을 작성하세요. 기본 키 및 외래 키 제약조건을 모두 명세해야 합니다.
    3. 모든 부서에는 부서장이 있어야 하도록 부서 릴레이션을 작성하는 SQL 문을 작성하세요.
2,3번을 한꺼번에 했습니다.
create database Company;

use Company;

create table Worker(
WorkerNo integer primary key,
WorkerName VARCHAR(30),
Age integer,
Salary float
);
desc Worker;

create table Work(
WorkerNo integer,
DepartmentNo integer,
WorkTime integer
);
desc Work;

create table Department(
DepartmentNo integer primary key,
DepartmentName varchar(20),
Budget integer,
DepartmentHeadNo integer not null
);
desc Department;

alter table Work add constraint fk_work_worker foreign key(WorkerNo) references Worker(WorkerNo);
desc work;

alter table Work add constraint fk_work_department foreign key(DepartmentNo) references Department(DepartmentNo);
desc work;

alter table Department add constraint fk_department_worker foreign key(DepartmentHeadNo) references Worker(WorkerNo);
desc Department;

insert into worker values (1,'Lee',26,3000000);
insert into worker values (2,'Choi',45,5000000);
insert into worker values (3,'Kim',28,3200000);
insert into worker values (4,'Jeong',50,5200000);
insert into worker values (5,'Choo',21,2500000);
select * from worker;

insert into department values(1,'HR',100000000, 2);
insert into department values(2,'AT',150000000, 4);
select * from department;

insert into work values(1,1,2);
insert into work values(3,1,8);
insert into work values(5,1,4);
insert into work values(1,2,3);
insert into work values(5,2,2);
insert into work values(3,2,4);
insert into work values(1,1,3);
insert into work values(5,1,2);
select * from work;

4. 모든 직원들이 급여를 10% 인상하는 SQL 질의를 작성하세요.
set sql_safe_updates=0;
update worker set
Salary = Salary * 1.1;
select * from worker;

5.‘HR’ 부서를 삭제하는 SQL 문을 작성하세요. 참조 무결성 제약조건에 따라 발생할 수 있는 일들을 설명하세요
delete from department Where departmentName = 'HR';
만약 부서와 외래키를 설정할 때,
alter table work add constraint fk_work_department foreign key(DepartmentNo) references Department(DepartmentNo) on delete no action;
로 설정을 하였다면 부서를 삭제할 때 에러가 뜨면서, 삭제가 일어나지 않습니다.
하지만 on delete cascade로 설정을 하였다면 부서를 삭제할 때, 해당 부서를 참조하고 있는 근무의 모든 행도 같이 삭제가 됩니다.
또한 on delete set null로 설정을 하였다면 부서를 삭제할 때, 해당 부서를 참조하고 있는 근무의 모든 행들의 부서 번호가 null로 변경이 될 겁니다.

## 2일차
1. 아래와 같은 릴레이션이 있습니다.


학생(학번: 정수, 학생이름: 문자열, 전공: 문자열, 나이: 정수, 학년: 정수)
과목(과목이름: 문자열, 수업시간: 시간, 강의실: 문자열, 교수번호: 정수)
수강(학번: 정수, 과목이름: 문자열)
교수(교수번호: 정수, 교수이름: 문자열, 학과번호: 정수)


이 릴레이션의 의미는 단순합니다. 예를 들어 수강 릴레이션에는 한 학생이 과목을 수강할 때 마다
학생-과목 쌍에 해당하는 레코드가 하나씩 생깁니다.


다음 질의를 SQL로 작성하십시오. 어느 결과에서는 중복된 값을 출력해서는 안됩니다.


A. 이순신이 가르치는 과목을 수강하고 있는 모든 3학년들의 이름을 구하시오.
B. 역사를 전공하거나 이순신이 가르치는 과목을 수강하고 있는 가장 나이 많은 학생의 나이를 구하시오.
C. 강의실 번호가 1103이거나 다섯 명 이상이 수강하고 있는 모든 과목들의 이름을 구하시오.
D. 같은 시간에 진행되는 두 과목을 수강하고 있는 모든 학생들의 이름을 구하시오.
E. 강의에 이용되는 모든 강의실에서 가르치고 있는 교수들의 이름을 구하시오.
F. 자신이 가르치는 과목을 수강하는 학생의 수가 5가 안되는 교수들의 이름을 구하시오.
G. 등급별로 학생의 평균 나이와 그 등급을 출력하시오.
H. 3학년을 제외한 모든 등급에 대해서 학생들의 평균 나이와 그 등급을 출력하시오.
I. 과목을 최대로 수강하고 있는 학생들의 이름을 구하시오.
J. 어떠한 과목도 수강하고 있지 않은 학생들의 이름을 구하시오.
K. 학생의 나이별로 가장 많은 등급을 구하시오.




2. 아래와 같은 릴레이션이 있습니다.


공급자(공급자번호: 정수, 공급자이름: 문자열, 주소: 문자열)
부품(부품번호: 정수, 부품이름: 문자열, 색상: 문자열)
카탈로그(공급자번호: 정수, 부품번호: 정수, 단가: 실수)


카탈로그 릴레이션은 공급자별 부품 단가를 가지고 있습니다. 다음 질의들을 SQL로 표현하세요.


A. 공급자가 있는 부품들의 이름을 구하시오.
B. 모든 부품을 공급하는 공급자들의 이름을 구하시오.
C. 모든 적색 부품을 공급하는 공급자들의 이름을 구하시오.
D. 홍길동이라는 공급자만 공급하는 부품의 이름을 구하시오.
E. 일부 부품에 대해서 평균 부품 단가보다 많이 받는 공급자들의 이름을 구하시오.
F. 각 부품별로 그 부품에 대해서 가장 비싼 단가를 책정하고 있는 공급자들의 이름을 구하시오.
G. 적색 부품들만 공급하는 공급자들의 이름을 구하시오.
H. 적색 부품 하나와 녹색 부품 하나를 공급하는 공급자들의 이름을 구하시오.
I. 적색 부품 하나 또는 녹색 부품 하나를 공급하는 공급자들의 이름을 구하시오.


3. 관계 스키마는 다음과 같습니다. 직원 한 명이 여러 부서에서 일할 수 있습니다. 근무 릴레이션에서 시간백분을 만드는 필드는 해당 직원이 해당 부서에서 근무하는 시간 백분율을 나타냅니다.


직원(직원번호: 정수, 직원이름: 문자열, 나이: 정수, 급여: 실수)
근무(직원번호: 정수, 부서번호: 정수, 시간백분율: 정수)
부서(부서번호: 정수, 예산: 실수, 부서장번호: 정수)


다음 질의를 SQL로 작성하세요.


A. 하드웨어 부서와 소프트웨어 부서에서 모두 근무하는 직원들의 이름과 나이를 출력하세요.
B. 20명 이상의 풀타임 직원이 있는 각 부서(즉 파트 타임 근무 직원과 풀타임 근무 직원들을 합하여 적어도 그만큼의 풀타임 직원에 해당하는 부서)별로, 부서번호와 그 부서에 근무하는 직원들의 수를 출력하세요.
C. 자신의 근무하는 어느 부서의 예산보다 급여를 많이 받는 직원들의 이름을 출력하세요.
D. 예산에 1억원이 넘는 부서를 관리하는 부서장의 이름을 구하세요
E. 한 부서장이 여러 부서를 관리한다면 그는 결국 부서의 예산 총액을 관리하는 것입니다. 5000 만원 이상을 관장하는 부서장들의 번호를 구하세요.
F. 가장 많은 예산을 관리하는 부서장의 번호를 구하세요.
G. 모든 직원들이 5000만원 이상을 벌 수 있도록 직원 릴레이션에 대한 테이블 제약조건을 정의 하세요.
I. 자신이 근무하는 부서들중에서 어느 한 곳에서라도 부서장보다 급여가 더 많은 직원들에 대한 모든 정보를 삭제하는 SQL문을 작성하세요. 이렇게 한 후에도 관련 무결성 제약조건은 만족되어야 합니다.

정답
1. 아래와 같은 릴레이션이 있습니다.

학생(학번: 정수, 학생이름: 문자열, 전공: 문자열, 나이: 정수, 학년: 정수)
과목(과목이름: 문자열, 수업시간: 시간, 강의실: 문자열, 교수번호: 정수)
수강(학번: 정수, 과목이름: 문자열)
교수(교수번호: 정수, 교수이름: 문자열, 학과번호: 정수)

이 릴레이션의 의미는 단순합니다. 예를 들어 수강 릴레이션에는 한 학생이 과목을 수강할 때 마다
학생-과목 쌍에 해당하는 레코드가 하나씩 생깁니다.

다음 질의를 SQL로 작성하십시오. 어느 결과에서는 중복된 값을 출력해서는 안됩니다.

A. 이순신이 가르치는 과목을 수강하고 있는 모든 3학년들의 이름을 구하시오.
SELECT
    StudentName
From
    Professor as p inner join Subject as s on p.ProfessorNo = s.ProfessorNo
    inner join SignUp as su on su.SubjectName = s.SubjectName
    inner join Student as st on st.StudentNo = su.StudentNo
Where
    ProfessorName = '이순신' and Grade = 3;

B. 역사를 전공하거나 이순신이 가르치는 과목을 수강하고 있는 가장 나이 많은 학생의 나이를 구하시오.
SELECT
    max(Age)
From
    Professor as p inner join Subject as s on p.ProfessorNo = s.ProfessorNo
    inner join SignUp as su on su.SubjectName = s.SubjectName
    inner join Student as st on st.StudentNo = su.StudentNo
Where
    ProfessorName = '이순신' or Major = 'History';

C. 강의실 번호가 1103이거나 다섯 명 이상이 수강하고 있는 모든 과목들의 이름을 구하시오.
Select s.SubjectName, LectureRoom, Count(s.SubjectName)
From
    SignUp as su inner join Subject as s on s.SubjectName = su.SubjectName
Group by s.SubjectName
Having count(s.SubjectName) >= 5 or LectureRoom = '1103';

D. 같은 시간에 진행되는 두 과목을 수강하고 있는 모든 학생들의 이름을 구하시오.
Select
    StudentName
From
    SignUp as su on su.SubjectName = s.SubjectName
    inner join Student as st on st.StudentNo = su.StudentNo
GROUP BY StudentNo, SubjecTime
Having Count(StudentNo) >1
E. 강의에 이용되는 모든 강의실에서 가르치고 있는 교수들의 이름을 구하시오.
Select

F. 자신이 가르치는 과목을 수강하는 학생의 수가 5가 안되는 교수들의 이름을 구하시오.
Select
    ProfessorName
From
    Professor
Where
    ProfessorNo in (
    Select
        ProfessorNo
    From
        Subject as s inner join SignUp as su on s.SubjectName = su.SubjectName
    Group by
        SubjectName
    Having
        count(StudentNo)<5
)

G. 등급별로 학생의 평균 나이와 그 등급을 출력하시오.
Select
    AVG(Age), grade
From
    Student
Group by Grade;

H. 3학년을 제외한 모든 등급에 대해서 학생들의 평균 나이와 그 등급을 출력하시오.
Select
    AVG(Age), grade
From
    Student
Where
    not Grade = 3
Group by Grade;

I. 과목을 최대로 수강하고 있는 학생들의 이름을 구하시오.
Select s.StudentName
From SignUp as su inner join Student as s on su.StudentNo = s.StudentNo
Group by s.StudentNo
Having Count(SubjectName) = (Select max(result)
From (Select s.StudentName, Count(SubjectName) as result
From SignUp as su inner join Student as s on su.StudentNo = s.StudentNo
Group by s.StudentNo) as data)

J. 어떠한 과목도 수강하고 있지 않은 학생들의 이름을 구하시오.
Select StudentName
from Student left outer join SignUp on Student.StudentNo = Signup.StudentNo
where signup.SubjectName is null;

K. 학생의 나이별로 가장 많은 등급을 구하시오.


2. 아래와 같은 릴레이션이 있습니다.

공급자(공급자번호: 정수, 공급자이름: 문자열, 주소: 문자열)
부품(부품번호: 정수, 부품이름: 문자열, 색상: 문자열)
카탈로그(공급자번호: 정수, 부품번호: 정수, 단가: 실수)

카탈로그 릴레이션은 공급자별 부품 단가를 가지고 있습니다. 다음 질의들을 SQL로 표현하세요.

A. 공급자가 있는 부품들의 이름을 구하시오.
Select
    ComponentName
From
   Component as c Left Outer join Catelogue as cl on c.ComponentNo = cl.ComponentNo
Where
    Not ProduceNo = null;

B. 모든 부품을 공급하는 공급자들의 이름을 구하시오.
Select
    ProduceName
From
    Produce as p inner join Catelogue as c on p.ProduceNo = c.ProduceNo
Group by ProduceNo
Having count(ComponentNo) = (
    Select count(ComponentNo)
    From Component
    );

C. 모든 적색 부품을 공급하는 공급자들의 이름을 구하시오.
Select
    ProduceName
From
    Produce as p inner join Catelogue as c on p.ProduceNo = c.ProduceNo
Group by ProduceNo
Having count(ComponentNo) = (
    Select count(ComponentNo)
    From Component
    Where Color = '빨강'
    );

D. 홍길동이라는 공급자만 공급하는 부품의 이름을 구하시오.
Select ComponentName
From
    Produce as p inner join Catelogue as cl on p.ProduceNo = cl.ProduceNo
    inner join Component as c on c.ComponentNo = cl.ComponentNo
Where ProduceName = '홍길동'
Group by c.ComponentNo
having Count(c.ComponentNo) =1;

E. 일부 부품에 대해서 평균 부품 단가보다 많이 받는 공급자들의 이름을 구하시오.
공급자별 단가를 정하고 있지 않아서 알 수 없음

F. 각 부품별로 그 부품에 대해서 가장 비싼 단가를 책정하고 있는 공급자들의 이름을 구하시오.
공급자별 단가를 정하고 있지 않아서 알 수 없음

G. 적색 부품들만 공급하는 공급자들의 이름을 구하시오.

H. 적색 부품 하나와 녹색 부품 하나를 공급하는 공급자들의 이름을 구하시오.
Select
    ProduceName
From
    ((Select
        ProduceName, count(Color) as red
    From
        Produce as p inner join Catalogue as cl on p.ProduceNo = cl.ProduceNo
        inner join Compoenent as c on cl.ComponentNo = c.ComponentNo
    Where
        Color = '적')
    and
    (Select
        ProduceName , count(Color) as green
    From
        Produce as p inner join Catalogue as cl on p.ProduceNo = cl.ProduceNo
        inner join Compoenent as c on cl.ComponentNo = c.ComponentNo
    Where
        Color = '녹'))
Group by ProduceName
Having green = 1 and red = 1;

I. 적색 부품 하나 또는 녹색 부품 하나를 공급하는 공급자들의 이름을 구하시오.
Select
    ProduceName
From
    ((Select
        ProduceName, count(Color) as red
    From
        Produce as p inner join Catalogue as cl on p.ProduceNo = cl.ProduceNo
        inner join Compoenent as c on cl.ComponentNo = c.ComponentNo
    Where
        Color = '적')
    and
    (Select
        ProduceName , count(Color) as green
    From
        Produce as p inner join Catalogue as cl on p.ProduceNo = cl.ProduceNo
        inner join Compoenent as c on cl.ComponentNo = c.ComponentNo
    Where
        Color = '녹'))
Group by ProduceName
Having green = 1 or red = 1;

3. 관계 스키마는 다음과 같습니다. 직원 한 명이 여러 부서에서 일할 수 있습니다. 근무 릴레이션에서 시간백분을 만드는 필드는 해당 직원이 해당 부서에서 근무하는 시간 백분율을 나타냅니다.

직원(직원번호: 정수, 직원이름: 문자열, 나이: 정수, 급여: 실수)
근무(직원번호: 정수, 부서번호: 정수, 시간백분율: 정수)
부서(부서번호: 정수, 예산: 실수, 부서장번호: 정수)

다음 질의를 SQL로 작성하세요.

A. 하드웨어 부서와 소프트웨어 부서에서 모두 근무하는 직원들의 이름과 나이를 출력하세요.
Select
    EmployeeName, age
From
    Employee as e inner join Work as w on e.EmployeeNo = w.EmployeeNo
Where
    w.departmentNo = 1 and w.departmentNo =2;

B. 20명 이상의 풀타임 직원이 있는 각 부서(즉 파트 타임 근무 직원과 풀타임 근무 직원들을 합하여 적어도 그만큼의 풀타임 직원에 해당하는 부서)별로, 부서번호와 그 부서에 근무하는 직원들의 수를 출력하세요.
Select
    DepartmentNo, count(EmployeeNo)
From
    Work as w inner join Department as d on w.DepartmentNo = d.DepartmentNo
Where count(EmployeeNo)>=20
Group by DepartmentNo
Having AVG(WorkTime) = 100

C. 자신의 근무하는 어느 부서의 예산보다 급여를 많이 받는 직원들의 이름을 출력하세요.
Select
    EmployeeName
From
    Emplyoee as e inner join Work as w on e.EmployeeNo = w.EmployeeNo
    inner join Department as d on d.DepartmentNo = w.DepartmentNo
Where
    DepartmentBudget < Salary;

D. 예산에 1억원이 넘는 부서를 관리하는 부서장의 이름을 구하세요
Select EmployeeName
From Employee
Where EmployeeNo = (
    Selelct
        DepartmentHeadNo
    From
        Department
    Where
        DepartmentBudget > 100000000);

E. 한 부서장이 여러 부서를 관리한다면 그는 결국 부서의 예산 총액을 관리하는 것입니다. 5000 만원 이상을 관장하는 부서장들의 번호를 구하세요.
Select DepartmentHeadNo
from Department
Group by DepartmentHeadNo
Having Sum(DepartmentBudget) > 50000000;

F. 가장 많은 예산을 관리하는 부서장의 번호를 구하세요.
Select DepartmentHeadNo
from Department
Group by DepartmentHeadNo
Having Sum(DepartmentBudget) = (Select max(result)
from ( Select DepartmentHeadNo , SUM(DepartmentBudget) as result
            from Dapartment
            Group by DepartmentHeadNo )as data
);
G. 모든 직원들이 5000만원 이상을 벌 수 있도록 직원 릴레이션에 대한 테이블 제약조건을 정의 하세요.
Alter table Employee add constraint Salary_Up_5000 check Salary >= 50000000;
I. 자신이 근무하는 부서들중에서 어느 한 곳에서라도 부서장보다 급여가 더 많은 직원들에 대한 모든 정보를 삭제하는 SQL문을 작성하세요. 이렇게 한 후에도 관련 무결성 제약조건은 만족되어야 합니다
## 3일차
첨부 파일은  영화 데이터베이스의 mysql 백업 파일입니다.
mysql을 실행하고, 아래 질의를 실행해서 DatamotionMovieDatabase 데이터베이스를 생성하세요.


mysql> CREATE DATABASE DatamotionMovieDatabase;


데이터베이스가 생성되면 아래 명령을 실행해서 mysql 클라이언트를 종료합니다.


mysql> exit


아래 명령을 수행하여 백업 받은 파일을 DatamotionMovieDatabase 데이터베이스에 복원합니다.


$ mysql -u root -p DatamotionMovieDatabase < DMMDB-20221215.sql


복원이 완료되면, mysql에 접근합니다.


$ mysql -u root -p
******


아래 명령을 실행하여 복원된 데이터베이스를 확인합니다.


mysql> show databases;
mysql> use datamotionmoviedatabase;
mysql> show tables;


복원한 DatamotionMovieDatabase에서, 아래 질의들을 SQL Query 식으로 작성하세요.


1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
3. 영화 '글래디에이터'의 작곡가를 고르세요
4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
5. 감독이 2명 이상인 영화를 출력하세요
6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요
8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
11. 가장 많은 제작비가 투입된 영화를 감독한 사람은 누구입니까?
12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
16. 액션 장르 영화의 평균 수익을 집계하세요.
17. 드라마, 전쟁 장르의 영화를 고르세요.
18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
20. 아카데미상을 가장 많이 수상한 영화인을 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
25. 가장 많은 영화에 출연한 여배우를 고르세요.
26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
29. 드라마에 가장 많이 출연한 사람을 고르세요.
30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
34. 아카데미에 가장 많이 노미네이트된 사람은 누구인가요?
35. 1999년에서 2009년 사이에 남우 주연상을 수상한 배우를 모두 고르세요.
36. 아카데미를 한번 수상한 후, 가장 짧은 기간에 한번 더 수상한 사람을 고르세요.
37. '제임스 카메론'의 영화에 출연한 모든 배우를 고르세요
38. '월드 디즈니'가 수상한 아카데미상의 종류를 고르세요
39. 장르별 영화의 제작비 평균을 구하세요.
40. 장르별 영화의 제작비 대비 수익률을 구하세요.
## 4일차
지난 과제에서 사용했던 DatamotionMovieDatabase 데이터베이스에서 아래 질의를 작성합니다.


1. DBManager 라는 이름을 가지는 사용자를 작성하세요.
2. User 라는 이름을 가지는 사용자를 작성하세요.
3. DBManger 사용자는 DatamotionMovieDatabase의 모든 개체에 모든 권한을 가지되, 다른 데이터베이스에 대한 권한은 가지지 않아야 합니다.
4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
5. '영화'에 출연한 '배우'와 관련된 뷰를 작성하세요.
6. '영화'를 감독한 '감독'과 관련된 뷰를 작성하세요.
7. '아카데미 시상 결과'과 관련된 뷰를 작성하세요.
8. '영화 장르별 제작비와 흥행 수익'에 관련된 뷰를 작성하세요.
9. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
10. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 출력하세요.
11. '알란 실버스트리'가 음악을 작곡한 영화와 '애런 소킨'이 각본을 쓴 영화를 출력하세요. (애런 소킨 데이터를 찾아보세요)
12. '쿠엔틴 타란티노'가 감독한 영화에 출연한 배우들이 출연한 영화의 수익율을 배우별로 출력하세요.
13. 위 문제들 중, 생성한 뷰에서 질의가 어려운 쿼리에 대한 뷰를 작성하세요.
14. 새로 생성한 뷰를 사용해서 쿼리를 다시 작성하세요.


14. 사용자 테이블을 작성하세요. 사용자 테이블은 사용자의 ID, 이름, 패스워드, 전자메일 주소를 필드로 가집니다.
15. 사용자가  MovieTrivia 테이블과 PersonTrivia 테이블에 투플을 삽입할 수 있고, 각 투플에 삽입한 사용자를 식별할 수 있는 정보가 포함되어야 할 때, 두 테이블의 릴레이션 스키마를 수정하세요.
16. 수정된 테이블 두 테이블(MovieTrivia, PersonTrivia)과 사용자 테이블 사이의 참조 무결성을 위한 제약조건을 설정하세요.
17. User 사용자가 두 테이블(MovieTrivia, PersonTrivia)에 데이터를 삽입할 수 있도록 권한을 설정하세요.
18. MovieTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
19. PersonTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
20. 주어진 감독이 감독한 영화를 모두 출력하는 저장 프로시저를 작성하세요.
21. 주어진 영화에 출연한 배우를 모두 출력하는 저장 프로시저를 작성하세요.
22. 주어진 영화에 참여한, 감독, 각본, 배우를 제외한 모든 사람을 출력하는 저장 프로시저를 작성하세요.
23. 주어진 사람이 '참여'한 영화를 출력하는 저장 프로시저를 작성하세요.
24. 주어진 장르에 대한 제작비, 전체 수익과 수익율을 출력하는 저장 프로시저를 작성하세요.


<수업하지 않은 내용>
25. 저장 프로시저의 파라미터는 출력/입력/입출력 형태의 파라미터를 가질 수 있습니다. 주어진 영화(MovieID)로 출연/참여자 정보를 제외한 모든 정보를 출력 파라미터로 가지고, 실행 결과가 출력 파라미터에 기록되도록 하는 저장 프로시저를 작성하세요.
