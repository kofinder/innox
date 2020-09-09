ALTER TABLE `innox`.`user`   
	ADD COLUMN `phone_no` VARCHAR(30) NULL AFTER `email`;
	
CREATE TABLE `innox`.`user_address` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `state_id` BIGINT(20),
  `township_id` BIGINT(20),
  `detail_address` VARCHAR(1000),
  `is_primary_address` TINYINT(1),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

ALTER TABLE `innox`.`user`   
	CHANGE `user_role_level` `user_role_level` INT(1) DEFAULT 1 NULL COMMENT '1 = Admin / 2 = User / 3 = Designer';