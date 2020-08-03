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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`innox` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `innox`;

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
(1,'Banner1','banner 1',NULL,1,0,NULL,'2020-06-06 17:27:01','2020-06-15 21:53:53'),
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
  `feature` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_createdBY` (`created_by_id`),
  CONSTRAINT `fk_createdBY` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`,`feature`) values 
(1,'Shirt','category/category_image/1/1_category_image_21108267522700Dress.png',1,1,NULL,'2020-06-08 22:46:10','2020-06-27 15:55:17',1),
(2,'Shoe','category/category_image/2/2_category_image_16201809540500Nike_Shoe_Red.png',2,1,NULL,'2020-06-10 23:57:18','2020-06-11 00:29:45',1),
(3,'Pants','category/category_image/3/3_category_image_16222207719800Supreme_Tee.png',3,2,NULL,'2020-06-11 00:22:22','2020-06-11 00:30:05',1);

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_id` bigint(20) DEFAULT NULL,
  `city_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `state_fk` (`state_id`),
  CONSTRAINT `state_fk` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `city` */

/*Table structure for table `color` */

DROP TABLE IF EXISTS `color`;

CREATE TABLE `color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color_code` varchar(100) DEFAULT NULL,
  `color_name` varchar(100) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_fk_id` (`created_by_id`),
  CONSTRAINT `created_fk_id` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `color` */

insert  into `color`(`id`,`color_code`,`color_name`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'FA2E03','Red',NULL,NULL,NULL),
(2,'61FA03','Green',NULL,NULL,NULL),
(3,'0352FA','Blue',NULL,NULL,NULL),
(4,'FA03BA','Pink',NULL,NULL,NULL),
(5,'F3FA03','Yellow',NULL,NULL,NULL);

/*Table structure for table `custom_item` */

DROP TABLE IF EXISTS `custom_item`;

CREATE TABLE `custom_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `custom_product_id` bigint(20) DEFAULT NULL,
  `color_id` bigint(20) DEFAULT NULL,
  `item_code` varchar(100) DEFAULT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `item_price` decimal(15,2) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus_prd_fk` (`custom_product_id`),
  KEY `cus_item_color_fk` (`color_id`),
  KEY `cus_item_created_fk` (`created_by_id`),
  CONSTRAINT `cus_item_color_fk` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `cus_item_created_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cus_prd_fk` FOREIGN KEY (`custom_product_id`) REFERENCES `custom_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `custom_item` */

insert  into `custom_item`(`id`,`custom_product_id`,`color_id`,`item_code`,`item_name`,`item_price`,`created_by_id`,`created_time`,`updated_time`,`sequence_no`) values 
(1,1,1,'CIC-001','CIC-001',8500.00,NULL,'2020-07-16 23:54:59','2020-07-26 15:18:18',1);

/*Table structure for table `custom_item_layout` */

DROP TABLE IF EXISTS `custom_item_layout`;

CREATE TABLE `custom_item_layout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `custom_item_id` bigint(20) DEFAULT NULL,
  `layout_name` varchar(255) DEFAULT NULL,
  `layout_price` decimal(15,2) DEFAULT '0.00',
  `layout_image_path` varchar(1000) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `sequence_no` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `layout_cus_item_fk` (`custom_item_id`),
  KEY `layout_created_fk` (`created_by_id`),
  CONSTRAINT `layout_created_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `layout_cus_item_fk` FOREIGN KEY (`custom_item_id`) REFERENCES `custom_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `custom_item_layout` */

insert  into `custom_item_layout`(`id`,`custom_item_id`,`layout_name`,`layout_price`,`layout_image_path`,`status`,`created_by_id`,`created_time`,`updated_time`,`sequence_no`) values 
(10,1,'Layout 1',15000.00,'custom_layout/custom_layout_image/10/10_custom_layout_image_13704221998600IMG_3374.JPG',1,NULL,'2020-07-26 22:40:21','2020-07-26 22:40:21',0);

/*Table structure for table `custom_item_size` */

DROP TABLE IF EXISTS `custom_item_size`;

CREATE TABLE `custom_item_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `custom_item_id` bigint(20) DEFAULT NULL,
  `size_id` bigint(20) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus_size_item_fk` (`custom_item_id`),
  KEY `cus_size_fk` (`size_id`),
  KEY `cus_size_created_fk` (`created_by_id`),
  CONSTRAINT `cus_size_created_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cus_size_fk` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `cus_size_item_fk` FOREIGN KEY (`custom_item_id`) REFERENCES `custom_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `custom_item_size` */

