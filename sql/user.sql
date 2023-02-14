CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `user_name` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `sex` int(1) DEFAULT NULL COMMENT '0 man 1 women',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `user`(`id`, `user_name`, `password`, `sex`, `department`) VALUES (1, 'admin', '111111', 0);
