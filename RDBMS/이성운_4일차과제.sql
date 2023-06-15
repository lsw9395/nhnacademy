use DatamotionMovieDatabase;
-- 1
Select ReleaseYear, Title, RunningTime, Plot
From Movie
Where KoreanTitle = '퍼스트 맨';
-- 2
Select KoreanTitle, Title
From Movie
Where ReleaseYear = 2003;
-- 3
Select p.name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where KoreanTitle = '글래디에이터' and RoleName = 'Composer';
-- 4
Select count(*)
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where KoreanTitle = '매트릭스' and RoleKorName = '감독';
-- 5
Select title
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where RoleKorName = '감독'
Group by m.MovieId
Having count(RoleKorName) >=2;
-- 6
Select title, KoreanTitle
From Movie as m inner join appear as a on m.MovieID = a.MovieID
inner join person as p on p.PersonId = a.PersonId
inner join awardinvolve as aw on aw.AppearId = a.AppearId
Where KoreanName = '한스 짐머' and WinningID = 2;
-- 7
Select data1.title
From
(Select title, KoreanName
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where RoleKorName = '감독' and KoreanName = '제임스 카메론') as data1
inner join
(Select title, KoreanName
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where KoreanName = '아놀드 슈워제네거') as data2 on data1.title = data2.title;
-- 8
Select title
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where RoleKorName = '배우' and KoreanName = '레오나르도 디카프리오' and RunningTime >=100;
-- 9
Select Title
from Movie as m inner join GradeInKorea as g on m.gradeInKoreaID = g.gradeInKoreaID
Where GradeInKoreaName = '청소년 관람불가' and BoxOfficeWWGross =(
	Select Max(BoxOfficeWWGross)
	from Movie as m inner join GradeInKorea as g on m.gradeInKoreaID = g.gradeInKoreaID
	Where GradeInKoreaName = '청소년 관람불가'
);
-- 10
Select AVG(BoxOfficeWWGross)
from movie
Where ReleaseYear < 1999;
-- 11
Select name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where RoleName ='Director' and Budget = (
	Select Max(Budget)
	From Movie
);
-- 12
Select name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 2
Group by name
Having sum(budget) = (
Select Max(data) 
from (
Select sum(budget) as data
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
	Where r.RoleID = 2
	Group by name) as field
);
-- 13
Select name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 6
Group by name
Having sum(BoxOfficeWWGross) = (
Select Max(data) 
from (
Select sum(BoxOfficeWWGross) as data
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
	Where r.RoleID = 6
	Group by name) as field
);
-- 14
Select title,budget
From movie
Where budget != 0 and budget = (
	Select min(budget)
	From movie
	Where budget != 0 and budget
);
-- 15
Select avg(BoxOfficeUSGross)
from movie
Where budget <= 50000000;
-- 16
select AVG(BoxOfficeWWGross)
from 
	movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
Where GenreKorName = '액션';
-- 17
select title
from 
	movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
Where GenreKorName = '드라마' or GenreKorName = '전쟁';
-- 18
Select Title, Koreantitle, releaseYear
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 6 and koreanName = '톰 행크스' and RunningTime = (
	Select MAX(RunningTime)
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
    Where r.RoleID = 6 and koreanName = '톰 행크스'
);
-- 19
Select name
From Person as p inner join appear as a on p.PersonID = a.PersonID
inner join awardinvolve as aw on a.AppearID = aw.AppearID
inner join sector as s on s.sectorID = aw.sectorID
Where SectorKorName = '남우주연상' and WinningID = '2'
Group by name
having count(SectorKorName) = (
Select Max(count) 
	from(Select count(SectorKorName) as count
    From Person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
	Where SectorKorName = '남우주연상' and WinningID = '2'
	Group by name) as result
);
-- 20
Select name
From Person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
    inner join role as r on r.RoleID = a.RoleID
