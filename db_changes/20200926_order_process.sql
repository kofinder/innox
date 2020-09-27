CREATE TABLE `innox`.`payment_type` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `code` VARCHAR(100),
  `payment_type_image` VARCHAR(1000),
  `status` INT(1) COMMENT '1 = Active / 2 = Inactive',
  `is_offline` INT(2) COMMENT '1 = Offline / 2 = Online',
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `innox`.`order` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT(20),
  `invoice_number` VARCHAR(100),
  `total_cost` DECIMAL(15,2),
  `delivery_fee` DECIMAL(15,2),
  `order_status` INT(1) COMMENT '1=procesing / 2=onprogress / 3=finish / 4=cancel',
  `payment_status` INT(1) COMMENT '1 = pending / 2=finish / 3=cancel / 4=error',
  `payment_type_id` BIGINT(20),
  `order_date` DATETIME,
  `state_id` BIGINT(20),
  `township_id` BIGINT(20),
  `delivery_address` VARCHAR(255),
  `remark` VARCHAR(1000),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_fk1` FOREIGN KEY (`customer_id`) REFERENCES `innox`.`user`(`id`),
  CONSTRAINT `order_fk2` FOREIGN KEY (`payment_type_id`) REFERENCES `innox`.`payment_type`(`id`),
  CONSTRAINT `order_fk3` FOREIGN KEY (`state_id`) REFERENCES `innox`.`state`(`id`),
  CONSTRAINT `order_fk4` FOREIGN KEY (`township_id`) REFERENCES `innox`.`township`(`id`)
);

CREATE TABLE `innox`.`order_item` (  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT(20),
  `product_id` BIGINT(20),
  `unit_price` DECIMAL(15,2),
  `quantity` INT(20),
  `sub_total` DECIMAL(15,2),
  `created_time` DATETIME,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_item_fk1` FOREIGN KEY (`order_id`) REFERENCES `innox`.`order`(`id`),
  CONSTRAINT `order_item_fk2` FOREIGN KEY (`product_id`) REFERENCES `innox`.`product`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;
