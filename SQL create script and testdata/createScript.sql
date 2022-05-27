CREATE DATABASE  IF NOT EXISTS `bilabonnement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bilabonnement`;
-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: localhost    Database: bilabonnement
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cars`
--

DROP TABLE IF EXISTS `Cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cars` (
  `vognNummer` int NOT NULL AUTO_INCREMENT,
  `stelNummer` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `isLeased` int DEFAULT NULL,
  PRIMARY KEY (`vognNummer`),
  UNIQUE KEY `vognNummer_UNIQUE` (`vognNummer`),
  UNIQUE KEY `stelNummer_UNIQUE` (`stelNummer`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CarsLeases`
--

DROP TABLE IF EXISTS `CarsLeases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CarsLeases` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Cars_vognNummer` int NOT NULL,
  `Leases_leaseID` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_CarsLeases_Cars1_idx` (`Cars_vognNummer`),
  KEY `fk_CarsLeases_Leases1_idx` (`Leases_leaseID`),
  CONSTRAINT `fk_CarsLeases_Cars1` FOREIGN KEY (`Cars_vognNummer`) REFERENCES `Cars` (`vognNummer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_CarsLeases_Leases1` FOREIGN KEY (`Leases_leaseID`) REFERENCES `Leases` (`leaseID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DamageReport`
--

DROP TABLE IF EXISTS `DamageReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DamageReport` (
  `damageReportID` int NOT NULL AUTO_INCREMENT,
  `Leases_leaseID` int NOT NULL,
  `Cars_vognNummer` int NOT NULL,
  `Employee_EmployeeID` int NOT NULL,
  PRIMARY KEY (`damageReportID`),
  UNIQUE KEY `damageReportID_UNIQUE` (`damageReportID`),
  KEY `fk_DamageReport_Leases1_idx` (`Leases_leaseID`),
  KEY `fk_DamageReport_Cars1_idx` (`Cars_vognNummer`),
  KEY `fk_DamageReport_Employee1_idx` (`Employee_EmployeeID`),
  CONSTRAINT `fk_DamageReport_Cars1` FOREIGN KEY (`Cars_vognNummer`) REFERENCES `Cars` (`vognNummer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_DamageReport_Employee1` FOREIGN KEY (`Employee_EmployeeID`) REFERENCES `Employee` (`EmployeeID`),
  CONSTRAINT `fk_DamageReport_Leases1` FOREIGN KEY (`Leases_leaseID`) REFERENCES `Leases` (`leaseID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(60) NOT NULL,
  `lastName` varchar(65) NOT NULL,
  `phoneNumber` varchar(60) NOT NULL,
  `eMail` varchar(65) NOT NULL,
  `UserLogin_userID` int NOT NULL,
  `status` int DEFAULT '1' COMMENT '1 = employed\\n0 = not employed',
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `EmployeeID_UNIQUE` (`EmployeeID`),
  KEY `fk_Employee_UserLogin1_idx` (`UserLogin_userID`),
  CONSTRAINT `fk_Employee_UserLogin1` FOREIGN KEY (`UserLogin_userID`) REFERENCES `UserLogin` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Leases`
--

DROP TABLE IF EXISTS `Leases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Leases` (
  `leaseID` int NOT NULL AUTO_INCREMENT,
  `customerFirstName` varchar(65) NOT NULL,
  `customerLastName` varchar(45) NOT NULL,
  `UserLogin_userID` int NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `status` int DEFAULT '1' COMMENT '0 = ikke aktiv\n1 = aktiv',
  PRIMARY KEY (`leaseID`),
  UNIQUE KEY `leaseID_UNIQUE` (`leaseID`),
  KEY `fk_Leases_UserLogin1_idx` (`UserLogin_userID`),
  CONSTRAINT `fk_Leases_UserLogin1` FOREIGN KEY (`UserLogin_userID`) REFERENCES `UserLogin` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SpecificDamage`
--

DROP TABLE IF EXISTS `SpecificDamage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SpecificDamage` (
  `specificDamageID` int NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `picture` varchar(300) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `DamageReport_damageReportID` int NOT NULL,
  PRIMARY KEY (`specificDamageID`),
  UNIQUE KEY `specificDamageID_UNIQUE` (`specificDamageID`),
  KEY `fk_SpecificDamage_DamageReport1_idx` (`DamageReport_damageReportID`),
  CONSTRAINT `fk_SpecificDamage_DamageReport1` FOREIGN KEY (`DamageReport_damageReportID`) REFERENCES `DamageReport` (`damageReportID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserLogin`
--

DROP TABLE IF EXISTS `UserLogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserLogin` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(65) NOT NULL,
  `password` varchar(95) NOT NULL,
  `userType` int NOT NULL,
  `status` int DEFAULT '1' COMMENT '1 = employed\\n0 = unemployed\\n',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26 21:43:02
