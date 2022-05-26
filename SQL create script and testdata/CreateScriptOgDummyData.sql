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
-- Dumping data for table `Cars`
--

LOCK TABLES `Cars` WRITE;
/*!40000 ALTER TABLE `Cars` DISABLE KEYS */;
INSERT INTO `Cars` VALUES (18,'KLNWE234','BMW','X5',100,1),(19,'KLWDOA0234','Mercedes','C63 AMG',2300,1),(20,'KLOMW0923','Opel','Corsa 1.2',200,1),(21,'KWAEL123','BMW','X5',300,1),(22,'EQWEQ213','Opel','X5',200,1),(23,'WQEQWF213','bmw','X3',100,1),(24,'YKDQW123','bmw','X5',200,1),(25,'KQWDL1241','BMW','X5',100,1),(26,'KQWEL0123','BMW','X5',400000,1),(27,'WQDQWD234','OPEL','Corsa 1.2',100,1),(28,'QWDQW1234','OPEL','Corsa 1.2',100,1),(29,'OQWLEQ1234','OPEL','Corsa 1.2',100,1),(30,'PQWEOQW213','OPEL','Corsa 1.2',100,0),(31,'POLQWE123','OPEL','Corsa 1.2',100,0),(32,'ÅPKEWQ213','OPEL','Corsa 1.2',100,0),(33,'PWEQL0932','OPEL','Corsa 1.2',100,0),(34,'IWQDQ234','Mercedes','C63 AMG',200000,1),(35,'POQWEQ23','Mercedes','C63 AMG',2300,1),(36,'OLOLO23','Mercedes','C63 AMG',2300,1),(37,'POPLOP234','Mercedes','C63 AMG',2300,0),(38,'WQDD21324','Opel','X5',200,1),(39,'IWQDKQJ123','Skoda','fibia',100,1),(40,'KQWDQD166','Skoda','fibia',100,1),(41,'OWEQL566','Skoda','fibia',100,0),(42,'KWEQDKL12314','BMW','X5',2300,0),(43,'OLDWQKK2','BMW','X5',2300,0),(44,'OLDWQKK21','BMW','X5',2300,0),(45,'OLDWQKK23','BMW','X5',2300,0),(46,'POPLOP23490','Mercedes','C63 AMG',2300,0),(47,'POPLOP23411','Mercedes','C63 AMG',2300,0),(48,'KWEQDKL123143','BMW','X5',2300,0),(50,'OAPDLAW','Mercedes','C63 AMG',6700,1);
/*!40000 ALTER TABLE `Cars` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `CarsLeases`
--

LOCK TABLES `CarsLeases` WRITE;
/*!40000 ALTER TABLE `CarsLeases` DISABLE KEYS */;
INSERT INTO `CarsLeases` VALUES (10,26,24),(11,27,25),(12,35,26),(13,25,27),(14,29,28),(15,36,29),(16,30,30),(17,28,31),(18,50,32);
/*!40000 ALTER TABLE `CarsLeases` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `DamageReport`
--

LOCK TABLES `DamageReport` WRITE;
/*!40000 ALTER TABLE `DamageReport` DISABLE KEYS */;
INSERT INTO `DamageReport` VALUES (15,30,30,1),(16,24,26,2),(17,25,27,2);
/*!40000 ALTER TABLE `DamageReport` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Admin firstName','Admin lastName','+4500000000','admin@admin.dk',1,1),(2,'leje firstName','leje lastName','+4500000000','leje@leje.dk',2,1),(3,'fejl firstName','fejl lastName','+4500000000','fejl@fejl.dk',3,1),(4,'kpi firstName','kpi lastName','+4500000000','kpi@kpi.dk',4,1);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Leases`
--

LOCK TABLES `Leases` WRITE;
/*!40000 ALTER TABLE `Leases` DISABLE KEYS */;
INSERT INTO `Leases` VALUES (24,'Erik','Jakobsen',2,'2022-05-04','2022-05-25',1),(25,'Kasper','Olsen',2,'2022-05-18','2022-05-25',1),(26,'Karina','Olsen',2,'2022-05-18','2022-06-30',1),(27,'Dagens salg','salg',1,'2022-05-25','2022-06-30',1),(28,'dagens salg2','dagens salg',1,'2022-05-25','2022-05-31',1),(29,'salg for i dag','salg for i dag',1,'2022-05-25','2022-05-31',1),(30,'testmonth','testmonth',1,'2022-06-22','2022-07-20',0),(31,'TestJavascript','TestJavaScript',1,'2022-05-01','2022-06-30',1),(32,'testkpi','testkpi',1,'2022-05-26','2022-05-31',1);
/*!40000 ALTER TABLE `Leases` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `SpecificDamage`
--

LOCK TABLES `SpecificDamage` WRITE;
/*!40000 ALTER TABLE `SpecificDamage` DISABLE KEYS */;
INSERT INTO `SpecificDamage` VALUES (8,1,'testjavascript','Skærmbillede2022-05-23kl.11.17.40.png','testjavascript',15),(9,3,'random billede','Skærmbillede2022-05-23kl.11.17.40.png','KLOK',15),(10,2,'igen','Skærmbillede2022-05-23kl.11.17.40.png','tesbillede',15),(11,3,'igen','Skærmbillede2022-05-23kl.11.17.40.png','testbillede',15),(12,2300,'testicloud','img_avatar2.png','testminusicloud',15),(13,321,'ewfdwf','20020425-4-250h.jpg','Kloksda',15),(14,54,'twef','unknown2022.png','Tetsafd',15);
/*!40000 ALTER TABLE `SpecificDamage` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `UserLogin`
--

LOCK TABLES `UserLogin` WRITE;
/*!40000 ALTER TABLE `UserLogin` DISABLE KEYS */;
INSERT INTO `UserLogin` VALUES (1,'admin','admin',4,1),(2,'leje','leje',1,1),(3,'fejl','fejl',2,1),(4,'kpi','kpi',3,1);
/*!40000 ALTER TABLE `UserLogin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26 21:02:16
