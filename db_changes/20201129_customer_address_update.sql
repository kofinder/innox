ALTER TABLE `innox`.`user_address`  
  ADD CONSTRAINT `fk_2` FOREIGN KEY (`state_id`) REFERENCES `innox`.`state`(`id`),
  ADD CONSTRAINT `fk_3` FOREIGN KEY (`township_id`) REFERENCES `innox`.`township`(`id`);