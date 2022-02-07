  
USE `mission-web-app`;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
CREATE TABLE `mission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL,
  `content` varchar(100) NOT NULL,
  `location_city` varchar(50) NOT NULL,
  `location_district` varchar(50) NOT NULL,
  `location_address` varchar(50) NOT NULL,
  `salary` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL,
  `detail` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `user_id` int NOT NULL,
  `worker_id` int,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_USERS_USERNAME` (`user_id`), 
  
  CONSTRAINT `FK_USERS_OWNER`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION,


  CONSTRAINT `FK_USERS_WORKER`
  FOREIGN KEY (`worker_id`)
  REFERENCES `user` (`id`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO `mission` (category,content,location_city,location_district,location_address,salary,time,detail,description,user_id)
VALUES 
('food','buy mcDonalds','Taipei','Da-an','Maple Road','5000','2021-09-11','buy','buy',2),
('food','buy starbucks','Taipei','Da-an','Maple Road','5000','2021-09-11','buy','buy',3),
('food','buy starbucks2','Taipei','Da-an','Maple Road','5000','2021-09-11','buy','buy',3);
  
  
  
  
  
  