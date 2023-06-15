CREATE DataBase 이성운_Board;

use 이성운_Board;

CREATE TABLE Users(
	User_id integer primary key,
    User_name varchar(20),
    role varchar(20),
    create_at timestamp
);
CREATE TABLE Post(
	Post_id integer primary key,
    Title varchar(100),
    body text,
    User_id integer,
    Board_id integer,
    Category_id integer,
    created_at timestamp
);
CREATE TABLE Board(
	Board_id integer primary key,
    Board_name varchar(20)
);
CREATE TABLE Category(
	Category_id integer primary key,
	Category varchar(20)
);
CREATE TABLE Comments(
	Comment_id integer primary key,
    Post_id integer,
    User_id integer,
    body text
);
CREATE TABLE Reply(
	Reply_id integer primary key,
    Comment_id integer,
    User_id integer,
    body text
);
alter table Reply add constraint fk_Reply_Comments foreign key(Comment_id) references Comments(Comment_id) on delete set null;
alter table Reply add constraint fk_Reply_Users foreign key(User_id) references Users(User_id) on delete cascade;

alter table Comments add constraint fk_Comments_Users foreign key(User_id) references Users(User_id) on delete cascade;
alter table Comments add constraint fk_Comments_Post foreign key(Post_id) references Post(Post_id) on delete cascade;

alter table Post add constraint fk_Post_Board foreign key(Board_id) references Board(Board_id) on delete cascade;
alter table Post add constraint fk_Post_Users foreign key(User_id) references Users(User_id) on delete cascade;
alter table Post add constraint fk_Post_Category foreign key(Category_id) references Category(Category_id) on delete set null;
