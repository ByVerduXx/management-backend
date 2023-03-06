-- MySQL Script generated by MySQL Workbench
-- Wed Feb 22 10:59:15 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Table `ofc`.`concerts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`concerts` ;

CREATE TABLE IF NOT EXISTS `ofc`.`concerts` (
                                                `id_concert` INT NOT NULL AUTO_INCREMENT,
                                                `title` VARCHAR(45) NULL,
                                                `description` LONGTEXT NULL,
                                                `date` DATETIME NULL,
                                                `soundcheck` DATETIME NULL,
                                                `scores` VARCHAR(2083) NULL,
                                                PRIMARY KEY (`id_concert`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ofc`.`rehersals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`rehersals` ;

CREATE TABLE IF NOT EXISTS `ofc`.`rehersals` (
                                                 `id_rehersal` INT NOT NULL AUTO_INCREMENT,
                                                 `date` DATETIME NULL,
                                                 `place` VARCHAR(200) NULL,
                                                 `id_concert` INT NOT NULL,
                                                 PRIMARY KEY (`id_rehersal`, `id_concert`),
                                                 INDEX `fk_ensayos_conciertos_idx` (`id_concert` ASC) VISIBLE,
                                                 CONSTRAINT `fk_ensayos_conciertos`
                                                     FOREIGN KEY (`id_concert`)
                                                         REFERENCES `ofc`.`concerts` (`id_concert`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ofc`.`instruments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`instruments` ;

CREATE TABLE IF NOT EXISTS `ofc`.`instruments` (
                                                   `id_instrument` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(45) NULL,
                                                   PRIMARY KEY (`id_instrument`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ofc`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`users` ;

CREATE TABLE IF NOT EXISTS `ofc`.`users` (
                                             `id_user` INT NOT NULL AUTO_INCREMENT,
                                             `name` VARCHAR(45) NOT NULL,
                                             `last_name` VARCHAR(100) NOT NULL,
                                             `username` VARCHAR(45) NOT NULL,
                                             `password` VARCHAR(256) NOT NULL,
                                             `position` VARCHAR(45) NULL,
                                             `id_instrument` INT NULL,
                                             PRIMARY KEY (`id_user`),
                                             INDEX `fk_users_instruments1_idx` (`id_instrument` ASC) VISIBLE,
                                             CONSTRAINT `fk_users_instruments1`
                                                 FOREIGN KEY (`id_instrument`)
                                                     REFERENCES `ofc`.`instruments` (`id_instrument`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ofc`.`musicians_concerts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`musicians_concerts` ;

CREATE TABLE IF NOT EXISTS `ofc`.`musicians_concerts` (
                                                          `id_concert` INT NOT NULL,
                                                          `role` VARCHAR(45) NULL,
                                                          `payment` DECIMAL NULL,
                                                          `id_musician` INT NOT NULL,
                                                          `accepted` TINYINT NULL,
                                                          `pending` TINYINT NULL,
                                                          PRIMARY KEY (`id_concert`, `id_musician`),
                                                          INDEX `fk_musicians_concerts_users1_idx` (`id_musician` ASC) VISIBLE,
                                                          CONSTRAINT `fk_musicos_conciertos_conciertos1`
                                                              FOREIGN KEY (`id_concert`)
                                                                  REFERENCES `ofc`.`concerts` (`id_concert`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `fk_musicians_concerts_users1`
                                                              FOREIGN KEY (`id_musician`)
                                                                  REFERENCES `ofc`.`users` (`id_user`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ofc`.`announcements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`announcements` ;

CREATE TABLE IF NOT EXISTS `ofc`.`announcements` (
                                                     `id_announcement` INT NOT NULL AUTO_INCREMENT,
                                                     `title` VARCHAR(45) NULL,
                                                     `content` LONGTEXT NULL,
                                                     `date` DATETIME NULL,
                                                     `id_user` INT NOT NULL,
                                                     PRIMARY KEY (`id_announcement`),
                                                     INDEX `fk_anouncements_users1_idx` (`id_user` ASC) VISIBLE,
                                                     CONSTRAINT `fk_anouncements_users1`
                                                         FOREIGN KEY (`id_user`)
                                                             REFERENCES `ofc`.`users` (`id_user`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ofc`.`notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ofc`.`notifications` ;

CREATE TABLE IF NOT EXISTS `ofc`.`notifications` (
                                                     `id_notification` INT NOT NULL AUTO_INCREMENT,
                                                     `title` VARCHAR(45) NULL,
                                                     `content` LONGTEXT NULL,
                                                     `date` DATETIME NULL,
                                                     `id_concert` INT NOT NULL,
                                                     `id_musician` INT NOT NULL,
                                                     PRIMARY KEY (`id_notification`),
                                                     INDEX `fk_notifications_musicians_concerts1_idx` (`id_concert` ASC, `id_musician` ASC) VISIBLE,
                                                     CONSTRAINT `fk_notifications_musicians_concerts1`
                                                         FOREIGN KEY (`id_concert` , `id_musician`)
                                                             REFERENCES `ofc`.`musicians_concerts` (`id_concert` , `id_musician`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
