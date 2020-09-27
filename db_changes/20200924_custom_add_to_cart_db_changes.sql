ALTER TABLE `innox`.`product`   
	CHANGE `size` `is_custom_product` INT(1) NULL COMMENT '1 = Instock / 2 = Custom Product',
	CHANGE `color` `custom_product_id` BIGINT(20) NULL,
  ADD CONSTRAINT `fk_custom_product_id` FOREIGN KEY (`custom_product_id`) REFERENCES `innox`.`custom_product`(`id`);
  
ALTER TABLE `innox`.`product`   
	ADD COLUMN `custom_product_item_id` BIGINT(20) NULL AFTER `custom_product_id`,
  ADD CONSTRAINT `fk_custom_product_item` FOREIGN KEY (`custom_product_item_id`) REFERENCES `innox`.`custom_item`(`id`);
  
ALTER TABLE `innox`.`product_size`   
	ADD COLUMN `quantity` INT(50) DEFAULT 0 NULL AFTER `created_by_id`;
	
	
CREATE TABLE `innox`.`product_layout` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20),
  `custom_item_layout_id` BIGINT(20),
  `created_design_image` VARCHAR(1000),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_layout_fk_1` FOREIGN KEY (`product_id`) REFERENCES `innox`.`product`(`id`),
  CONSTRAINT `product_layout_fk_2` FOREIGN KEY (`custom_item_layout_id`) REFERENCES `innox`.`custom_item_layout`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `innox`.`product_artwork` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_layout_id` BIGINT(20),
  `artwork_id` BIGINT(20),
  `price` DECIMAL(15,2),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_artwork_fk_1` FOREIGN KEY (`product_layout_id`) REFERENCES `innox`.`product_layout`(`id`),
  CONSTRAINT `product_artwork_fk_2` FOREIGN KEY (`artwork_id`) REFERENCES `innox`.`artwork`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `innox`.`product_font` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_layout_id` BIGINT(20),
  `fonts_id` BIGINT(20),
  `price` DECIMAL(15,2),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_font_fk_1` FOREIGN KEY (`product_layout_id`) REFERENCES `innox`.`product_layout`(`id`),
  CONSTRAINT `product_font_fk_2` FOREIGN KEY (`fonts_id`) REFERENCES `innox`.`fonts`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

UPDATE product SET is_custom_product =1;
