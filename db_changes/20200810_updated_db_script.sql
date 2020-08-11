ALTER TABLE `innox`.`color`   
	ADD COLUMN `Status` INT(1) DEFAULT 1 NULL COMMENT '1 = Active / 2 = Inactive' AFTER `color_name`;