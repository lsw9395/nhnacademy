-- 1. DBManager 라는 이름을 가지는 사용자를 작성하세요.
Create user DBManager identified by '12345678';
-- 2. User 라는 이름을 가지는 사용자를 작성하세요.
Create user User identified by '12345678';
Drop user User;
-- 3. DBManger 사용자는 DatamotionMovieDatabase의 모든 개체에 모든 권한을 가지되, 다른 데이터베이스에 대한 권한은 가지지 않아야 합니다.
Grant ALL privileges on DatamotionMovieDatabase to DBManager;
-- 4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
Grant Select on *.* To User;
Grant INSERT, Update on DatamotionMovieDatabase.PersonTrivia to User;
Grant INSERT, Update on DatamotionMovieDatabase.MovieTrivia to User;
-- 5. '영화'에 출연한 '배우'와 관련된 뷰를 작성하세요.
SHOW FULL TABLES IN DatamotionMovieDatabase
WHERE TABLE_TYPE LIKE 'VIEW';
drop view ActorInfo;
drop view DirectorInfo;
drop view AwardInfo;
drop view GenreInfo;
Create view ActorInfo
AS 
	Select distinct p.*, m.*, r.*
    From
		Movie as m inner join appear as a on m.MovieID = a.MovieID
		inner join role as r on r.RoleID = a.RoleID
		inner join Person as p on p.PersonID = a.PersonID
	Where RoleKorName = '배우';
Select *  from ActorInfo;
-- 6. '영화'를 감독한 '감독'과 관련된 뷰를 작성하세요.
Create view DirectorInfo
AS 
	Select distinct p.*, m.*, r.*
    From
		Movie as m inner join appear as a on m.MovieID = a.MovieID
		inner join role as r on r.RoleID = a.RoleID
		inner join Person as p on p.PersonID = a.PersonID
	Where RoleKorName = '감독';
Select *  from DirectorInfo;
-- 7. '아카데미 시상 결과'과 관련된 뷰를 작성하세요.
Create view AwardInfo
AS 
	Select ay.Date, ay.Location, p.name, s.SectorKorName, m.RunningTime
	from 
		awardInvolve as al inner join sector as s on al.SectorID = s.SectorID
		inner join awardYear as ay on al.awardYearID = ay.awardYearID
		inner join winning as w on al.WinningID = w.WinningID
		inner join award as aw on ay.awardID = aw.awardID
		inner join appear as ap on al.AppearID = ap.AppearID
		inner join person as p on ap.PersonID = p.PersonID
		inner join Movie as m on m.MovieID = ap.MovieID
	Where w.WinningID = 2
	order by ay.Date;
Select * from AwardInfo;
-- 8. '영화 장르별 제작비와 흥행 수익'에 관련된 뷰를 작성하세요.
Create view GenreInfo
AS 
	Select GenreKorName, AVG(Budget) as Budget, AVG(BoxOfficeWWGross) as BoxOfficeWWGross
	From 
		Movie as m inner join MovieGenre as mg on m.MovieID = mg.MovieID
		inner join Genre as g on g.GenreID = mg.GenreID
	Group by g.GenreID;
Select * from GenreInfo;
Drop view GenreInfo;
-- 9. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
Select count(*)
From DirectorInfo
Where KoreanTitle = '매트릭스';
-- 10. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 출력하세요.
Select title
From ActorInfo
Where KoreanName = '레오나르도 디카프리오' and RunningTime >=100;
-- 11. '알란 실버스트리'가 음악을 작곡한 영화와 '애런 소킨'이 각본을 쓴 영화를 출력하세요. (애런 소킨 데이터를 찾아보세요)
Select p.Name, m.Title, m.Plot, m.ReleaseYear, m.RunningTime
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where KoreanName ='알란 실버스트리' and RoleKorName = '작곡'
Union
Select p.Name, m.Title, m.Plot, m.ReleaseYear, m.RunningTime
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where KoreanName ='아론 소킨' and RoleKorName = '각본가';
-- 12. '쿠엔틴 타란티노'가 감독한 영화에 출연한 배우들이 출연한 영화의 수익율을 배우별로 출력하세요.
Select data2.name, sum(BoxofficewwGross)/sum(budget) *100 as '수익률'
From (Select Title
	From directorInfo 
	Where KoreanName = '쿠엔틴 타란티노') as data1
	inner join
	(Select Title, BoxofficewwGross, Name , budget
	From Actorinfo) as data2 on data1.title = data2.title
	Group by data2.name;
-- 13. 위 문제들 중, 생성한 뷰에서 질의가 어려운 쿼리에 대한 뷰를 작성하세요.
Create view ComposerInfo
as
	Select distinct m.*, r.*, p.*
	From Movie as m inner join appear as a on m.MovieID = a.MovieID
		inner join role as r on r.RoleID = a.RoleID
		inner join Person as p on p.PersonID = a.PersonID
	Where RoleKorName = '작곡';
