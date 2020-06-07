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
  `Seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `imagePath` varchar(1000) DEFAULT NULL,
  `SequenceNo` int(10) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `CreatedBy_Id` bigint(20) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `UpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`),
  KEY `banner_user_fk` (`CreatedBy_Id`),
  CONSTRAINT `banner_user_fk` FOREIGN KEY (`CreatedBy_Id`) REFERENCES `user` (`userSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `banner` */

insert  into `banner`(`Seq`,`Name`,`Description`,`imagePath`,`SequenceNo`,`Status`,`CreatedBy_Id`,`CreatedTime`,`UpdatedTime`) values 
(1,'Banner1','banner 1',NULL,1,1,1,'2020-06-06 17:27:01','2020-06-07 20:46:16'),
(2,'Banner 2','banner','banner/banner_image/2/2_banner_image_32489102495500fernando-hernandez-JdoofvUDUwc-unsplash.jpg',2,1,NULL,NULL,NULL),
(4,'Banner3','banner','banner/banner_image/4/4_banner_image_35045715112899fernando-hernandez-JdoofvUDUwc-unsplash.jpg',3,1,NULL,'2020-06-06 21:43:54','2020-06-07 20:48:22');

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `Seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `ImagePath` varchar(1000) DEFAULT NULL,
  `Sequence` int(10) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `CreatedBy_Id` bigint(20) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `UpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`),
  KEY `brand_fk_createdBy` (`CreatedBy_Id`),
  CONSTRAINT `brand_fk_createdBy` FOREIGN KEY (`CreatedBy_Id`) REFERENCES `user` (`userSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `brand` */

insert  into `brand`(`Seq`,`Name`,`ImagePath`,`Sequence`,`Status`,`CreatedBy_Id`,`CreatedTime`,`UpdatedTime`) values 
(1,'Brand 1','brand/brand_image/1/1_brand_image_13390427613100fernando-hernandez-JdoofvUDUwc-unsplash.jpg',1,1,NULL,'2020-06-07 22:18:55',NULL);

/*Table structure for table `c2b_layout` */

DROP TABLE IF EXISTS `c2b_layout`;

CREATE TABLE `c2b_layout` (
  `Seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Price` decimal(15,2) DEFAULT NULL,
  `ImagePath` varchar(1000) DEFAULT NULL,
  `Sequence` int(1) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `CreatedBy` bigint(20) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `UpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c2b_layout` */

/*Table structure for table `c2b_product` */

DROP TABLE IF EXISTS `c2b_product`;

CREATE TABLE `c2b_product` (
  `Seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Product_Code` varchar(255) DEFAULT NULL,
  `Price` decimal(15,2) DEFAULT NULL,
  `Sequence` int(1) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Active',
  `Category_Id` bigint(20) DEFAULT NULL,
  `SubCategory_Id` bigint(20) DEFAULT NULL,
  `CreatedBy` bigint(20) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `UpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`),
  KEY `cat_fk_` (`Category_Id`),
  KEY `sub_cat_fk_` (`SubCategory_Id`),
  KEY `create_fk_` (`CreatedBy`),
  CONSTRAINT `cat_fk_` FOREIGN KEY (`Category_Id`) REFERENCES `category` (`Seq`),
  CONSTRAINT `create_fk_` FOREIGN KEY (`CreatedBy`) REFERENCES `user` (`userSeq`),
  CONSTRAINT `sub_cat_fk_` FOREIGN KEY (`SubCategory_Id`) REFERENCES `sub_category` (`Seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c2b_product` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `Seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `ImagePath` varchar(1000) DEFAULT NULL,
  `Sequence` int(10) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Active',
  `CreatedBy_Id` bigint(20) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `UpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `sub_category` */

DROP TABLE IF EXISTS `sub_category`;

CREATE TABLE `sub_category` (
  `Seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `ImagePath` varchar(1000) DEFAULT NULL,
  `Sequence` int(1) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL COMMENT '1 = Active / 2 = Inactive',
  `Category_Id` bigint(20) DEFAULT NULL,
  `CreatedBy_Id` bigint(20) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `UpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`),
  KEY `sub_fk_cat` (`Category_Id`),
  KEY `sub_fk_created` (`CreatedBy_Id`),
  CONSTRAINT `sub_fk_cat` FOREIGN KEY (`Category_Id`) REFERENCES `category` (`Seq`),
  CONSTRAINT `sub_fk_created` FOREIGN KEY (`CreatedBy_Id`) REFERENCES `user` (`userSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sub_category` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userSeq` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `roles` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `avatar` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `draftFlag` tinyint(2) DEFAULT NULL,
  `recordRegSeq` int(11) DEFAULT NULL,
  `recordUpdateSeq` int(11) DEFAULT NULL,
  `recordRegDate` date DEFAULT NULL,
  `recordUpdDate` date DEFAULT NULL,
  `recordTmpFlag` tinyint(2) DEFAULT NULL,
  `recordDelFlag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`userSeq`),
  UNIQUE KEY `user_seq_UNIQUE` (`userSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userSeq`,`userName`,`password`,`email`,`status`,`roles`,`avatar`,`draftFlag`,`recordRegSeq`,`recordUpdateSeq`,`recordRegDate`,`recordUpdDate`,`recordTmpFlag`,`recordDelFlag`) values 
(1,'Zay','$2a$10$N0eqNiuikWCy9ETQ1rdau.XEELcyEO7kukkfoiNISk/9F7gw6eB0W','zay@gmail.com','1','ADMIN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
