CREATE DATABASE  IF NOT EXISTS `TestBilabonnement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `TestBilabonnement`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: TestBilabonnement
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.21.10.2

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
  `stelNummer` varchar(115) NOT NULL,
  `brand` varchar(115) NOT NULL,
  `model` varchar(115) NOT NULL,
  `price` double NOT NULL,
  `isLeased` int DEFAULT NULL,
  PRIMARY KEY (`vognNummer`),
  UNIQUE KEY `vognNummer_UNIQUE` (`vognNummer`),
  UNIQUE KEY `stelNummer_UNIQUE` (`stelNummer`)
) ENGINE=InnoDB AUTO_INCREMENT=3797 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cars`
--

LOCK TABLES `Cars` WRITE;
/*!40000 ALTER TABLE `Cars` DISABLE KEYS */;
INSERT INTO `Cars` VALUES (1,'DA','Volvo','X12',122,0),(2,'DFA','Volvo','X12',3342,1),(3,'MDK8181DJ','Volvo','XO67',8181,1),(4,'MDK2010.09712063186388753','Passat','v12',0.09712063186388753,1),(5,'MDK8201DJ','Mazda','QE50',8201,1),(3788,'MDK2010.3605684858698437','Passat','v12',0.3605684858698437,1),(3789,'MDK6400DJ','Jaguar','MO91',6400,1),(3790,'MDK6385DJ','Tesla','O63',6385,1),(3791,'MDK8864DJ','Honda','R38',8864,1),(3792,'MDK9790DJ','Jaguar','XO1',9790,1),(3793,'MDK7877DJ','Volvo','E62',7877,1),(3794,'MDK6242DJ','Tesla','XO34',6242,1),(3795,'MDK2010.16046223366599877','Passat','v12',0.16046223366599877,1),(3796,'MDK2010.20821850925411456','Passat','v12',0.20821850925411456,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarsLeases`
--

LOCK TABLES `CarsLeases` WRITE;
/*!40000 ALTER TABLE `CarsLeases` DISABLE KEYS */;
INSERT INTO `CarsLeases` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(10,3,3);
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
  `status` int DEFAULT '1',
  PRIMARY KEY (`damageReportID`),
  UNIQUE KEY `damageReportID_UNIQUE` (`damageReportID`),
  KEY `fk_DamageReport_Leases1_idx` (`Leases_leaseID`),
  KEY `fk_DamageReport_Cars1_idx` (`Cars_vognNummer`),
  KEY `fk_DamageReport_Employee1_idx` (`Employee_EmployeeID`),
  CONSTRAINT `fk_DamageReport_Cars1` FOREIGN KEY (`Cars_vognNummer`) REFERENCES `Cars` (`vognNummer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_DamageReport_Employee1` FOREIGN KEY (`Employee_EmployeeID`) REFERENCES `Employee` (`EmployeeID`),
  CONSTRAINT `fk_DamageReport_Leases1` FOREIGN KEY (`Leases_leaseID`) REFERENCES `Leases` (`leaseID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DamageReport`
--

LOCK TABLES `DamageReport` WRITE;
/*!40000 ALTER TABLE `DamageReport` DISABLE KEYS */;
INSERT INTO `DamageReport` VALUES (1,1,1,2,1),(2,1,3,2,1),(3,3,3,2,1),(4,2,2,2,1),(5,4,4,2,1),(18,27,3796,2,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Admin firstName','Admin lastName','+4500000000','admin@admin.dk',1,1),(2,'leje firstName','leje lastName','+4500000000','leje@leje.dk',2,1),(3,'fejl firstName','fejl lastName','+4500000000','fejl@fejl.dk',3,1),(4,'kpi firstName','kpi lastName','+4500000000','kpi@kpi.dk',4,1),(10,'Karl','Hansen','29405521','adm',4,1),(11,'Karl','Hansen','29405521','adm',4,1),(12,'Karl','Hansen','+4528591682','Lonne2@bilabonnement.dk',2,1),(13,'Cay','Hansen','+4571927398','Cay1@bilabonnement.dk',1,1),(14,'Nisse','Adel','+4559661140','Nisse2@bilabonnement.dk',2,1),(15,'test','Holm','+4599076089','test3@bilabonnement.dk',3,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Leases`
--

LOCK TABLES `Leases` WRITE;
/*!40000 ALTER TABLE `Leases` DISABLE KEYS */;
INSERT INTO `Leases` VALUES (1,'Lars','Hansen',1,'2022-01-10','2022-01-03',1),(2,'Thies','Hansen',1,'2022-01-10','2022-02-05',1),(3,'Lars','Hansen',1,'2022-01-10','2022-01-03',1),(4,'Lars','Hansen',1,'2022-01-10','2022-01-03',1),(5,'Lars','Hansen',1,'2022-01-10','2022-01-03',1),(26,'random','Artelldo',2,'2022-01-10','2022-01-20',1),(27,'random','Lue',2,'2022-01-10','2022-08-20',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpecificDamage`
--

LOCK TABLES `SpecificDamage` WRITE;
/*!40000 ALTER TABLE `SpecificDamage` DISABLE KEYS */;
INSERT INTO `SpecificDamage` VALUES (1,5252,'test','default.jpg','Skade på motor',1),(2,2400,'Ridse i laken','default.jpg','Ridse i laken',2),(3,7500,'Hul af bagsæde','default.jpg','Hul ',1),(9,5000,'Olie lækker, hul skal lappes','default.jpg','Olie læk',1),(10,5000,'Olie lækker, hul skal lappes','default.jpg','Olie læk',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserLogin`
--

LOCK TABLES `UserLogin` WRITE;
/*!40000 ALTER TABLE `UserLogin` DISABLE KEYS */;
INSERT INTO `UserLogin` VALUES (1,'admin','admin',4,1),(2,'leje','948238',1,1),(3,'fejl','fejl',2,1),(4,'kpi','kpi',3,1),(11,'NewUserName','newCode',4,1),(13,'Jaguar','newCode',4,1),(14,'Volvo','newCode',4,1),(15,'Tesla','newCode',4,1),(16,'Jaguar19','newCode',4,1),(17,'random269','newCode',4,1);
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

-- Dump completed on 2022-05-31 10:33:36
