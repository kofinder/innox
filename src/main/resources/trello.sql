SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema trello
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `trello` ;

-- -----------------------------------------------------
-- Schema trello
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trello` DEFAULT CHARACTER SET utf8 ;
USE `trello` ;

-- -----------------------------------------------------
-- Table `trello`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trello`.`User` ;

CREATE TABLE IF NOT EXISTS `trello`.`User` (
  `userSeq` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NULL,
  `roles` VARCHAR(45) NULL,
  `avatar` VARCHAR(45) NULL,
  `draftFlag` TINYINT(2) NULL,
  `recordRegSeq` INT NULL,
  `recordUpdateSeq` INT NULL,
  `recordRegDate` DATE NULL,
  `recordUpdDate` DATE NULL,
  `recordTmpFlag` TINYINT(2) NULL,
  `recordDelFlag` TINYINT(2) NULL,
  PRIMARY KEY (`userSeq`),
  UNIQUE INDEX `user_seq_UNIQUE` (`userSeq` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trello`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trello`.`Category` ;

CREATE TABLE IF NOT EXISTS `trello`.`Category` (
  `categorySeq` INT NOT NULL AUTO_INCREMENT,
  `userSeq` INT NOT NULL,
  `taskName` VARCHAR(45) NOT NULL,
  `taskTitle` VARCHAR(45) NOT NULL,
  `draftFlag` TINYINT(2) NULL,
  `recordRegSeq` INT NULL,
  `recordUpdateSeq` INT NULL,
  `recordRegDate` DATE NULL,
  `recordUpdDate` DATE NULL,
  `recordTmpFlag` TINYINT(2) NULL,
  `recordDelFlag` TINYINT(2) NULL,
  PRIMARY KEY (`categorySeq`),
  UNIQUE INDEX `category_seq_UNIQUE` (`categorySeq` ASC),
  INDEX `fk_Category_User_idx` (`userSeq` ASC),
  CONSTRAINT `fk_Category_User`
    FOREIGN KEY (`userSeq`)
    REFERENCES `trello`.`User` (`userSeq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trello`.`Todo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trello`.`Todo` ;

CREATE TABLE IF NOT EXISTS `trello`.`Todo` (
  `todoSeq` INT NOT NULL AUTO_INCREMENT,
  `categorySeq` INT NOT NULL,
  `todoProgress` INT NULL,
  `description` VARCHAR(45) NULL,
  `draftFlag` TINYINT(2) NULL,
  `recordRegSeq` INT NULL,
  `recordUpdateSeq` INT NULL,
  `recordRegDate` DATE NULL,
  `recordUpdDate` DATE NULL,
  `recordTmpFlag` TINYINT(2) NULL,
  `recordDelFlag` TINYINT(2) NULL,
  PRIMARY KEY (`todoSeq`),
  UNIQUE INDEX `todo_seq_UNIQUE` (`todoSeq` ASC),
  INDEX `fk_Todo_Category1_idx` (`categorySeq` ASC),
  CONSTRAINT `fk_Todo_Category1`
    FOREIGN KEY (`categorySeq`)
    REFERENCES `trello`.`Category` (`categorySeq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
