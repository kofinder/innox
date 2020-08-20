ALTER TABLE `innox`.`size`   
	ADD COLUMN `status` INT(1) NULL COMMENT '1 = Active / 2 = Inactive' AFTER `updated_time`;

ALTER TABLE `innox`.`size`   
	CHANGE `status` `status` INT(1) NULL COMMENT '1 = Active / 2 = Inactive'  AFTER `size_name`;
	
	
CREATE TABLE `innox`.`artwork_category` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255),
  `category_code` VARCHAR(255),
  `status` INT(1) COMMENT '1 = Active / 2 = Inactive',
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`Id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `innox`.`artwork` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `designer_id` BIGINT(20),
  `artwork_name` VARCHAR(255),
  `original_price` DECIMAL(15,2) DEFAULT 0.00,
  `sell_price` DECIMAL(15,2) DEFAULT 0.00,
  `artwork_image` VARCHAR(3000),
  `status` INT(1) COMMENT '1 = Active / 2 = Inactive',
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`Id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;


ALTER TABLE `innox`.`artwork`  
  ADD CONSTRAINT `designer_id_fk` FOREIGN KEY (`designer_id`) REFERENCES `innox`.`user`(`id`);
  
CREATE TABLE `innox`.`artwork_tags` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `artwork_id` BIGINT(20),
  `artwork_category_id` BIGINT(20),
  `status` INT(1) COMMENT '1 = Active / 2 = Inactive',
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `artwork_id_fk` FOREIGN KEY (`artwork_id`) REFERENCES `innox`.`artwork`(`id`),
  CONSTRAINT `artwork_category_id_fk` FOREIGN KEY (`artwork_category_id`) REFERENCES `innox`.`artwork_category`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

ALTER TABLE `innox`.`users_roles`  
  CHARSET=utf8, COLLATE=utf8_general_ci;
  
  ALTER TABLE `innox`.`users_roles`   
	ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST, 
  ADD PRIMARY KEY (`id`);

  
ALTER TABLE `innox`.`user`   
	ADD COLUMN `user_role_level` INT(1) DEFAULT 1 NULL COMMENT '1 = User / 2 = Admin / 3 = Designer' AFTER `record_del_flag`;
