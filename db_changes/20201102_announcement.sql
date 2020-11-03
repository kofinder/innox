CREATE TABLE `innox`.`announcement` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `noti_type` INT(1),
  `title` VARCHAR(1000),
  `description` LONGTEXT,
  `summary_image` VARCHAR(3000),
  `detail_image` VARCHAR(3000),
  `created_by` BIGINT(20),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `announcement_fk_1` FOREIGN KEY (`created_by`) REFERENCES `innox`.`user`(`id`)
);

ALTER TABLE `innox`.`user`   
	ADD COLUMN `device_token` VARCHAR(1000) NULL AFTER `user_role_level`,
	ADD COLUMN `device_type` INT(1) NULL COMMENT '1 = Android / 2 = IOS' AFTER `device_token`;
	
ALTER TABLE `innox`.`announcement`   
	ADD COLUMN `status` INT(1) NULL COMMENT '1 = Active / 2 = Inactive' AFTER `detail_image`;