Where WinningID = 2 and KoreanName != '수상자 없음' and r.RoleId = 6
Group by name
Having count(WinningID) = (
Select Max(count) 
	from(Select count(WinningID) as count
    From Person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
    inner join role as r on r.RoleID = a.RoleID
	Where WinningID = '2' and KoreanName != '수상자 없음' and r.RoleId = 6
	Group by name) as result
);
-- 21
Select name
From Person as p inner join appear as a on p.PersonID = a.PersonID
inner join awardinvolve as aw on a.AppearID = aw.AppearID
inner join sector as s on s.sectorID = aw.sectorID
Where SectorKorName = '남우주연상' and WinningID = '2'
Group by name
having count(SectorKorName) >=2;
-- 23
Select name
From Person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
    inner join role as r on r.RoleID = a.RoleID
Where WinningID = 2 and KoreanName != "수상자 없음"
Group by name
Having count(WinningID) = (
Select Max(count) 
	from(Select count(WinningID) as count
    From Person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
    inner join role as r on r.RoleID = a.RoleID
	Where WinningID = '2' and KoreanName != "수상자 없음"
	Group by name) as result
);
-- 24
Select title
    From Movie as m inner join appear as a on m.MovieId = a.MovieID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
Where WinningID = 1
Group by title
Having count(WinningID) = (Select MAX(count) from(
	Select count(WinningID) as count
    From Movie as m inner join appear as a on m.MovieId = a.MovieID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
	Where WinningID = 1
	Group by title) as result
);
-- 25
Select name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 7
Group by p.PersonID
order by count(m.MovieID) desc
Limit 1;
-- 26
Select title
from movie
order by BoxOfficeWWGross desc
limit 10;
-- 27
Select title, BoxOfficeWWGross, budget
from movie
Where BoxOfficeWWGross >= 1000000000 and budget<=100000000;
-- 28
Select name
	From movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.MovieID = a.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
    inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.roleID = 2 and GenreKorName = "전쟁"
Group by p.name
Order by count(m.MovieID) desc
limit 1;
-- 29
Select name
	From movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.MovieID = a.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
    inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 6 or r.RoleID = 7 and GenreKorName = "드라마"
Group by p.PerSonID
order by count(m.MovieID) desc
limit 1;
-- 30
Select name
	From movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.MovieID = a.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
    inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 6 or r.RoleID = 7 and GenreKorName = "드라마" and GenreKorName !="호러"
Group by p.PerSonID;
-- 31
Select * from awardyear;

Select Location
from awardYear
Group by Location
order by count(AwardID) desc
limit 1;
-- 33
Select 2023-min(year) as Year
from awardYear;
-- 34
Select name
    From Movie as m inner join appear as a on m.MovieId = a.MovieID
    inner join Person as p on p.PersonID = a.PersonId
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
Where WinningID = 1 and KoreanName != '수상자 없음'
Group by name
order by count(WinningID) desc
limit 1;
-- 35
Select name
From Person as p inner join appear as a on p.PersonID = a.PersonID
inner join awardinvolve as aw on a.AppearID = aw.AppearID
inner join sector as s on s.sectorID = aw.sectorID
inner join awardYear as ay on ay.awardYearID = aw.awardYearID
Where SectorKorName = '남우주연상' and WinningID = '2' and Year>=1999 and Year<=2009;
-- 36

-- 37
Select Name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where RoleKorName = '배우' and title in (Select distinct title
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 2 and Koreanname = '제임스 카메론');
-- 38
Select distinct SectorKorName
From Person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearID = aw.AppearID
	inner join sector as s on s.sectorID = aw.sectorID
	inner join awardYear as ay on ay.awardYearID = aw.awardYearID
Where WinningID = 2 and KoreanName ='월트 디즈니';
-- 39
Select GenreKorName, avg(budget)
From movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.MovieID = a.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
    inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Group by GenreKorName;
-- 40
Select GenreKorName, avg(boxofficeWWGross)/avg(budget) * 100 as "제작비 대비 수익률"
From movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.MovieID = a.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
    inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Group by GenreKorName;
