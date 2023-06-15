use exam_18;
CREATE TABLE Person(
	person_id int primary key,
    description varchar(1024),
    kor_name varchar(100),
    eng_name varchar(100),
    real_name varchar(255),
    birth date,
    birhplace varchar(100),
    death date,
    deateplace varchar(100)
);
CREATE TABLE Roles(
	role_id int primary key,
    role_name varchar(100)
);
CREATE TABLE Restrictions(
	restriction_id int primary key,
    restriction_name varchar(100)
);
CREATE TABLE Movies(
	movie_id int primary key,
    movie_kor_title varchar(255),
    moive_eng_title varchar(255),
    movie_running_time varchar(255),
    movie_opening_date date,
    movie_plot varchar(255),
    movie_restriction_id int,
    constraint fk_movie_restriction foreign key(movie_restriction_id) references Restrictions(restriction_id)
);
CREATE TABLE Genres(
	genre_id int primary key,
    genre_name varchar(100)
);
CREATE TABLE MovieGerne(
	movie_id int,
    genre_id int,
    constraint pk_movie_gerne primary key(movie_id,genre_id),
    constraint fk_movieGerne_movie foreign key(movie_id) references Movies(movie_id),
    constraint fk_movieGerne_genre foreign key(genre_id) references Genres(genre_id)
);

CREATE TABLE Trivias(
	movie_id int,
	trivia_id int primary key,
    trivia_content varchar(1024),
    constraint fk_trivia_movie foreign key(movie_id) references Movies(movie_id)
);
CREATE TABLE BoxOffice(
	movie_id int,
	boxoffice_id int primary key,
	production_cost varchar(255),
    american_box_office varchar(255),
    world_box_office varchar(255),
    constraint fk_boxoffice_movie foreign key(movie_id) references Movies(movie_id)
);

CREATE TABLE Appear(
	appear_id int,
    movie_id int,
    person_id int,
    role_id int,
    constraint pk_appear primary key (appear_id,movie_id,person_id,role_id),
    constraint fk_appear_movie foreign key (movie_id) references Movies(movie_id),
    constraint fk_appear_person foreign key (person_id) references Person(person_id),
    constraint fk_appear_role foreign key (role_id) references Roles(role_id)
);
CREATE TABLE Characters(
	appear_id int,
	character_id int primary key,
    character_name varchar(255),
    constraint fk_character_appear foreign key(appear_id) references Appear(appear_id)
);
CREATE TABLE Award(
	award_id int primary key,
    award_name varchar(255)
);
CREATE TABLE AwardDetails(
	award_detail_id int primary key,
    award_detail_name varchar(255)
);
CREATE TABLE AwardAppear(
	award_appear_id int,
    award_id int,
    award_detail_id int,
    appear_id int,
    award_appear_date date,
    constraint pk_award_appear primary key(award_appear_id,award_id,award_detail_id,appear_id),
    constraint fk_awardAppear_award foreign key (award_id) references Award(award_id),
    constraint fk_awardAppear_awardDetail foreign key (award_detail_id) references AwardDetails(award_detail_id),
    constraint fk_awardAppear_appear foreign key(appear_id) references Appear(appear_id)
);
insert into Person value(392,"쿠엔틴 제롬 타란티노는 간호사인 어머니와 뉴욕출신인 이탈리아계 미국인 아버지 사이에서 태어났다. 타란티노는 네 살때 어머니와 함께 캘리포니아 토렌스로 이사했다. 1992년 1월, 선댄스 영화제에서 그의 첫 번째 각본-감독한 영화인 <저수지의 개들>(1992)이 소개되었다. 영화는 컬트적인 인기를 얻으며 단숨에 그를 전설로 만들었으며, 2년 후 칸 영화제에 <펄프 픽션>(1994)이 상영되었고, 이 영화는 아카데미 영화제에서 작품상, 감독상 및 각본상 후보에 올랐으며, 공동 각본 로저 에버리와 함께 각본상을 수생했다. 1995년...","쿠엔틴 타란티노","Quentin Tarantino","1963-03-27","녹스빌, 테네시, 미국",null,null,"Quentin Jerome Tarantino");
insert into Person value(207,"Few actors in the world have had a career quite as diverse as Leonardo DiCaprio‘s. DiCaprio has gone from relatively humble beginnings, as a supporting cast member of the sitcom Growing Pains (1985) and low budget horror movies, such as Critters 3 (1991), to a major teenage heartthrob in the 1990s, ...","레오나르도 디카프리오","Leonardo DiCaprio","1974-11-11","할리우드, 로스앤젤레스, 캘리포니아, 미국",null,null,"Leonardo Wilhelm DiCaprio");
insert into Person value(9,"Self-taught writer-director Richard Stuart Linklater was born in Houston, Texas, to Diane Margaret (Krieger), who taught at a university, and Charles W. Linklater III. Richard was among the first and most successful talents to emerge during the American independent film renaissance of the 1990s. Typ...","리처드 링클레이터","Richard Linklater","1960-07-30","휴스턴, 텍사스, 미국",null,null,"Richard Stuart Linklater");
insert into Restrictions value(3,"12세");
insert into Restrictions value(4,"15세");
insert into Movies value(10230,"원스 어폰 어 타임... 인 할리우드","Once Upon a Time... in Hollywood","2시간 41분","2019-09-25","쇠락해 가는 TV 스타 릭 달톤과 그의 친구인 스턴트 클리프 부스는 1969년 할리우드 황금기의 마지막 몇 년동안 영화 산업에서 명성을 쌓고 성공하기 위해 노력한다.",3);
insert into Movies value(2530,"레버넌트: 죽음에서 돌아온 자","The Revenant","2시간 36분","2016-01-14","1820년 모피 사냥 원정대에서 곰의 습격을 받아 심한 부상을 입은 남자가 자신의 팀원들에 의해 아들이 죽고 자신도 죽을 위기에 처한다. 죽음의 위기에서 살아난 남자가 복수를 시작한다.",4);
insert into Movies value(11206,"리처드 3세","Richard III","2시간 41분","1956-03-11","Shakespeare‘s powerful tale of the wicked deformed King and his conquests, both on the battlefield and in the boudoir.",null);
insert into Genres value(1,"드라마");
insert into Genres value(2,"코미디");
insert into MovieGerne value(10230,1);
insert into MovieGerne value(10230,2);
insert into Roles value(1,"감독");
insert into Roles value(2,"각본");
insert into Roles value(3,"배우");
insert into Roles value(4,"예술");
insert into Roles value(5,"촬영");
insert into Roles value(6,"의상");
insert into Roles value(7,"편집");
insert into Roles value(8,"제작");
insert into Roles value(9,"프로덕션 디자인");
insert into Roles value(10,"세트 데코레이션");
insert into Roles value(11,"음향");
insert into Appear value(1,10230,392,1);
insert into Appear value(2,10230,392,2);
insert into Appear value(3,10230,392,3);
insert into Appear value(4,10230,392,8);
insert into Appear value(5,10230,207,3);
insert into Appear value(6,2530,207,3);
insert into Award value(1,"Best Performance by an Actor in a Leading Role");
insert into Award value(2,"Best Motion Picture of the Year");
insert into Award value(3,"Best Actor in a Supporting Role");
insert into AwardDetails value(1,"winning");
insert into AwardDetails value(2,"lose");
insert into AwardAppear value(1,1,1,6,"2016-01-01");
insert into AwardAppear value(2,1,2,5,"2020-01-01");


