/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.18 : Database - eco_shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eco_shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `eco_shop`;

/*Table structure for table `banner` */

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_created_by_user` (`created_by_id`),
  CONSTRAINT `fk_created_by_user` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `banner` */

insert  into `banner`(`id`,`name`,`description`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Banner1','banner 1',NULL,1,0,1,'2020-06-06 17:27:01','2020-06-15 21:53:53'),
(2,'Banner 2','banner','banner/banner_image/2/2_banner_image_32489102495500fernando-hernandez-JdoofvUDUwc-unsplash.jpg',2,1,NULL,NULL,NULL),
(4,'Banner3','banner','banner/banner_image/4/4_banner_image_35045715112899fernando-hernandez-JdoofvUDUwc-unsplash.jpg',3,1,NULL,'2020-06-06 21:43:54','2020-06-07 20:48:22');

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `brand_fk_createdBy` (`created_by_id`),
  CONSTRAINT `brand_fk_createdBy` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Brand 1','brand/brand_image/1/1_brand_image_13390427613100fernando-hernandez-JdoofvUDUwc-unsplash.jpg',1,1,NULL,'2020-06-07 22:18:55',NULL);

/*Table structure for table `c2b_layout` */

DROP TABLE IF EXISTS `c2b_layout`;

CREATE TABLE `c2b_layout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `image_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sequence_no` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c2b_layout` */

/*Table structure for table `c2b_product` */

DROP TABLE IF EXISTS `c2b_product`;

CREATE TABLE `c2b_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `sequence_no` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Active',
  `category_id` bigint(20) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `ceated_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cat_fk_` (`category_id`),
  KEY `sub_cat_fk_` (`sub_category_id`),
  KEY `create_fk_` (`created_by_id`),
  CONSTRAINT `cat_fk_` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `create_fk_` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `sub_cat_fk_` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c2b_product` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Active',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_createdBY` (`created_by_id`),
  CONSTRAINT `fk_createdBY` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Shirt','category/category_image/1/1_category_image_16181950059300Casual.png',1,1,1,'2020-06-08 22:46:10','2020-06-15 21:54:12'),
(2,'Shoe','category/category_image/2/2_category_image_16201809540500Nike_Shoe_Red.png',2,1,NULL,'2020-06-10 23:57:18','2020-06-11 00:29:45'),
(3,'Pants','category/category_image/3/3_category_image_16222207719800Supreme_Tee.png',3,2,NULL,'2020-06-11 00:22:22','2020-06-11 00:30:05');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `code_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` decimal(15,2) DEFAULT '0.00',
  `original_price` decimal(15,2) DEFAULT '0.00',
  `discount_percent` double DEFAULT '0',
  `quantity` int(10) DEFAULT NULL,
  `is_promotion` tinyint(1) DEFAULT NULL,
  `is_new_arrival` tinyint(1) DEFAULT NULL,
  `is_popular` tinyint(1) DEFAULT NULL,
  `overview` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `image_path1` varchar(3000) DEFAULT NULL,
  `image_path2` varchar(3000) DEFAULT NULL,
  `image_path3` varchar(3000) DEFAULT NULL,
  `image_path4` varchar(3000) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_brand_id` (`brand_id`),
  KEY `fk_category_id` (`category_id`),
  KEY `fk_sub_category_id` (`sub_category_id`),
  KEY `fk_created_by` (`created_by_id`),
  CONSTRAINT `fk_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_sub_category_id` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`brand_id`,`category_id`,`sub_category_id`,`name`,`code_number`,`price`,`original_price`,`discount_percent`,`quantity`,`is_promotion`,`is_new_arrival`,`is_popular`,`overview`,`detail`,`image_path1`,`image_path2`,`image_path3`,`image_path4`,`status`,`created_by_id`,`created_time`,`updated_time`) values 
(2,1,1,1,'Man T-Shirt','PRD-001',16000.00,0.00,0,NULL,0,NULL,0,'Write some overview about your product !','Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',2,NULL,'2020-06-22 22:25:00','2020-06-24 22:30:16');

/*Table structure for table `product_image` */

DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `color` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_id` (`product_id`),
  KEY `fk_created_by_id` (`created_by_id`),
  CONSTRAINT `fk_created_by_id` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `product_image` */

insert  into `product_image`(`id`,`product_id`,`description`,`color`,`size`,`image_path`,`created_by_id`,`created_time`,`updated_time`) values 
(12,2,NULL,NULL,NULL,'product/product_image/2/12_product_image_52314906329600Dress.png',NULL,'2020-06-23 23:27:36','2020-06-23 23:27:36');

/*Table structure for table `product_size` */

DROP TABLE IF EXISTS `product_size`;

CREATE TABLE `product_size` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `size_name` varchar(100) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `product_id_fk_1` (`product_id`),
  KEY `user_fk_id_1` (`created_by_id`),
  CONSTRAINT `product_id_fk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `user_fk_id_1` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_size` */

/*Table structure for table `sub_category` */

DROP TABLE IF EXISTS `sub_category`;

CREATE TABLE `sub_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sequence` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `category_id` bigint(20) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sub_fk_cat` (`category_id`),
  KEY `sub_fk_created` (`created_by_id`),
  CONSTRAINT `sub_fk_cat` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `sub_fk_created` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `sub_category` */

insert  into `sub_category`(`id`,`name`,`image_path`,`sequence`,`status`,`category_id`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Man Shirt',NULL,1,1,1,1,'2020-06-08 23:38:07','2020-06-15 21:54:17'),
(4,'Short Pants','sub_category/sub_category_image/4/4_sub_category_image_7061632760700Shirt_4.png',1,1,3,NULL,'2020-06-11 21:49:44','2020-06-11 22:24:11');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roles` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `draft_flag` tinyint(2) DEFAULT NULL,
  `record_reg_seq` int(11) DEFAULT NULL,
  `record_update_seq` int(11) DEFAULT NULL,
  `record_reg_date` date DEFAULT NULL,
  `record_upd_date` date DEFAULT NULL,
  `record_tmp_flag` tinyint(2) DEFAULT NULL,
  `record_del_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_seq_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`email`,`status`,`roles`,`avatar`,`draft_flag`,`record_reg_seq`,`record_update_seq`,`record_reg_date`,`record_upd_date`,`record_tmp_flag`,`record_del_flag`) values 
(1,'Zay','$2a$10$N0eqNiuikWCy9ETQ1rdau.XEELcyEO7kukkfoiNISk/9F7gw6eB0W','zay@gmail.com','1','ADMIN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