/*Table structure for table `custom_product` */

DROP TABLE IF EXISTS `custom_product`;

CREATE TABLE `custom_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_price` decimal(15,2) DEFAULT '0.00',
  `default_image` varchar(1000) DEFAULT NULL,
  `statue` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus_category_fk` (`category_id`),
  KEY `cus_sub_category_fk` (`sub_category_id`),
  KEY `cus_created_fk` (`created_by_id`),
  CONSTRAINT `cus_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `cus_created_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cus_sub_category_fk` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `custom_product` */

insert  into `custom_product`(`id`,`category_id`,`sub_category_id`,`product_name`,`product_price`,`default_image`,`statue`,`created_by_id`,`created_time`,`updated_time`) values 
(1,2,4,'Test_1',7000.00,'custom_product/custom_product_image/1/1_custom-product_image_781678323640015_brand_image_3362417298368554received_664583367531111.jpeg',1,NULL,'2020-07-12 21:35:49','2020-07-12 21:35:50'),
(2,1,1,'Test_1',7000.00,'custom_product/custom_product_image/2/2_custom-product_image_4697974355400fernando-hernandez-JdoofvUDUwc-unsplash.jpg',1,NULL,'2020-07-15 21:57:20','2020-07-15 21:57:20');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `code_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_german2_ci DEFAULT NULL,
  `price` decimal(15,2) DEFAULT '0.00',
  `original_price` decimal(15,2) DEFAULT '0.00',
  `discount_percent` double DEFAULT '0',
  `quantity` int(10) DEFAULT NULL,
  `is_promotion` tinyint(1) DEFAULT NULL,
  `is_new_arrival` tinyint(1) DEFAULT NULL,
  `is_popular` tinyint(1) DEFAULT NULL,
  `overview` longtext CHARACTER SET utf8 COLLATE utf8_german2_ci,
  `detail` longtext CHARACTER SET utf8 COLLATE utf8_german2_ci,
  `image_path1` varchar(3000) DEFAULT NULL,
  `image_path2` varchar(3000) DEFAULT NULL,
  `image_path3` varchar(3000) DEFAULT NULL,
  `image_path4` varchar(3000) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_brand_id` (`brand_id`),
  KEY `fk_category_id` (`category_id`),
  KEY `fk_sub_category_id` (`sub_category_id`),
  KEY `fk_created_by` (`created_by_id`),
  CONSTRAINT `fk_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_sub_category_id` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`brand_id`,`category_id`,`sub_category_id`,`name`,`code_number`,`price`,`original_price`,`discount_percent`,`quantity`,`is_promotion`,`is_new_arrival`,`is_popular`,`overview`,`detail`,`image_path1`,`image_path2`,`image_path3`,`image_path4`,`status`,`created_by_id`,`created_time`,`updated_time`,`size`,`color`) values 
(2,1,1,1,'Man T-Shirt','PRD-001',16000.00,0.00,0,15,1,0,1,NULL,'Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',1,NULL,'2020-06-22 22:25:00','2020-07-01 23:25:57',NULL,NULL),
(17,1,1,1,'Man Short','PRD-003',16000.00,180000.00,0,20,1,0,1,NULL,'Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',1,NULL,'2020-06-22 22:25:00','2020-07-01 23:07:38',NULL,NULL),
(18,1,1,1,'Girl T-Shirt','PRD-002',16000.00,0.00,0,100,0,0,1,NULL,'Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',1,NULL,'2020-06-22 22:25:00','2020-07-29 23:54:53',NULL,NULL);

