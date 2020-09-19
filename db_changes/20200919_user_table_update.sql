ALTER TABLE `innox`.`user`   
	CHANGE `avatar` `avatar` VARCHAR(1000) CHARSET utf8 COLLATE utf8_general_ci NULL;
	
ALTER TABLE `innox`.`user_address`   
	ADD COLUMN `user_id` BIGINT(20) NULL AFTER `updated_time`,
  ADD CONSTRAINT `fk_1` FOREIGN KEY (`user_id`) REFERENCES `innox`.`user`(`id`);
