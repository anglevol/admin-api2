CREATE TABLE `granted_holiday` (
                                   `user_id` INT(11) NOT NULL AUTO_INCREMENT,
                                   `granted_service_years` FLOAT(3,1),
												`granted_date` DATETIME,
												`expiry_date` DATETIME,
												`carryover_expiry_date` DATETIME,
												`granted_days` INT(4),
												`used_days` INT(4),
												`unused_days` INT(4),
												`remaining_days` INT(4),
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
