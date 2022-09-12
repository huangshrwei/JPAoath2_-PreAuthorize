SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_profile` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_detail` (
  `user_id` int NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `edu` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `professional` varchar(45) DEFAULT NULL,
  `photo` blob,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int NOT NULL,
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for products
-- ----------------------------
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `category` varchar(100) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `creation_by` int DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

