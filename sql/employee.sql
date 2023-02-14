CREATE TABLE `employee` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `employeeId` varchar(50),
    `user_id` INT(11),
    `name` varchar(50),
    `department_id` INT(11),
    `gender` int(1) DEFAULT NULL COMMENT '0 man 1 women',
    `employdate` DATE,
    `remaining_days` INT(4),
    `comment` varchar(200),
    `createdate` DATE,
    `updatedate` DATE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
