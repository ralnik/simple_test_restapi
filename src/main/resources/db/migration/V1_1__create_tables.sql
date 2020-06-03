DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(15) NOT NULL,
  `userToken` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ;

INSERT INTO `user` VALUES (1,'testuser','password',-492570309);

DROP TABLE IF EXISTS `typequestion`;
CREATE TABLE `typequestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `typequestion` VALUES (1,'Несколько вариантов ответов'),(2,'Свой вариант ответ');


DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questions` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `test` VALUES (1,'25,26,27,28,29','test 1');

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `typeKey_idx` (`type_id`),
  CONSTRAINT `typeFKey` FOREIGN KEY (`type_id`) REFERENCES `typequestion` (`id`)
) ;

INSERT INTO `question` VALUES (1,'who are you?',1),(2,'what is your name?',2),(3,'Do you like tennis?',1),(4,'How many lines have russian flag?',1),(5,'How old are you?',2);

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) NOT NULL,
  `question_id` int(11) NOT NULL,
  `iscorrect` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `questionFKey_idx` (`question_id`),
  CONSTRAINT `questionFKey` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `answer` VALUES (1,'cat',1,1),(2,'dog',1,0),(3,'Alex',2,0),(4,'yes',3,1),(5,'no',3,0),(6,'1',4,0),(7,'2',4,0),(8,'3',4,1),(9,'4',4,0),(10,'33',5,0);


DROP TABLE IF EXISTS `resultuser`;
CREATE TABLE `resultuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  `self_answer` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `mistake` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

INSERT INTO `resultuser`(`test_id`, `answer_id`, `self_answer`, `user_id`, `mistake`) VALUES (1,1,NULL,1,0),(1,3,'Alex',1,0),(1,4,NULL,1,0),(1,8,NULL,1,0),(1,10,'33',1,0);