Select * from ComposerInfo;
Create view WriterInfo
as
	Select distinct m.*, r.*, p.*
	From Movie as m inner join appear as a on m.MovieID = a.MovieID
		inner join role as r on r.RoleID = a.RoleID
		inner join Person as p on p.PersonID = a.PersonID
	Where RoleKorName = '각본가';
Select * from WriterInfo;
-- 14. 새로 생성한 뷰를 사용해서 쿼리를 다시 작성하세요.
Select Name, Title, Plot, ReleaseYear, RunningTime
From ComposerInfo
Where KoreanName ='알란 실버스트리'
Union
Select Name, Title, Plot, ReleaseYear, RunningTime
From WriterInfo
Where KoreanName ='아론 소킨';


-- 14. 사용자 테이블을 작성하세요. 사용자 테이블은 사용자의 ID, 이름, 패스워드, 전자메일 주소를 필드로 가집니다.
CREATE TABLE User (
	UserId int primary key auto_increment,
	Name varchar(20) NOT NULL,
	Password varchar(20) NOT NULL,
	Email VARCHAR(50) NOT NULL
);
-- 15. 사용자가  MovieTrivia 테이블과 PersonTrivia 테이블에 투플을 삽입할 수 있고, 각 투플에 삽입한 사용자를 식별할 수 있는 정보가 포함되어야 할 때, 두 테이블의 릴레이션 스키마를 수정하세요.
Alter table MovieTrivia Add UserId int;
Alter table PersonTrivia Add UserId int;
-- 16. 수정된 테이블 두 테이블(MovieTrivia, PersonTrivia)과 사용자 테이블 사이의 참조 무결성을 위한 제약조건을 설정하세요.
Alter table MovieTrivia add constraint fk_MovieTrivia_User foreign key(UserId) references User(UserId);
Alter table PersonTrivia add constraint fk_PersonTrivia_User foreign key(UserId) references User(UserId);
-- 17. User 사용자가 두 테이블(MovieTrivia, PersonTrivia)에 데이터를 삽입할 수 있도록 권한을 설정하세요.
Grant INSERT, Update on DatamotionMovieDatabase.PersonTrivia to User;
Grant INSERT, Update on DatamotionMovieDatabase.MovieTrivia to User;
 -- 위에서 이미 설정했기 때문에 할 필요가 없습니다.
-- 18. MovieTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Insert_Movie_Trivia (
  in trivia_id integer,
  in movie_id integer
)
begin
  Insert into MovieTrivia (TriviaID, MovieID) Values (trivia_id, movie_id);
end $$
Delimiter ;
-- 19. PersonTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Insert_Person_Trivia (
  in trivia_id integer,
  in movie_id integer
)
begin
  Insert into PersonTrivia (TriviaID, MovieID) Values (trivia_id, movie_id);
end $$
Delimiter ;
-- 20. 주어진 감독이 감독한 영화를 모두 출력하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Find_Movies_By_Director(
  in director_name varchar(50)
)
begin
  Select KoreanTItle
  From DirectorInfo
  Where KoreanName = director_name or Name = director_name;
end $$
Delimiter ;

call Find_Movies_By_Director("쿠엔틴 타란티노");

-- 21. 주어진 영화에 출연한 배우를 모두 출력하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Find_Actors_By_Movie (
  in movie_name VARCHAR(255)
)
begin
  Select KoreanName
  From ActorInfo
  Where (KoreanTitle = movie_name or Title = movie_name) and RoleKorName = '배우';
end$$
Delimiter ;
call Find_Actors_By_Movie("avatar");
call Find_Actors_By_Movie("아바타");
-- 22. 주어진 영화에 참여한, 감독, 각본, 배우를 제외한 모든 사람을 출력하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Find_Person_by_movie (
  in movie_title varchar(255)
)
begin
  select KoreanName, RoleKorName
  From Movie as m inner join Appear as ap on m.MovieID = ap.MovieID
	inner join Role as r on r.RoleID = ap.RoleID
    inner join Person as p on p.PersonID = ap.PersonID
  Where (title = movie_title or KoreanTitle = movie_title) and RoleKorName not in (
	Select RoleKorName
    From Movie as m inner join Appear as ap on m.MovieID = ap.MovieID
	inner join Role as r on r.RoleID = ap.RoleID
    inner join Person as p on p.PersonID = ap.PersonID
    Where r.RoleKorName = "감독" or r.RoleKorName = "각본가" or r.RoleKorName = "배우");
end$$
Delimiter ;
call Find_Person_by_movie("아바타");
call Find_Person_by_movie("avatar");
-- 23. 주어진 사람이 '참여'한 영화를 출력하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Find_Movie_By_Person_Name (
  in person_name varchar(255)
)
begin
  Select distinct Title
  From Movie as m inner join Appear as ap on m.MovieID = ap.MovieID
    inner join Person as p on p.PersonID = ap.PersonID
  Where KoreanName = person_name or name = person_name; 
end$$
Delimiter ;
call Find_Movie_By_Person_Name("레오나르도 디카프리오");
call Find_Movie_By_Person_Name("Leonardo Dicaprio");
-- 24. 주어진 장르에 대한 제작비, 전체 수익과 수익율을 출력하는 저장 프로시저를 작성하세요.
Delimiter $$
Create Procedure Find_Movie_By_Genre (
  in genre_name varchar(255)
)
begin
  Select * , boxOfficeWWGross/Budget * 100
  From GenreInfo
  Where GenreKorName = genre_name;