/*Table structure for table `product_color` */

DROP TABLE IF EXISTS `product_color`;

CREATE TABLE `product_color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `color_id` bigint(20) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id_fk_1` (`product_id`),
  KEY `color_id_fk` (`color_id`),
  KEY `created_by_id_fk` (`created_by_id`),
  CONSTRAINT `color_id_fk` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `created_by_id_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `product_id_fk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `product_color` */

insert  into `product_color`(`id`,`product_id`,`color_id`,`created_by_id`,`created_time`,`updated_time`) values 
(40,2,1,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57'),
(41,2,2,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57'),
(42,2,3,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57');

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `size_id` bigint(20) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prodcut_fk_id` (`product_id`),
  KEY `size_fk_id` (`size_id`),
  KEY `created_by_fk_id` (`created_by_id`),
  CONSTRAINT `created_by_fk_id` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `prodcut_fk_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `size_fk_id` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

/*Data for the table `product_size` */

insert  into `product_size`(`id`,`product_id`,`size_id`,`created_by_id`,`created_time`,`updated_time`) values 
(24,2,1,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57'),
(25,2,2,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values 
(9,'ROLE_USER'),
(10,'ROLE_ADMIN');

/*Table structure for table `size` */

DROP TABLE IF EXISTS `size`;

CREATE TABLE `size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `size_name` varchar(100) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_fk_id_1` (`created_by_id`),
  CONSTRAINT `user_fk_id_1` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `size` */

insert  into `size`(`id`,`size_code`,`size_name`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'0001','Small',NULL,NULL,NULL),
(2,'0002','Medium',NULL,NULL,NULL),
(3,'0003','X',NULL,NULL,NULL),
(4,'0004','XL',NULL,NULL,NULL);

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state_no` int(150) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `state_created_by_fk` (`created_by_id`),
  CONSTRAINT `state_created_by_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `state` */

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
(1,'Man Shirt',NULL,1,1,1,NULL,'2020-06-08 23:38:07','2020-06-15 21:54:17'),
(4,'Short Pants','sub_category/sub_category_image/4/4_sub_category_image_7061632760700Shirt_4.png',1,1,2,NULL,'2020-06-11 21:49:44','2020-06-11 22:24:11');

/*Table structure for table `township` */

DROP TABLE IF EXISTS `township`;

CREATE TABLE `township` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_id` bigint(20) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `zone_id` bigint(20) DEFAULT NULL,
  `township_name` varchar(255) DEFAULT NULL,
  `nrc_name` varchar(100) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `state_id_fk` (`state_id`),
  KEY `city_id_fk` (`city_id`),
  KEY `zone_id_fk` (`zone_id`),
  KEY `township_created_fk` (`created_by_id`),
  CONSTRAINT `city_id_fk` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`),
  CONSTRAINT `township_created_fk` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `zone_id_fk` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `township` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(1) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`email`,`status`,`avatar`,`draft_flag`,`record_reg_seq`,`record_update_seq`,`record_reg_date`,`record_upd_date`,`record_tmp_flag`,`record_del_flag`) values 
(3,'admin','$2a$10$n8VFVsXhAAOOiVhpT/bON.lSRJjJiY83Bo0Zr73Hc00nvZPWv/pYO','admin@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `users_roles` */

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `role_name_UNIQUE` (`role_id`),
  KEY `fk_user_role_1_idx` (`user_id`),
  CONSTRAINT `fk_users_role_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_users_role_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users_roles` */

insert  into `users_roles`(`role_id`,`user_id`) values 
(9,3),
(10,3);

/*Table structure for table `zone` */

DROP TABLE IF EXISTS `zone`;

CREATE TABLE `zone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zone_code` varchar(150) DEFAULT NULL,
  `zone_name` varchar(150) DEFAULT NULL,
  `delivery_fee` decimal(15,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_required_address` tinyint(1) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zone` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
