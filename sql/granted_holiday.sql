CREATE TABLE `granted_holiday` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `user_id` INT(11) NOT NULL,
    `granted_service_years` FLOAT(3,1),
    `granted_date` DATE,
    `expiry_date` DATE,
    `carryover_expiry_date` DATE,
    `status` INT(2),
    `granted_days` INT(4),
    `used_days` INT(4),
    `unused_days` INT(4),
    `remaining_days` INT(4),
	`create_time` DATETIME,
	`update_time` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `granted_holiday`(`id`,`user_id`,`granted_service_years`,`granted_date`, `expiry_date`,`carryover_expiry_date`,`status`,`granted_days`,`used_days`,`unused_days`,`remaining_days`,`create_time`,`update_time`) VALUES (1,2,1,'2023-01-23','2024-01-23','2025-01-23',0,10,0,10,10,now(),now());
INSERT INTO `granted_holiday`(`id`,`user_id`,`granted_service_years`,`granted_date`, `expiry_date`,`carryover_expiry_date`,`status`,`granted_days`,`used_days`,`unused_days`,`remaining_days`,`create_time`,`update_time`) VALUES (2,12138,0.5,'2022-01-23','2023-01-23','2024-01-23',0,10,0,10,10,now(),now());
