CREATE TABLE `innox`.`shopping_cart` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT(20),
  `product_id` BIGINT(20),
  `color_id` BIGINT(20),
  `size_id` BIGINT(20),
  `quantity` INT(10),
  `is_custom_product` TINYINT(1) DEFAULT 0,
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `customer_id_FK` FOREIGN KEY (`customer_id`) REFERENCES `innox`.`user`(`id`),
  CONSTRAINT `product_id_FK` FOREIGN KEY (`product_id`) REFERENCES `innox`.`product`(`id`),
  CONSTRAINT `color_id_sh_FK` FOREIGN KEY (`color_id`) REFERENCES `innox`.`color`(`id`),
  CONSTRAINT `size_id_FK` FOREIGN KEY (`size_id`) REFERENCES `innox`.`size`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;