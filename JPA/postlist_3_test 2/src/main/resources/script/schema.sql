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