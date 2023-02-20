-- 付与休暇テーブル

CREATE TABLE `granted_holiday` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `user_id` INT(11) NOT NULL,
    `employee_id` INT(11) NOT NULL,
    `granted_service_years` FLOAT(3,1),
    `granted_date` DATE,
    `expiry_date` DATE,
    `carryover_expiry_date` DATE,
    `status_code` VARCHAR(20),
    `granted_days` INT(4),
    `used_days` INT(4),
    `unused_days` INT(4),
    `remaining_days` INT(4),
	`create_time` DATETIME,
	`update_time` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `granted_holiday`(`user_id`,`employee_id`,`granted_service_years`,`granted_date`, `expiry_date`,`carryover_expiry_date`,`status_code`,`granted_days`,`used_days`,`unused_days`,`remaining_days`,`create_time`,`update_time`) VALUES (1,9527,0.5,'2018-01-23','2019-01-23','2020-01-23','GHST04',10,0,10,0,now(),now());
INSERT INTO `granted_holiday`(`user_id`,`employee_id`,`granted_service_years`,`granted_date`, `expiry_date`,`carryover_expiry_date`,`status_code`,`granted_days`,`used_days`,`unused_days`,`remaining_days`,`create_time`,`update_time`) VALUES (1,9527,1.5,'2019-01-23','2020-01-23','2021-01-23','GHST05',10,10,0,0,now(),now());
INSERT INTO `granted_holiday`(`user_id`,`employee_id`,`granted_service_years`,`granted_date`, `expiry_date`,`carryover_expiry_date`,`status_code`,`granted_days`,`used_days`,`unused_days`,`remaining_days`,`create_time`,`update_time`) VALUES (1,9527,2.5,'2022-01-23','2023-01-23','2024-01-23','GHST03',10,5,5,5,now(),now());
