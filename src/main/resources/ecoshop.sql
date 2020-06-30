-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: eco-shop
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_path` varchar(1000) DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_created_by_user` (`created_by_id`),
  CONSTRAINT `fk_created_by_user` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,'Banner1','banner 1',NULL,1,0,1,'2020-06-06 17:27:01','2020-06-15 21:53:53'),(2,'Banner 2','banner','banner/banner_image/2/2_banner_image_32489102495500fernando-hernandez-JdoofvUDUwc-unsplash.jpg',2,1,NULL,NULL,NULL),(4,'Banner3','banner','banner/banner_image/4/4_banner_image_35045715112899fernando-hernandez-JdoofvUDUwc-unsplash.jpg',3,1,NULL,'2020-06-06 21:43:54','2020-06-07 20:48:22');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image_path` varchar(1000) DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `brand_fk_createdBy` (`created_by_id`),
  CONSTRAINT `brand_fk_createdBy` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Brand 1','brand/brand_image/1/1_brand_image_13390427613100fernando-hernandez-JdoofvUDUwc-unsplash.jpg',1,1,NULL,'2020-06-07 22:18:55',NULL);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c2b_layout`
--

DROP TABLE IF EXISTS `c2b_layout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c2b_layout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `image_path` varchar(1000) DEFAULT NULL,
  `sequence_no` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c2b_layout`
--

LOCK TABLES `c2b_layout` WRITE;
/*!40000 ALTER TABLE `c2b_layout` DISABLE KEYS */;
/*!40000 ALTER TABLE `c2b_layout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c2b_product`
--

DROP TABLE IF EXISTS `c2b_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c2b_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c2b_product`
--

LOCK TABLES `c2b_product` WRITE;
/*!40000 ALTER TABLE `c2b_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `c2b_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image_path` varchar(1000) DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Active',
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_createdBY` (`created_by_id`),
  CONSTRAINT `fk_createdBY` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Shirt','category/category_image/1/1_category_image_16181950059300Casual.png',1,1,1,'2020-06-08 22:46:10','2020-06-15 21:54:12'),(2,'Shoe','category/category_image/2/2_category_image_16201809540500Nike_Shoe_Red.png',2,1,NULL,'2020-06-10 23:57:18','2020-06-11 00:29:45'),(3,'Pants','category/category_image/3/3_category_image_16222207719800Supreme_Tee.png',3,2,NULL,'2020-06-11 00:22:22','2020-06-11 00:30:05');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `code_number` varchar(100) DEFAULT NULL,
  `price` decimal(15,2) DEFAULT '0.00',
  `original_price` decimal(15,2) DEFAULT '0.00',
  `discount_percent` double DEFAULT '0',
  `quantity` int(10) DEFAULT NULL,
  `is_promotion` tinyint(1) DEFAULT NULL,
  `is_new_arrival` tinyint(1) DEFAULT NULL,
  `is_popular` tinyint(1) DEFAULT NULL,
  `overview` longtext,
  `detail` longtext,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,1,1,1,'Man T-Shirt','PRD-001',16000.00,0.00,0,NULL,0,NULL,0,'Write some overview about your product !','Write some overview about your product !','product/product_image/2/2_product_image_9697653004300Vans.png','product/product_image/2/2_product_image_9676296366100Nike_Shirt.png','product/product_image/2/2_product_image_9687188775000Shoes.png','product/product_image/2/2_product_image_9687192190800Shirt_5.png',2,NULL,'2020-06-22 22:25:00','2020-06-24 22:30:16');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `image_path` varchar(1000) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_id` (`product_id`),
  KEY `fk_created_by_id` (`created_by_id`),
  CONSTRAINT `fk_created_by_id` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (12,2,NULL,NULL,NULL,'product/product_image/2/12_product_image_52314906329600Dress.png',NULL,'2020-06-23 23:27:36','2020-06-23 23:27:36');
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'ROLE_USER'),(4,'ROLE_ADMIN'),(5,'ROLE_USER'),(6,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_category`
--

DROP TABLE IF EXISTS `sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image_path` varchar(1000) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_category`
--

LOCK TABLES `sub_category` WRITE;
/*!40000 ALTER TABLE `sub_category` DISABLE KEYS */;
INSERT INTO `sub_category` VALUES (1,'Man Shirt',NULL,1,1,1,1,'2020-06-08 23:38:07','2020-06-15 21:54:17'),(4,'Short Pants','sub_category/sub_category_image/4/4_sub_category_image_7061632760700Shirt_4.png',1,1,3,NULL,'2020-06-11 21:49:44','2020-06-11 22:24:11');
/*!40000 ALTER TABLE `sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `draft_flag` tinyint(2) DEFAULT NULL,
  `record_reg_seq` int(11) DEFAULT NULL,
  `record_update_seq` int(11) DEFAULT NULL,
  `record_reg_date` date DEFAULT NULL,
  `record_upd_date` date DEFAULT NULL,
  `record_tmp_flag` tinyint(2) DEFAULT NULL,
  `record_del_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_seq_UNIQUE` (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Zay','$2a$10$N0eqNiuikWCy9ETQ1rdau.XEELcyEO7kukkfoiNISk/9F7gw6eB0W','zay@gmail.com','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'theinlwin','$2a$10$1oaxCYY9TqoU2kE.It8P2OBh6Z8RLedNY8KjzvjALk6TYN52UprH.','aungbohtaik@m.bnkcapital.net',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'jameslwin','$2a$10$p.Hslfpiy/wfNHu9knZNseOTDU0Z4c9DRMXyxtJIOCs2fltnLLGLe','finderbar.theinlwin@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'admin','$2a$10$9c18AOgbK6j0RaHj8TsKluIarI31WO5uCckFM2TONwze0pLN4wuva','admin@finderbar.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `role_name_UNIQUE` (`role_id`),
  KEY `fk_user_role_1_idx` (`user_id`),
  CONSTRAINT `fk_users_role_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_role_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,4),(2,4),(3,5),(4,5),(5,6),(6,6);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-30 10:51:39
