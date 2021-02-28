-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: englishdb
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'MultipleChoice'),(2,'InComplete'),(3,'Conversation');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choice` (
  `id_choice` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `iscorrect` tinyint NOT NULL,
  `note` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `id_multi` int NOT NULL,
  PRIMARY KEY (`id_choice`),
  KEY `fk_choice_multi_idx` (`id_multi`),
  CONSTRAINT `fk_choice_multi` FOREIGN KEY (`id_multi`) REFERENCES `multiplechoice` (`id_Qmultiple`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES (13,'big',0,NULL,1),(14,'bigger',0,NULL,1),(15,'bigger than',1,NULL,1),(16,'more bigger than',0,NULL,1),(17,'loaded',0,NULL,2),(18,'crowded',0,NULL,2),(19,'carried',0,NULL,2),(20,'packed',1,NULL,2),(21,'simultaneous interpreters ',1,NULL,6),(22,'the booth',0,NULL,6),(23,'consecutive interpreters',0,NULL,6),(24,'the conference',0,NULL,6),(25,'secret ',1,NULL,7),(73,' exact ',0,NULL,7),(74,' believable ',0,NULL,7),(75,' valuable ',0,NULL,7),(76,'purposefully ',0,NULL,8),(77,'exceedingly ',0,NULL,8),(78,' relatively',1,NULL,8),(79,'normally',0,NULL,8),(80,'hardly ',0,NULL,9),(81,'inside',0,NULL,9),(82,'only ',0,NULL,9),(83,'within',1,NULL,9),(84,' As a result of ',1,NULL,10),(85,'In compared with',0,NULL,10),(86,'According to ',0,NULL,10),(87,'In addition to',0,NULL,10),(88,'shame ',0,NULL,11),(89,'shameful ',1,NULL,11),(90,'ashamed ',0,NULL,11),(91,'shameless',0,NULL,11),(92,'mostly',0,NULL,12),(93,'almost ',1,NULL,12),(94,' most ',0,NULL,12),(95,'the most',0,NULL,12),(96,'ability ',0,NULL,13),(97,'inability ',1,NULL,13),(98,'unable',0,NULL,13),(99,'able',0,NULL,13),(100,'complete ',0,NULL,14),(101,'completed',0,NULL,14),(102,'completely',1,NULL,14),(103,'completion',0,NULL,14),(104,'Not any of the ',0,NULL,15),(105,'None of the',1,NULL,15),(106,'Not any of ',0,NULL,15),(107,'None of',0,NULL,15),(108,' had played ',0,NULL,16),(109,'have played',0,NULL,16),(110,' would play ',0,NULL,16),(111,'was playing',1,NULL,16),(112,'should be ',0,NULL,17),(113,'should have been',0,NULL,17),(114,'must be',0,NULL,17),(115,'must have been',1,NULL,17),(116,'due to',0,NULL,18),(117,'despite',1,NULL,18),(118,'so as',0,NULL,18),(119,'only if',0,NULL,18),(124,'Cures from plants. ',0,NULL,19),(125,'The beginning of natural history. ',0,NULL,19),(126,' Prehistoric man. ',0,NULL,19),(127,' Early plants and animals.',1,NULL,19),(128,'To explain the scope of interpreting.',0,NULL,20),(129,'To differentiate between simultaneous and consecutive interpreters.',1,NULL,20),(130,'To state the qualifications of an interpreter.',0,NULL,20),(131,'To state the qualifications of an interpreter.',0,NULL,20),(132,'progressive',0,NULL,21),(133,'advanced ',0,NULL,21),(134,' highly-developed',0,NULL,21),(135,'all are correct',1,NULL,21),(136,'to ',1,NULL,22),(137,'from ',0,NULL,22),(138,'for',0,NULL,22),(139,'with',0,NULL,22),(140,'despite ',0,NULL,25),(141,'because of ',1,NULL,25),(142,'though',0,NULL,25),(143,'because',0,NULL,25),(144,'there are',0,NULL,26),(145,'they are ',0,NULL,26),(146,' it is ',0,NULL,26),(147,'there is',1,NULL,26);
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `id_question` int NOT NULL AUTO_INCREMENT,
  `c_general` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_question`),
  CONSTRAINT `fk_conversation_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (19,'Long ago prehistoric man began to domesticate a number of wild plants and animals for his own use. This not only provided more abundant food but also allowed more people to live on a smaller plot of ground. We tend to forget that all of our present-day pets, livestock, and food plants were taken from the wild and developed into the forms we know today. As centuries passed and human cultures evolved and blossomed, humans began to organise their knowledge of nature into the broad field of natural history. One aspect of early natural history concerned the use of plants for drugs and medicine. The early herbalists sometimes overworked their imaginations in this respect. For example, it was widely believed that a plant or part of a '),(20,'Simply being bilingual doesnt qualify someone to interpret. Interpreting is not only a mechanical process of converting one sentence in language A into the same sentence in language B. Rather, its a complex art in which thoughts and idioms that have no obvious counterparts from tongue to tongue _ or words that have several meanings  must be quickly transformed in such a way that the message is clearly and accurately expressed to the listener.  At one international conference, an American speaker said, “You cant make a silk purse out of a sows ear”, which meant nothing to the Spanish audience. The interpretation was, “A monkey in a silk dress is still a monkey” _ an idiom the Spanish understood and that expressed the same idea. ');
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomplete`
--

