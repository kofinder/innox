CREATE TABLE `innox`.`fonts` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `font_url` VARCHAR(3000),
  `font_sample` VARCHAR(500),
  `font_description` VARCHAR(1000),
  `font_image` VARCHAR(3000),
  `status` INT(1) DEFAULT 1 COMMENT '1 = Active / 2 = Inactive',
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;