end$$
Delimiter ;
call Find_Movie_By_Genre("액션");
-- <수업하지 않은 내용>
-- 25. 저장 프로시저의 파라미터는 출력/입력/입출력 형태의 파라미터를 가질 수 있습니다. 
-- 주어진 영화(MovieID)로 출연/참여자 정보를 제외한 모든 정보를 출력 파라미터로 가지고, 
-- 실행 결과가 출력 파라미터에 기록되도록 하는 저장 프로시저를 작성하세요.

Delimiter $$
Create Procedure Get_Movie_Info (
	in moive_name int,
    out mTitle varchar(255),
	out mKoreanTitle varchar(255),
    out mPlot varchar(4000),
    out mReleaseYear smallint,
    out mRunningTime smallint,
    out mGradeName varchar(20),
    out mGradeInKoreaName varchar(20),
    out mReleaseDateInKorea Date,
    out mBoxOfficeWWGross Bigint,
    out mBudget Bigint,
    out mOriginalAuthor varchar(200),
    out mOriginalSource varchar(400),
    out mGenreName varchar(200),
    out mGenreKorName varchar(200),
    out mContents varchar(10000),
    out mRemark varchar(500),
    out mDate date,
    out mYear char(12),
    out mLocation varchar(400),
    out mSectorName varChar(400),
    out mSectorKorName varChar(500),
    out mAwardEnglishTitle varchar(100),
    out mAwardKoreanTitle varchar(100),
    out mDescription varchar(2000)
)
begin
	Select Title, KoreanTitle, Plot, ReleaseYear, RunningTime, GradeName, GradeInKoreaName,ReleaseDateInKorea, 
	BoxOfficeWWGross, Budget, OriginalAuthor, OriginalSource, group_concat(distinct GenreName),group_concat(distinct GenreKorName),
    group_concat(distinct contents), group_concat(distinct Remark), group_concat(distinct Year), group_concat(distinct Date), group_concat(distinct Location), 
    group_concat(distinct SectorName), group_concat(distinct SectorKorName), group_concat(distinct AwardEnglishTitle), group_concat(distinct AwardKoreanTitle),group_concat(distinct Description)
    into 
		mTitle, mKoreanTitle, mPlot,mReleaseYear ,mRunningTime ,mGradeName ,mGradeInKoreaName ,mReleaseDateInKorea ,
        mBoxOfficeWWGross, mBudget, mOriginalAuthor, mOriginalSource, mGenreName, mGenreKorName, mContents, mRemark, mYear, mDate, mLocation,
		mSectorName, mSectorKorName, mAwardEnglishTitle, mAwardKoreanTitle, mDescription
	from 
		Movie as m left outer join MovieGenre as mg on m.MovieID = mg.MovieID
		left outer join Genre as g on g.GenreID = mg.GenreID
		left outer join Appear as ap on ap.MovieID = m.MovieID
		left outer join awardInvolve as ai on ai.AppearID = ap.AppearID
		left outer join awardyear as ay on ay.AwardYearID = ai.awardYearID
		left outer join award as aw on aw.awardID = ay.awardID
		left outer join GradeinKorea as gi on gi.GradeInKoreaId = m.GradeInKoreaId
        left outer join movietrivia as mt on mt.MovieId = m.MovieID
        left outer join trivia as t on t.TriviaID = mt.TriviaID
        left outer join Sector as s on s.SectorID = ai.sectorID
        left outer join Grade as gr on gr.GradeID = m.GradeID
        where m.Movieid = moive_name
        Group by m.MovieID;
end $$
Delimiter ;

CALL Get_Movie_Info(1, @Title, @KoreanTitle,@Plot,@ReleaseYear ,@RunningTime ,@GradeName ,@GradeInKoreaName ,@ReleaseDateInKorea ,@BoxOfficeWWGross,
		@Budget, @OriginalAuthor, @OriginalSource, @GenreName, @GenreKorName, @Contents, @Remark, @Year, @Date,@Location,
		@SectorName, @SectorKorName, @AwardEnglishTitle, @AwardKoreanTitle, @Description);

Select @Title as 제목, @KoreanTitle 한글제목,@Plot 플롯,@ReleaseYear 개봉일,@RunningTime 러닝타임,@GradeName 등급,@GradeInKoreaName 한글등급,@ReleaseDateInKorea 한국개봉일 ,@BoxOfficeWWGross 총수익,
		@Budget 제작비, @OriginalAuthor 원작자, @OriginalSource 원작, @GenreName 장르, @GenreKorName 한글장르, @Contents 비하인드, @Remark 주목, @Year 수상년도, @Date 수상일자,@Location 수상위치,
		@SectorName 상이름, @SectorKorName 상한글이름, @AwardEnglishTitle 상제목, @AwardKoreanTitle 상한글제목, @Description 설명;