DROP TABLE IF EXISTS `incomplete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incomplete` (
  `id_question` int NOT NULL AUTO_INCREMENT,
  `in_general` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_question`),
  CONSTRAINT `fk_imcomplete_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomplete`
--

LOCK TABLES `incomplete` WRITE;
/*!40000 ALTER TABLE `incomplete` DISABLE KEYS */;
INSERT INTO `incomplete` VALUES (21,'In this age of (1)______telephone networks and electronic mail, it seems that fewer and even fewer people are taking time to sit down and write letters (2)_____friends and relatives. For hundreds of years, letters were the only way to keep (3)_____ people who were any distance away and letter-writing was seen as an important skill for all learned people (4)______ . Gradually, (5)_____ , the importance of writing letters is decreasing to a point that majority of us have to (6)_____ a special effort to turn out something worthwhile when we apply for a job or make a complaint. In business circles the tendency is for routine communications to become shorter. (7)_____ clients may appreciate a detailed letter, an employee who sends out long letters is often regarded as (8)_____ . Many people prefer the telephone in all circumstances and its speed is essential in many situations but (9)______ have you put the telephone down, dissatisfied with what you have managed to say? I dont think Ill throw my (10)______  away yet.'),(25,'Some people believe that soon schools will no longer be necessary. They say that (1)____ the Internet and other new technologies, (2)____ no longer any need for school buildings, formal classes, or teachers. Perhaps this will be true one day, but this is hard to (3)_____ a world without schools. In fact, we need to look at how we can use new technology to make schools better, not (4)_______ them. We should invent a new kind of school that is (5)_____ to libraries, museums, science centers, laboratories, and even companies. (6)____ could give talks on video or over the Internet. TV networks and local stations could develop programming about things students are (7)______ studying in school. Already there are several towns (8)______ this is beginning to happen. Blacksburg, Virginia, is one of them. Here the entire city is linked to the Internet, and learning can (9) _______ at home, at school and in the office. Businesses provide programs for the schools and the schools provide computer labs for people without their own (10)____ at home.');
/*!40000 ALTER TABLE `incomplete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id_level` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_level`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'easy'),(2,'medium'),(3,'difficult');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multiplechoice`
--

DROP TABLE IF EXISTS `multiplechoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `multiplechoice` (
  `id_Qmultiple` int NOT NULL AUTO_INCREMENT,
  `id_conversation` int DEFAULT NULL,
  `id_incomplete` int DEFAULT NULL,
  PRIMARY KEY (`id_Qmultiple`),
  KEY `fk_multi_conver_idx` (`id_conversation`),
  KEY `fk_multi_incomplete_idx` (`id_incomplete`),
  CONSTRAINT `fk_multi_conver` FOREIGN KEY (`id_conversation`) REFERENCES `conversation` (`id_question`),
  CONSTRAINT `fk_multi_incomplete` FOREIGN KEY (`id_incomplete`) REFERENCES `incomplete` (`id_question`),
  CONSTRAINT `fk_multi_question` FOREIGN KEY (`id_Qmultiple`) REFERENCES `question` (`id_question`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multiplechoice`
--

LOCK TABLES `multiplechoice` WRITE;
/*!40000 ALTER TABLE `multiplechoice` DISABLE KEYS */;
INSERT INTO `multiplechoice` VALUES (1,NULL,NULL),(2,NULL,NULL),(6,NULL,NULL),(7,NULL,NULL),(8,NULL,NULL),(9,NULL,NULL),(10,NULL,NULL),(11,NULL,NULL),(12,NULL,NULL),(13,NULL,NULL),(14,NULL,NULL),(15,NULL,NULL),(16,NULL,NULL),(17,NULL,NULL),(18,NULL,NULL),(19,19,NULL),(20,20,NULL),(21,NULL,21),(22,NULL,21),(25,NULL,25),(26,NULL,25);
/*!40000 ALTER TABLE `multiplechoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `practice` (
  `practice_username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `count_test` int NOT NULL,
  `score` double NOT NULL,
  `practice_date` datetime NOT NULL,
  `id_question` int NOT NULL,
  PRIMARY KEY (`practice_username`,`count_test`,`id_question`),
  KEY `fk_p_q_idx` (`id_question`),
  CONSTRAINT `fk_p_u` FOREIGN KEY (`practice_username`) REFERENCES `user` (`username`),
  CONSTRAINT `fk_practice_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
INSERT INTO `practice` VALUES ('quynh',1,1,'2020-12-07 00:00:00',19),('quynh',2,0,'2020-12-07 00:00:00',19),('quynh',3,0,'2020-12-07 00:00:00',19),('quynh',4,0,'2020-12-07 00:00:00',19),('quynh',5,0,'2020-12-07 00:00:00',19),('quynh',6,0,'2020-12-07 00:00:00',20),('quynh',7,0,'2020-12-07 00:00:00',9),('quynh',7,0,'2020-12-07 00:00:00',12),('quynh',7,0,'2020-12-07 00:00:00',18),('quynh',8,0,'2020-12-07 00:00:00',19),('quynh',9,1,'2020-12-07 00:00:00',20),('quynh',10,0,'2020-12-07 00:00:00',20),('quynh',11,0,'2020-12-07 00:00:00',19),('quynh',12,0,'2020-12-08 00:00:00',21),('quynh',12,1,'2020-12-08 00:00:00',22),('quynh',13,0,'2020-12-08 00:00:00',21),('quynh',13,1,'2020-12-08 00:00:00',22),('quynh',14,0,'2020-12-08 00:00:00',8),('quynh',14,1,'2020-12-08 00:00:00',10),('quynh',15,0,'2020-12-08 00:00:00',21),('quynh',15,0,'2020-12-08 00:00:00',22),('quynh',16,0,'2020-12-08 00:00:00',20),('quynh',17,0,'2020-12-08 00:00:00',21),('quynh',17,0,'2020-12-08 00:00:00',22),('quynh',18,0,'2020-12-08 00:00:00',25),('quynh',18,0,'2020-12-08 00:00:00',26);
/*!40000 ALTER TABLE `practice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id_question` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id_level` int NOT NULL,
  `id_category` int NOT NULL,
  PRIMARY KEY (`id_question`),
  KEY `fk_question_level_idx` (`id_level`),
  KEY `fk_question_category_idx` (`id_category`),
  CONSTRAINT `fk_question_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `fk_question_level` FOREIGN KEY (`id_level`) REFERENCES `level` (`id_level`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Australia is big, but Canada is... Australia',1,1),(2,'Lightweight luggage enables you to manage easily even when fully …………',1,1),(6,'The phrase “the former“ refers to……',1,1),(7,'The information is strictly …….. and should not be discussed in public.',1,1),(8,'It was ……… easy for him to learn baseball because he had been a cricket player.',1,1),(9,'We were ……….. a mile of our destination when we ran out of petrol.',2,1),(10,' …….all the hard work they put in, the students got good exam results.',2,1),(11,'What a (n)  …………….behaviour! He went straight into the line.',2,1),(12,' If you book in advance you will …… certainly have a better table at our restaurant. ',2,1),(13,' He always complains about my ………………….to cook.',2,1),(14,'Although ……. satisfied with the contract, the officials hesitatingly agreed to sign it.',3,1),(15,' ……… children were injured. They all came back unharmed.',3,1),(16,' During the time I started to get chest pains, I ……… tennis a lot.',3,1),(17,' I didnt know exactly how old he was, but he …..about 30 the first time we met.',3,1),(18,'The building work is still on schedule …………… a problem in digging the foundation.',3,1),(19,'What does this passage mainly discuss?',3,3),(20,'What is the purpose of the passage?',3,3),(21,'',1,2),(22,' ',1,2),(23,' ',1,2),(24,' ',1,2),(25,' ',2,2),(26,' ',2,2);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `dob` datetime NOT NULL,
  `dos` datetime NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('quynh','123','quynh','my','nu','2000-01-29 00:00:00','2020-12-01 00:00:00'),('tu','1234','tu du lep','vietnam','b','2020-12-01 00:00:00','2020-12-01 00:00:00'),('tudulep','123','duong van tu','vn','nam','2000-11-28 00:00:00','2020-12-01 00:00:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-08  0:30:52
