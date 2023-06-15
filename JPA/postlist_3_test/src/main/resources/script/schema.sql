CREATE TABLE IF NOT EXISTS `Posts`(
    `id` bigint AUTO_INCREMENT,
    `title` varchar(255) not null,
    `content` text,
    `writer_user_id` varchar(255) not null,
    `write_time` datetime,
    `view_count` integer,

    primary key(`id`)
);

CREATE TABLE IF NOT EXISTS `Users`(
    `id` varchar(255) primary key,
    `password` varchar(255) not null,
    `name` varchar(255),
    `profile_file_name` varchar(255)
);

merge into `Users` key (`id`) values('user1','1234','유저1','user1.jpg');
merge into `Users` key (`id`) values('user2','1234','유저2','user2.jpg');
merge into `Users` key (`id`) values('user3','1234','유저3','user3.jpg');

merge into `Posts` key (`id`) values(1,'제목1','내용1','user1',now(),0);
merge into `Posts` key (`id`) values(2,'제목2','내용2','user1',now(),0);
merge into `Posts` key (`id`) values(3,'제목3','내용3','user2',now(),0);