CREATE DATABASE  IF NOT EXISTS `ks0` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ks0`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: ks0
-- ------------------------------------------------------
-- Server version	5.5.15

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
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `url` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT '0',
  `joinedDate` varchar(45) DEFAULT '0',
  `art` int(11) DEFAULT '0',
  `comics` int(11) DEFAULT '0',
  `dance` int(11) DEFAULT '0',
  `design` int(11) DEFAULT '0',
  `fashion` int(11) DEFAULT '0',
  `filmandvideo` int(11) DEFAULT '0',
  `food` int(11) DEFAULT '0',
  `games` int(11) DEFAULT '0',
  `music` int(11) DEFAULT '0',
  `photography` int(11) DEFAULT '0',
  `publishing` int(11) DEFAULT '0',
  `technology` int(11) DEFAULT '0',
  `theator` int(11) DEFAULT '0',
  `location` varchar(100) DEFAULT NULL,
  `totalprojects` int(11) DEFAULT '0',
  `created` int(11) DEFAULT '0',
  `ks_id` int(11) NOT NULL DEFAULT '0',
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ks_id`,`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `url` varchar(200) NOT NULL DEFAULT '',
  `goal` double DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `pledgedAmount` varchar(45) DEFAULT NULL,
  `percentageRaised` varchar(45) DEFAULT NULL,
  `postedDate` varchar(45) DEFAULT NULL,
  `endedDate` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `proId` varchar(45) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `backers` int(11) DEFAULT NULL,
  `daysToGo` varchar(45) DEFAULT NULL,
  `duration` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile_projects`
--

DROP TABLE IF EXISTS `profile_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_projects` (
  `profileId` int(11) NOT NULL,
  `projectId` varchar(200) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3212 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-19 23:35:44
