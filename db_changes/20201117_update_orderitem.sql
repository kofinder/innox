ALTER TABLE `innox`.`order_item`   
	ADD COLUMN `color_id` BIGINT(20) NULL AFTER `product_id`,
	ADD COLUMN `size_id` BIGINT(20) NULL AFTER `color_id`;
	
ALTER TABLE `innox`.`order_item`  
  ADD CONSTRAINT `order_item_fk_3` FOREIGN KEY (`color_id`) REFERENCES `innox`.`color`(`id`),
  ADD CONSTRAINT `order_item_fk_4` FOREIGN KEY (`size_id`) REFERENCES `innox`.`size`(`id`);