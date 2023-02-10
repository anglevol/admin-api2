CREATE TABLE `user_token` (
                              `user_id` int(11) NOT NULL AUTO_INCREMENT,
                              `token` varchar(20) DEFAULT NULL,
                              PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `user_token`(`user_id`, `token`) VALUES (1, 'admin-token');
INSERT INTO `user_token`(`user_id`, `token`) VALUES (2, 'editor-token');
