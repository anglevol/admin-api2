CREATE TABLE `employee` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `employee_id` varchar(20),
    `user_id` INT(11),
    `name` varchar(50),
    `department_code` varchar(20),
    `gender` int(1) DEFAULT NULL COMMENT '0 man 1 women',
    `employdate` DATE,
    `remaining_days` INT(4),
    `comment` varchar(200),
    `createdate` DATE,
    `updatedate` DATE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `holiday`.`employee`
    ADD COLUMN `deleted` int(1) NOT NULL DEFAULT 0 AFTER `updatedate`;
