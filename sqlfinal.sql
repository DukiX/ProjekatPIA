CREATE DATABASE  IF NOT EXISTS `projekatv1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `projekatv1`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projekatv1
-- ------------------------------------------------------
-- Server version	5.6.40-log

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
-- Table structure for table `donatorskiug`
--

DROP TABLE IF EXISTS `donatorskiug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donatorskiug` (
  `iddonatorskiug` int(11) NOT NULL,
  `idkomp` int(11) NOT NULL,
  `idpaket` int(11) NOT NULL,
  `procvrednost` double NOT NULL,
  `opis` text NOT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `datumugovora` date NOT NULL,
  `idstatus` int(11) NOT NULL,
  `datumisporuke` date NOT NULL,
  `komentar` text,
  `datumisticanjapaketa` date NOT NULL,
  PRIMARY KEY (`iddonatorskiug`),
  UNIQUE KEY `iddonatorskiug_UNIQUE` (`iddonatorskiug`),
  KEY `idkompdon_idx` (`idkomp`),
  KEY `idpakdon_idx` (`idpaket`),
  KEY `idstatdon_idx` (`idstatus`),
  CONSTRAINT `idkompdon` FOREIGN KEY (`idkomp`) REFERENCES `kompanija` (`idkompanija`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idpakdon` FOREIGN KEY (`idpaket`) REFERENCES `paket` (`idpaket`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idstatdon` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donatorskiug`
--

LOCK TABLES `donatorskiug` WRITE;
/*!40000 ALTER TABLE `donatorskiug` DISABLE KEYS */;
INSERT INTO `donatorskiug` VALUES (1,5,1,50000,'Kompjuterska oprema',100,'2016-08-10',5,'2016-11-03','','2018-08-10'),(2,6,2,35000,'Procesori',150,'2017-04-13',5,'2017-06-06','','2018-04-13'),(3,7,3,1000,'Cokolade',500,'2017-08-11',5,'2017-09-08','','2018-08-11'),(4,8,3,20000,'Laboratoriska oprema',200,'2018-05-24',2,'2018-06-07','','2019-05-24'),(5,9,2,100000,'Telefonska centrala',2,'2017-08-04',5,'2017-08-25','','2018-08-04'),(6,12,2,100000,'Kompjuterski monitori',100,'2016-09-07',6,'2016-12-08','','2017-09-07'),(7,13,3,50000,'Razni softver',200,'2018-03-09',2,'2018-06-28','','2019-03-09'),(8,12,1,100000,'Softver ove kompanije',300,'2018-03-07',5,'2018-04-04','','2020-03-07'),(9,1,3,50000,'Racunari',50,'2017-10-04',5,'2017-11-03','','2018-10-04'),(10,2,2,60000,'Monitori',70,'2017-10-31',5,'2018-01-05','','2018-10-31'),(11,3,2,20000,'Razna oprema',0,'2017-06-30',5,'2017-07-19','','2018-06-30'),(12,4,2,30000,'Mrezna oprema',50,'2017-07-13',5,'2017-08-18','','2018-07-13'),(13,5,3,10000,'Razvojni softver',10,'2018-05-11',4,'2018-07-20','','2019-05-11'),(14,6,2,20000,'Procesori',30,'2016-02-04',6,'2016-09-07','','2017-02-04'),(15,7,3,5000,'Cokoladice',4000,'2017-10-06',5,'2017-11-03','','2018-10-06'),(16,8,2,10000,'Lab oprema',200,'2018-01-05',5,'2018-05-02','','2019-01-05'),(17,9,2,20000,'Oprema za telekomunikacije',20,'2017-04-05',6,'2017-06-06','','2018-04-05'),(18,10,3,10000,'Oprema za telekomunikacije',20,'2017-02-09',6,'2017-06-06','','2018-02-09'),(19,11,2,20000,'Microsoft office',200,'2017-05-12',6,'2017-06-07','','2018-05-12'),(20,12,1,20000,'Hardver',300,'2016-05-19',6,'2016-06-15','','2018-05-19'),(21,13,1,10000,'Hardver',200,'2016-03-08',6,'2016-04-04','','2018-03-08'),(22,14,1,10000,'Administracioni softver',1,'2016-02-10',6,'2016-03-17','','2018-02-10'),(23,15,1,150000,'Laboratorijski materijal',500,'2016-09-06',5,'2016-09-06','','2018-09-06');
/*!40000 ALTER TABLE `donatorskiug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kompanija`
--

DROP TABLE IF EXISTS `kompanija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kompanija` (
  `idkompanija` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  `grad` varchar(45) NOT NULL,
  `postanskibroj` int(11) NOT NULL,
  `zemlja` varchar(45) NOT NULL,
  `ziroracun` varchar(100) NOT NULL,
  `valuta` varchar(5) NOT NULL,
  `pib` varchar(100) NOT NULL,
  `telefon1` varchar(45) NOT NULL,
  `telefon2` varchar(45) DEFAULT NULL,
  `telefon3` varchar(45) DEFAULT NULL,
  `telefon4` varchar(45) DEFAULT NULL,
  `telefon5` varchar(45) DEFAULT NULL,
  `email1` varchar(100) NOT NULL,
  `email2` varchar(100) DEFAULT NULL,
  `email3` varchar(100) DEFAULT NULL,
  `email4` varchar(100) DEFAULT NULL,
  `email5` varchar(100) DEFAULT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `kontaktemail` varchar(100) NOT NULL,
  `kontakttel` varchar(45) NOT NULL,
  `logolink` mediumtext,
  `webadresa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idkompanija`),
  UNIQUE KEY `idkompanija_UNIQUE` (`idkompanija`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kompanija`
--

LOCK TABLES `kompanija` WRITE;
/*!40000 ALTER TABLE `kompanija` DISABLE KEYS */;
INSERT INTO `kompanija` VALUES (1,'Intel','2200 Mission College Blvd','Santa Clara',95050,'USA','123456789','USD','123456789','+1 408-765-8080','+1 408-765-8081','+1 408-765-8082','','','intel@gmail.com','intel2@gmail.com','intel3@gmail.com','','','Gordon','Moore','gordonmoore@gmail.com','+1 408-765-1234','logoIntel.png','https://www.intel.com/'),(2,'NVidia','2788 San Thomas Expy','Santa Clara',95051,'USA','123456789','USD','123456789','+1 408-486-2000','+1 408-486-2001','+1 408-486-2002','','','nvidia@gmail.com','nvidia2@gmail.com','nvidia3@gmail.com','','','Jensen','Huang','jensenghuang@gmail.com','+1 408-468-1234','logoNVidia.png','http://www.nvidia.com/'),(3,'Kingston','17600 Newgope St','Fountain Valley',92708,'USA','123456789','USD','123456789','+1 714-435-2600','+1 714-435-2601','','','','kingston@gmail.com','kingston2@gmail.com','','','','John','Tu','johntu@gmail.com','+1 714-435-1234','logoKingston.png','https://www.kingston.com/'),(4,'Micro-Star International','69 Lide Street','New Taipei City',12345,'Taiwan','123456789','NTD','123456789','+886 2 3234 5599','+886 2 3234 5598','+886 2 3234 5597','','','msi@gmail.com','','','','','Hrishikesh',' Kumar','kumar@gmail.com','+886 2 3234 5597','logoMicro-Star International.png','https://msi.com/'),(5,'Nordeus','Bulevar Mihajla Pupina 165v','Beograd',11070,'Srbija','123456789','RSD','123456789','+381 64 1234567','+381 64 1234568','','','','nordeus@gmail.com','','','','','Branko','Milutinovic','branko@gmail.com','+381 64 1234567','logoNordeus.png','https://nordeus.com/'),(6,'AMD','Stewart Dr','Sunnyvale, CA',94058,'USA','123456789','USD','123456789','+886 2 3234 5599','','','','','amd@gmail.com','','','','','Lisa','Su','lisasu@gmail.com','+886 2 3234 5599','logoAMD.png','https://www.amd.com/'),(7,'Soko Stark','Milisava Djurovica 249','Beograd',11000,'Srbija','123456789','RSD','123456789','011 3956000','011 3956001','','','','sokostark@gmail.com','sokostark2@gmail.com','sokostark3@gmail.com','sokostark4@gmail.com','sokostark5@gmail.com','Matjaz','Vodopivec','matjaz@gmail.com','011 3956000','logoSoko Stark.jpg','http://www.stark.rs/'),(8,'NIS petrol','Narodnog fronta 12','Novi Sad',21000,'Srbija','123456789','RSD','123456789','021 4811111','','','','','nis@gmail.com','','','','','Vadim','Yakovlev','vadim@gmail.com','021 4811111','logoNIS petrol.png','https://www.nispetrol.rs/'),(9,'Telekom Srbija','Bulevar Kralja Aleksandra 55','Beograd',11000,'Srbija','123456789','RSD','123456789','011 3956321','011 3953212','','','','telekom@hotmail.com','telekom2@hotmail.com','telekom3@hotmail.com','','','Predrag','Culibrk','predrag@hotmail.com','011 3953212','logoTelekom Srbija.jpg','https://www.mts.rs/'),(10,'Telenor','Snaroveien 30','Fornebu',1360,'Norveska','123456789','NOK','123456789','+47 810 77 000','','','','','telenor@hotmail.com','','','','','Sigve','Brekke','sigve@hotmail.com','+47 810 77 000','logoTelenor.png','https://www.telenor.rs/'),(11,'Microsoft','166th Ave NE','Redmond, WA',7240,'USA','123456789','USD','123456789','+1 425-882-8080','','','','','microsoft@hotmai.com','','','','','John W.','Thompson','johnthompson@hotmail.com','+1 425-882-8080','logoMicrosoft.png','https://www.microsoft.com/'),(12,'Google','1600 Amphitheatre Pkwy','Mountain View, CA',94043,'USA','123456789','USD','123456789','+1 650-253-0000','','','','','google@gmail.com','','','','','Sundar','Pichai','sundar@gmail.com','+1 650-253-0000','logoGoogle.png','https://www.google.com/'),(13,'Youtube','901 Cherry Ave','San Bruno, CA',94066,'USA','123456789','USD','123456789','+1 650-253-0000','','','','','google@gmail.com','','','','','Susan','Wojcicki','susan@gmail.com','+1 650-253-0000','logoYoutube.png','https://www.youtube.com/'),(14,'Oracle','4120 Network Cir','Santa Clara, CA',95054,'USA','123456789','USD','123456789','+1 408-276-0000','','','','','oracle@gmail.com','','','','','Larry','Ellison','larry@gmail.com','+1 408-276-0000','logoOracle.png','https://www.oracle.com/'),(15,'Elektroprivreda Srbije','Makenzijeva 37','Beograd',11000,'Srbija','123456789','RSD','123456789','011 6558400','','','','','eps@gmail.com','','','','','Milos','Milanovic','milos@gmail.com','011 6558400','logoElektroprivreda Srbije.png','http://eps.rs/'),(16,'BMW','Petuelring 124-130','Minhen',80809,'Nemacka','123456789','EUR','123456778','+49 89 125016000','','','','','bmw@gmail.com','','','','','Norbert','Reithofer','norbert@gmail.com','+49 89 125016000','logoBMW.png','https://www.bmw.com/');
/*!40000 ALTER TABLE `kompanija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kontakt`
--

DROP TABLE IF EXISTS `kontakt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kontakt` (
  `idkontakt` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `idkompanija` int(11) NOT NULL,
  PRIMARY KEY (`idkontakt`),
  UNIQUE KEY `idkontakt_UNIQUE` (`idkontakt`),
  KEY `username_idx` (`username`),
  KEY `idkomp_idx` (`idkompanija`),
  CONSTRAINT `idkomp` FOREIGN KEY (`idkompanija`) REFERENCES `kompanija` (`idkompanija`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `korisnikp` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kontakt`
--

LOCK TABLES `kontakt` WRITE;
/*!40000 ALTER TABLE `kontakt` DISABLE KEYS */;
INSERT INTO `kontakt` VALUES (1,'dusan1337',1),(2,'dusan1337',2),(3,'tijana123',3),(4,'tijana123',4),(5,'tijana123',5),(6,'mrlja123',6),(7,'skrobonja123',6),(8,'zoran123',6),(9,'mrlja123',7),(10,'skrobonja123',7),(11,'mrlja123',8),(12,'zoran123',8),(13,'dusan1337',9),(14,'dusan1337',10),(15,'dusan1337',11),(16,'tijana123',11),(17,'zoran123',11),(18,'skrobonja123',12),(19,'tijana123',12),(20,'zoran123',12),(21,'dusan1337',13),(22,'mrlja123',13),(23,'zoran123',13),(24,'dusan1337',14),(25,'tijana123',14),(26,'tijana123',10),(27,'skrobonja123',15),(28,'zoran123',15),(29,'dusan1337',16),(30,'mrlja123',16);
/*!40000 ALTER TABLE `kontakt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnikp`
--

DROP TABLE IF EXISTS `korisnikp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnikp` (
  `ime` varchar(32) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `institucija` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `passwordsha` mediumtext NOT NULL,
  `pol` varchar(1) NOT NULL,
  `datumrodjenja` date DEFAULT NULL,
  `slika` varchar(100) DEFAULT NULL,
  `linkedin` varchar(200) DEFAULT NULL,
  `tip` varchar(32) NOT NULL,
  `odobren` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnikp`
--

LOCK TABLES `korisnikp` WRITE;
/*!40000 ALTER TABLE `korisnikp` DISABLE KEYS */;
INSERT INTO `korisnikp` VALUES ('Admin','Adminovic','admin@etf.rs','ETF','admin','338fc18b0aa196202aa4cb2677cc4e6bbb56d3f7916dccbdc93d012f5f76c26918a395c40027d2df2a35300f7a286063fa0977a02cf6954caa71696ec2ba98d4','m','1996-07-26',NULL,'linkedin.com/admin','admin','odobren'),('Dusan','Prokic','pd150258d@student.etf.rs','ETF','dusan1337','6a9164ebe263f2446923f96e7f6ebdfb08ec6f659a77e7ed06e32ec76940128e326d05103b84af65770d52facff2696e5664f7cf32463b1196f14e5a974af539','m','1996-07-26',NULL,'','clantima','odobren'),('ITmenadzer','ITmenadzic','itmen@etf.rs','ETF','itmen','d25a7d8b88fbf23eaf9119e32062a6770969733c08a3e9341c008448fc4b1ff445db2fd23bcb158efd96ea0e78ceee9ce714933dba5eae5561f9ebb93caff913','z','1997-01-22',NULL,'linkedin.com/itmen','itmenadzer','odobren'),('Marko','Antonijevic','marko@gmail.com','VA','marko123','b9dd36ceba54d06d25f2e9179719ad41a94ae7085eb9d92daf35ff1d6e46a5eec363180a033941c6b051de0ecf5bdd820984c292dbb37e92b31bca6f93028d06','m','1997-06-03','usermarko123.jpg','','clantima','cekanje'),('Marko','Mrljes','marko@gmail.com','VA','mrlja123','22bdf9adb1ae745e1f979a482722466d7f198d05b10f5516213da3475b712c6c966c5d241ca5800e368a587285f6e079eaf69e23b812bbc89a2470e40c9546a2','m','1996-03-14',NULL,'linkedin.com/mrlja','clantima','odobren'),('Jovan','Skrobonja','jovan@gmail.com','TMF','skrobonja123','c8aca84a14ccb4287c7f201c95b02298e5120e113cfe820934e57a7f0638efb19fe5a3ada6304ad98dae63ea5d68e3468e688d981ec49016741830b7119d9929','m','1996-09-20',NULL,'','clantima','odobren'),('Tijana','Kusljevic','kt15599d@student.etf.rs','ETF','tijana123','c08c64f3a4537659a5bc65364f200e992d029013efa3458f7811d6d227ab7b98388a0190c58d7bda97a1b268548c09b183c0bbfe7244e4ebf4aff3c938fb464b','z','1997-01-22','usertijana123.PNG','','clantima','odobren'),('Zoran','Milicevic','zoran@gmail.com','ETF','zoran123','21701ff817e3a4e1882fed34f6bdc73a97fe78fbdf4796215e67142d4cd702a47d6fd6c4444de38fbd427308f609b82962969cf955cc8e403085177fa7cfbe7d','m','1996-01-08',NULL,'','clantima','odobren');
/*!40000 ALTER TABLE `korisnikp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novcaniug`
--

DROP TABLE IF EXISTS `novcaniug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novcaniug` (
  `idnovcaniug` int(11) NOT NULL,
  `idkomp` int(11) NOT NULL,
  `idpaket` int(11) NOT NULL,
  `vrednost` double NOT NULL,
  `datum` date NOT NULL,
  `idstatus` int(11) NOT NULL,
  `faktura` int(11) NOT NULL,
  `uplata` int(11) NOT NULL,
  `datumuplate` date DEFAULT NULL,
  `datumisticanjapaketa` date NOT NULL,
  PRIMARY KEY (`idnovcaniug`),
  UNIQUE KEY `idnovcaniug_UNIQUE` (`idnovcaniug`),
  KEY `idkompnov_idx` (`idkomp`),
  KEY `idpaknov_idx` (`idpaket`),
  KEY `idstatnov_idx` (`idstatus`),
  CONSTRAINT `idkompnov` FOREIGN KEY (`idkomp`) REFERENCES `kompanija` (`idkompanija`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idpaknov` FOREIGN KEY (`idpaket`) REFERENCES `paket` (`idpaket`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idstatnov` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novcaniug`
--

LOCK TABLES `novcaniug` WRITE;
/*!40000 ALTER TABLE `novcaniug` DISABLE KEYS */;
INSERT INTO `novcaniug` VALUES (1,1,1,200000,'2017-10-10',5,1,1,'2018-06-01','2019-10-10'),(2,1,2,100000,'2017-05-05',6,1,1,'2017-06-01','2018-05-05'),(3,2,1,200000,'2017-10-20',5,1,1,'2017-11-03','2019-10-20'),(4,2,3,50000,'2018-05-17',3,1,0,NULL,'2019-05-17'),(5,2,2,100000,'2016-12-06',6,1,1,NULL,'2017-12-06'),(6,3,2,100000,'2017-09-09',5,1,1,NULL,'2018-09-09'),(7,3,1,200000,'2018-05-24',1,0,0,NULL,'2020-05-24'),(8,4,1,200000,'2018-03-07',5,1,1,NULL,'2020-03-07'),(9,5,3,50000,'2016-11-09',6,1,1,'2016-10-06','2017-11-09'),(10,10,1,200000,'2017-03-09',5,1,1,'2017-12-08','2019-03-09'),(11,11,1,200000,'2016-10-12',5,1,1,'2017-02-09','2018-10-12'),(12,14,2,100000,'2016-12-16',6,1,1,'2017-05-12','2017-12-16'),(13,14,3,50000,'2017-10-13',5,1,1,'2017-11-10','2018-10-13'),(14,13,3,50000,'2017-04-03',6,1,1,'2017-05-17','2018-04-03'),(15,16,2,100000,'2017-12-05',3,1,1,NULL,'2018-12-05');
/*!40000 ALTER TABLE `novcaniug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oglas`
--

DROP TABLE IF EXISTS `oglas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oglas` (
  `idoglas` int(11) NOT NULL,
  `naslov` varchar(45) NOT NULL,
  `opis` mediumtext NOT NULL,
  `praksa` int(11) NOT NULL,
  `posao` int(11) NOT NULL,
  `vremeunosenja` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `oglasfajl` varchar(100) DEFAULT NULL,
  `idkompanija` int(11) NOT NULL,
  PRIMARY KEY (`idoglas`),
  UNIQUE KEY `idoglas_UNIQUE` (`idoglas`),
  KEY `idkomp_idx` (`idkompanija`),
  CONSTRAINT `idkom` FOREIGN KEY (`idkompanija`) REFERENCES `kompanija` (`idkompanija`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oglas`
--

LOCK TABLES `oglas` WRITE;
/*!40000 ALTER TABLE `oglas` DISABLE KEYS */;
INSERT INTO `oglas` VALUES (1,'Letnja praksa u Nisu','Pomoc pri odrzavanju servera',1,0,'2018-06-01 14:37:36',NULL,8),(2,'Posao u Nordeusu','Java developer',0,1,'2018-06-01 14:48:19',NULL,5),(3,'Praksa u Americi','Praksa u intelu i mogucnost zaposljenja',1,1,'2018-06-01 14:49:43',NULL,1),(4,'Youtube praksa','Praksa u youtubeu  tokom leta',1,0,'2018-06-01 14:50:33',NULL,13),(5,'Posao Nvidia','Posao u razvoju grafickih cipova',0,1,'2018-06-01 14:51:44',NULL,2),(6,'Kingston praksa','Proucavanje razvoja RAM-a',1,0,'2018-06-01 14:52:35',NULL,3),(7,'Posao u tajvanu','Razvoj maticnih ploca',1,1,'2018-06-01 14:53:30',NULL,4),(8,'Nordeus praksa','Razvoj mobilnih igrica',1,0,'2018-06-01 14:54:18',NULL,5),(9,'Posao u Starku','Odrzavanje i unapredjivanje servera',0,1,'2018-06-01 14:54:46',NULL,7),(10,'Razvoj novog sajta za NIS','Razvoj novog sajta za NIS',0,1,'2018-06-01 14:55:28',NULL,8),(11,'Praksa u Telekomu','Mesto inzinjera telekomunikacija',1,0,'2018-06-01 14:56:12',NULL,9),(12,'Posao Telenor','Razvoj novih telekomunikacionih tehnologija',1,1,'2018-06-01 14:56:41',NULL,10),(13,'Praksa Microsoft','Potreban softverski inzinjer ',0,1,'2018-06-01 14:57:16',NULL,11),(14,'Posao Youtube','Potreban web developer za razvoj platforme',0,1,'2018-06-01 14:57:48',NULL,13),(15,'BMW posao','Projektovanje automobilskih multimedija',0,1,'2018-06-06 13:14:26','oglasBMW posao.pdf',16);
/*!40000 ALTER TABLE `oglas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paket`
--

DROP TABLE IF EXISTS `paket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paket` (
  `idpaket` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `vrednost` double NOT NULL,
  `trajanje` int(11) NOT NULL,
  `maxbrojgodisnje` int(11) NOT NULL,
  PRIMARY KEY (`idpaket`),
  UNIQUE KEY `idpaket_UNIQUE` (`idpaket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paket`
--

LOCK TABLES `paket` WRITE;
/*!40000 ALTER TABLE `paket` DISABLE KEYS */;
INSERT INTO `paket` VALUES (1,'Zlatni',200000,2,10),(2,'Srebrni',100000,1,15),(3,'Bronzani',50000,1,20);
/*!40000 ALTER TABLE `paket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paketstavka`
--

DROP TABLE IF EXISTS `paketstavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paketstavka` (
  `idpaketstavka` int(11) NOT NULL,
  `idpaket` int(11) NOT NULL,
  `idstavka` int(11) NOT NULL,
  PRIMARY KEY (`idpaketstavka`),
  UNIQUE KEY `idpaketstavka_UNIQUE` (`idpaketstavka`),
  KEY `idpak_idx` (`idpaket`),
  KEY `idsta_idx` (`idstavka`),
  CONSTRAINT `idpak` FOREIGN KEY (`idpaket`) REFERENCES `paket` (`idpaket`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idsta` FOREIGN KEY (`idstavka`) REFERENCES `stavka` (`idstavka`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paketstavka`
--

LOCK TABLES `paketstavka` WRITE;
/*!40000 ALTER TABLE `paketstavka` DISABLE KEYS */;
INSERT INTO `paketstavka` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,2,1),(7,2,2),(8,2,3),(9,3,1),(10,3,2);
/*!40000 ALTER TABLE `paketstavka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predavanje`
--

DROP TABLE IF EXISTS `predavanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `predavanje` (
  `idpredavanje` int(11) NOT NULL,
  `naslovsrp` varchar(45) NOT NULL,
  `nasloveng` varchar(45) DEFAULT NULL,
  `opissrp` text NOT NULL,
  `opiseng` text,
  `datum` date NOT NULL,
  `vreme` time NOT NULL,
  `sala` varchar(45) NOT NULL,
  `imepredavaca` varchar(100) NOT NULL,
  `biopredavaca` mediumtext,
  `fajl` varchar(100) DEFAULT NULL,
  `idkompanije` int(11) NOT NULL,
  PRIMARY KEY (`idpredavanje`),
  UNIQUE KEY `idpredavanje_UNIQUE` (`idpredavanje`),
  KEY `idkom_idx` (`idkompanije`),
  CONSTRAINT `idkomm` FOREIGN KEY (`idkompanije`) REFERENCES `kompanija` (`idkompanija`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predavanje`
--

LOCK TABLES `predavanje` WRITE;
/*!40000 ALTER TABLE `predavanje` DISABLE KEYS */;
INSERT INTO `predavanje` VALUES (1,'Nove procesorske tehnologije','New processor technologies','Intel odrzava predavanje o procesorima','Intel holds a lecture about processors','2018-08-23','18:00:00','70','John Dmith','Predstavnik intela u Srbiji',NULL,1),(6,'Tehnologije moderne grafike','Modern graphics technologies','Predavanje o novoj generaciji grafickih kartica','Lecture about new generation of graphic cards','2018-08-03','19:00:00','65','Peter Peterson','Predstavnik Nvidie u Srbiji',NULL,2),(7,'DDR5 Ram, Kingston','DDR5 Ram, Kingston','Predavanje o novoj verziji rama','Lecture about developing new RAM version','2018-09-14','20:45:00','56','Milan Ivanovic',NULL,NULL,3),(8,'Maticne ploce','Motherboards','Predavanje o maticnim plocama','Lecture about motherboards','2018-11-16','19:30:00','59','Milan Mikic','Dipl. inz. elektrotehnike i racunarstva',NULL,4),(9,'Pravljenje igrica','','Predavanje o pravljenju igrica','','2019-02-01','20:30:00','70','Petar Peric','Dipl. inz. elektrotehnike i racunarstva',NULL,5),(10,'Automatizacija u prehrambenoj industriji','','Predavanje o sistemima koriscenim u prehrambenoj industriji','','2019-02-08','14:45:00','70','Nikola Markovic','',NULL,7),(11,'Elektrotehnika i naftna industrija','','Predavanje o vezi elektrotehnike i naftne industrije','','2019-02-16','15:45:00','65','Petar Milutinovic','',NULL,8),(12,'Telekomunikacije u Srbiji','Telecommunications in Serbia','Predavanje o tehnologijama koriscenim u Srbiji','Lecture about technologies used in Serbia','2018-11-07','17:45:00','56','Mitar Markovic','Dipl inz elektrotehnike i racunarstva',NULL,9),(13,'5G u Srbiji','5G in Serbia','Implementacija 5g sistema u Srbiji','Implementation of 5G systems in Serbia','2018-10-24','18:00:00','65','Marko Markovic','Dipl inz elektrotehnike i racunarstva',NULL,10),(14,'Razvoj softvera','','Predavanje o novim projektima Majkrosofta u Srbiji','','2018-10-26','18:00:00','65','Petar Petrovic','Dipl inz elektrotehnike i racunarstva',NULL,11),(15,'Virtuelna realnost','Virtual reality','Dostignuca gugla u polju virtuelne realnosti','Achievements of google in virtual reality field','2018-09-07','15:45:00','56','Mika Mikic','',NULL,12),(16,'Dizajn na internetu','Virtual realit','Dizajn sajtova','','2018-09-22','15:00:00','70','Tamara Matic','',NULL,13),(17,'Uloga Jave danas','','Prednosti i mane jave u savremenom programiranju','','2018-12-07','20:30:00','56','Marija Markovic','Dipl inz elektrotehnike i racunarstva',NULL,14),(18,'Savremeni IDE','Modern IDE','Uloge savremenog IDE-a u programiranju','Role of modern IDE in programming ','2018-03-06','15:45:00','70','Milos Mikic','',NULL,14),(19,'Autorska prava','','Prava postavljanja video sadrzaja na Jutjubu','','2018-01-10','18:45:00','65','Pavle Pavic','',NULL,13),(20,'Sigurnost podataka','Data security','Algoritmi i zastita podataka na internetu','Algorithms and data security online','2018-01-26','17:00:00','70','Misa Pavlovic','',NULL,12),(21,'Buducnost operativnih sistema','','Smer razvoja windowsa u buducnosti','','2017-11-15','19:00:00','65','Ivan Mitic','',NULL,11),(22,'Mobilni internet','','Kako radi pristup internetu preko mreze','','2017-09-06','15:00:00','56','Jovan Jovanovic','',NULL,10),(23,'Istorija telekomunikacija','The history of telecommunications','Istorija telekomunikacija od telegrafa do danas','History of telecommunications from telegraph til today','2017-07-04','19:00:00','56','Marko Markovic','',NULL,9),(24,'Automatizacija naftnih busotina','','Proces uvodjenja automatskih sistema za vadjenje nafte','','2017-07-28','20:00:00','65','Milan Mirkovic','',NULL,8),(25,'Pametni sistemi','','Unapredjenje sistema u fabrikama u stilu pametnih kuca','','2017-10-13','18:00:00','56','Milutin Bojic','',NULL,7),(26,'Android programiranje','','Programiranje i optimizacija na android platformi','','2017-10-06','18:00:00','56','Zoran Nikolic','',NULL,5),(27,'Magistrale kompjuteskih sistema','','Detaljnije o magistalama','','2017-07-04','21:00:00','70','Marko Milutinovic','',NULL,4),(28,'DDR4 ram','','Razvoj DDR4 RAM-a','','2015-10-07','18:00:00','56','Nikola Mitic','',NULL,3),(29,'Rast cena grafickih karti','','NVidia pokusava se opravda za previsoke cene grafickih karti','','2018-03-09','18:00:00','56','Milos Pavlovic','',NULL,2),(30,'Kvantni racunari','Quantum computers','Napredak u razvoju kvantnih kompjutera','Advances in development of quantum computers','2018-03-22','18:00:00','56','Milica Mikic','',NULL,1),(31,'Autonomni automobili','','Programiranje autonomnih automobila i vestacka inteligencija','','2018-07-31','18:00:00','70','Johan Schmidt','','predavanjeAutonomni automobili.pdf',16);
/*!40000 ALTER TABLE `predavanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `idstatus` int(11) NOT NULL,
  `naziv` varchar(100) NOT NULL,
  PRIMARY KEY (`idstatus`),
  UNIQUE KEY `idstatus_UNIQUE` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Pripramljen'),(2,'Poslat kompaniji'),(3,'Potpisan od strane fakulteta'),(4,'Potpisan od strane kompanije'),(5,'Potpisan sa obe strane'),(6,'Predat arhivi');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stavka` (
  `idstavka` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `opis` mediumtext NOT NULL,
  PRIMARY KEY (`idstavka`),
  UNIQUE KEY `idstavka_UNIQUE` (`idstavka`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
INSERT INTO `stavka` VALUES (1,'logopano','Logo kompanije na reklamnom panou partnera fakulteta'),(2,'logobrosura','Logo kompanije unutar brosure fakulteta'),(3,'baner','Baner kompanije na zvanicnom sajtu fakulteta'),(4,'oglasavanjeskupova','Mogucnost oglasavanja dogadjaja, takmicenja i drugih strucnih skupova'),(5,'organizacijapredavanja','Mogucnost organizacije strucnih predavanja 2 puta godisnje(jednom po semestru)');
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-18 10:21:48
