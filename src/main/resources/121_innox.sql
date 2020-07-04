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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eco_shop` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `eco_shop`;

/*Data for the table `banner` */

insert  into `banner`(`id`,`name`,`description`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Banner1','banner 1',NULL,1,0,NULL,'2020-06-06 17:27:01','2020-06-15 21:53:53'),
(2,'Banner 2','banner','banner/banner_image/2/2_banner_image_32489102495500fernando-hernandez-JdoofvUDUwc-unsplash.jpg',2,1,NULL,NULL,NULL),
(4,'Banner3','banner','banner/banner_image/4/4_banner_image_35045715112899fernando-hernandez-JdoofvUDUwc-unsplash.jpg',3,1,NULL,'2020-06-06 21:43:54','2020-06-07 20:48:22');

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Brand 1','brand/brand_image/1/1_brand_image_13390427613100fernando-hernandez-JdoofvUDUwc-unsplash.jpg',1,1,NULL,'2020-06-07 22:18:55',NULL);

/*Data for the table `c2b_layout` */

/*Data for the table `c2b_product` */

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`image_path`,`sequence_no`,`status`,`created_by_id`,`created_time`,`updated_time`,`feature`) values 
(1,'Shirt','category/category_image/1/1_category_image_21108267522700Dress.png',1,1,NULL,'2020-06-08 22:46:10','2020-06-27 15:55:17',1),
(2,'Shoe','category/category_image/2/2_category_image_16201809540500Nike_Shoe_Red.png',2,1,NULL,'2020-06-10 23:57:18','2020-06-11 00:29:45',1),
(3,'Pants','category/category_image/3/3_category_image_16222207719800Supreme_Tee.png',3,2,NULL,'2020-06-11 00:22:22','2020-06-11 00:30:05',1);

/*Data for the table `color` */

insert  into `color`(`id`,`color_code`,`color_name`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'FA2E03','Red',NULL,NULL,NULL),
(2,'61FA03','Green',NULL,NULL,NULL),
(3,'0352FA','Blue',NULL,NULL,NULL),
(4,'FA03BA','Pink',NULL,NULL,NULL),
(5,'F3FA03','Yellow',NULL,NULL,NULL);

/*Data for the table `product` */

insert  into `product`(`id`,`brand_id`,`category_id`,`sub_category_id`,`name`,`code_number`,`price`,`original_price`,`discount_percent`,`quantity`,`is_promotion`,`is_new_arrival`,`is_popular`,`overview`,`detail`,`image_path1`,`image_path2`,`image_path3`,`image_path4`,`status`,`created_by_id`,`created_time`,`updated_time`,`size`,`color`) values 
(2,1,1,1,'Man T-Shirt','PRD-001',16000.00,0.00,0,15,1,0,1,NULL,'Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',1,NULL,'2020-06-22 22:25:00','2020-07-01 23:25:57',NULL,NULL),
(17,1,1,1,'Man Short','PRD-003',16000.00,180000.00,0,20,1,0,1,NULL,'Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',1,NULL,'2020-06-22 22:25:00','2020-07-01 23:07:38',NULL,NULL),
(18,1,1,1,'Girl T-Shirt','PRD-002',16000.00,0.00,0,NULL,1,0,1,NULL,'Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',1,NULL,'2020-06-22 22:25:00','2020-06-27 15:44:22','39,40,41','White,Black');

/*Data for the table `product_color` */

insert  into `product_color`(`id`,`product_id`,`color_id`,`created_by_id`,`created_time`,`updated_time`) values 
(40,2,1,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57'),
(41,2,2,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57'),
(42,2,3,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57');

/*Data for the table `product_image` */

insert  into `product_image`(`id`,`product_id`,`description`,`color`,`size`,`image_path`,`created_by_id`,`created_time`,`updated_time`) values 
(12,2,NULL,NULL,NULL,'product/product_image/2/12_product_image_52314906329600Dress.png',NULL,'2020-06-23 23:27:36','2020-06-23 23:27:36');

/*Data for the table `product_size` */

insert  into `product_size`(`id`,`product_id`,`size_id`,`created_by_id`,`created_time`,`updated_time`) values 
(24,2,1,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57'),
(25,2,2,NULL,'2020-07-01 23:25:57','2020-07-01 23:25:57');

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values 
(1,'ROLE_USER'),
(2,'ROLE_ADMIN'),
(3,'ROLE_DESIGNER');

/*Data for the table `size` */

insert  into `size`(`id`,`size_code`,`size_name`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'0001','Small',1,NULL,NULL),
(2,'0002','Medium',1,NULL,NULL),
(3,'0003','X',1,NULL,NULL),
(4,'0004','XL',1,NULL,NULL);

/*Data for the table `sub_category` */

insert  into `sub_category`(`id`,`name`,`image_path`,`sequence`,`status`,`category_id`,`created_by_id`,`created_time`,`updated_time`) values 
(1,'Man Shirt',NULL,1,1,1,NULL,'2020-06-08 23:38:07','2020-06-15 21:54:17'),
(4,'Short Pants','sub_category/sub_category_image/4/4_sub_category_image_7061632760700Shirt_4.png',1,1,3,NULL,'2020-06-11 21:49:44','2020-06-11 22:24:11');

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`email`,`status`,`avatar`,`draft_flag`,`record_reg_seq`,`record_update_seq`,`record_reg_date`,`record_upd_date`,`record_tmp_flag`,`record_del_flag`) values 
(1,'Zay','$2a$10$N0eqNiuikWCy9ETQ1rdau.XEELcyEO7kukkfoiNISk/9F7gw6eB0W','zay@gmail.com','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Data for the table `users_roles` */

insert  into `users_roles`(`role_id`,`user_id`) values 
(1,1),
(2,1),
(3,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
