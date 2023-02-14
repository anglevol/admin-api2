CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `user_name` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `sex` int(1) DEFAULT NULL COMMENT '0 man 1 women',
                        `name` varchar(255) DEFAULT NULL,
                        `avatar` varchar(255) DEFAULT NULL,
                        `introduction` varchar(255) DEFAULT NULL,
                        `roles` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `holiday`.`user`(`id`, `user_name`, `password`, `sex`, `name`, `avatar`, `introduction`, `roles`) VALUES (1, 'admin', '111111', 0, 'Super Admin', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'I am a super administrator', 'admin');
INSERT INTO `holiday`.`user`(`id`, `user_name`, `password`, `sex`, `name`, `avatar`, `introduction`, `roles`) VALUES (2, 'editor', '222222', 1, 'Normal Editor', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'I am an editor', 'editor');