-- 웹 브라우저를 실행합니다.
-- https://movie.datamotion.co.kr/Person/?id=392에 접속합니다.
-- 쿠엔틴 타란티노의 정보가 표시되는 것을 확인합니다.
SELECT *
from Person
Where person_id = 392;
-- 첫 번째 영화 '원스 어폰 어 타임... 인 할리우드' 링크를 클릭합니다.
-- '원스 어폰 어 타임 인... 할리우드' 영화 정보가 표시되는 것을 확인합니다.
SELECT *
from Movies as m inner join MovieGerne as mg on m.movie_id = mg.movie_id
	inner join Genres as g on mg.genre_id = g.genre_id
    inner join Restrictions as r on m.movie_restriction_id = r.restriction_id
Where m.movie_id = 10230;
-- 첫 번째 배우 '레오나르도 디카프리오' 정보가 표시되는 것을 확인합니다.
SELECT *
from Person as p inner join Appear as a on p.person_id = a.person_id
	inner join Roles as r on a.role_id = r.role_id
    inner join Movies as m on m.movie_id = a.movie_id
Where r.role_name ="배우" and m.movie_id = 10230;
-- 배우 이미지 아래쪽의 '상세 수상 정보 보기' 링크를 클릭합니다.
-- '레오나르도 디카프리오'의 아카데미 수상 정보를 확인합니다.
SELECT p.kor_name,awap.award_appear_date, aw.award_name, ad.award_detail_name 
from Appear as ap inner join AwardAppear as awap on ap.appear_id = awap.appear_id
		inner join Award as aw on aw.award_id = awap.award_id
        inner join AwardDetails as ad on awap.award_detail_id = ad.award_detail_id
        inner join Person as p on p.person_id = ap.person_id
Where p.person_id = 207;
-- 오른쪽 위의 검색 창에 '리처드'를 입력한 후 검색 결과를 확인합니다.
SELECT *
from Movies
Where movie_kor_title like "%리처드%";

SELECT *
from Person
Where kor_name like "%리처드%";
