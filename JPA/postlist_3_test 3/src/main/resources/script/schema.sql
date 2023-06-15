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
merge into `Users` key (`id`) values('user4','1234','유저4','user4.jpg');
merge into `Users` key (`id`) values('user5','1234','유저5','user5.jpg');
merge into `Users` key (`id`) values('user6','1234','유저6','user6.jpg');
merge into `Users` key (`id`) values('user7','1234','유저7','user7.jpg');
merge into `Users` key (`id`) values('user8','1234','유저8','user8.jpg');
merge into `Users` key (`id`) values('user9','1234','유저9','user9.jpg');



merge into `Posts` key (`id`) values(1,'제목1','내용1','user1',now(),1);
merge into `Posts` key (`id`) values(2,'제목2','내용2','user1',now(),10);
merge into `Posts` key (`id`) values(3,'제목3','내용3','user2',now(),100);
merge into `Posts` key (`id`) values(4,'제목4','내용11','user1',now(),0);
merge into `Posts` key (`id`) values(5,'제목5','내용22','user1',now(),2);
merge into `Posts` key (`id`) values(6,'제목6','내용33','user2',now(),20);
merge into `Posts` key (`id`) values(7,'제목7','내용111','user1',now(),21);
merge into `Posts` key (`id`) values(8,'제목8','내용222','user1',now(),22);
merge into `Posts` key (`id`) values(9,'제목9','내용333','user2',now(),10);
merge into `Posts` key (`id`) values(10,'제목10','내용1111','user1',now(),1);
merge into `Posts` key (`id`) values(11,'제목11','내용2222','user1',now(),4);
merge into `Posts` key (`id`) values(12,'제목12','내용3333','user2',now(),6);
merge into `Posts` key (`id`) values(13,'제목13','내용11111','user1',now(),10);
merge into `Posts` key (`id`) values(14,'제목14','내용22222','user1',now(),43);
merge into `Posts` key (`id`) values(15,'제목15','내용33333','user2',now(),64);