CREATE DATABASE `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `admins` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `images` (
  `id_image` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(45) NOT NULL,
  PRIMARY KEY (`id_image`),
  UNIQUE KEY `image_url_UNIQUE` (`image_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `categories` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(25) NOT NULL,
  `image_id` int(11) NOT NULL,
  PRIMARY KEY (`id_category`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(25) NOT NULL,
  `quantity` int(11) DEFAULT '0',
  `category` varchar(25) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `image_id` int(11) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `fk_product_category_idx` (`category`),
  KEY `fk_product_image_idx` (`image_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category`) REFERENCES `categories` (`category_name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_product_image` FOREIGN KEY (`image_id`) REFERENCES `images` (`id_image`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `addresses` (
  `id_address` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `country` varchar(25) NOT NULL,
  `city` varchar(25) NOT NULL,
  `street` varchar(25) NOT NULL,
  `house` int(11) NOT NULL,
  `flat` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id_address`),
  KEY `fk_address_user_idx` (`user_id`),
  CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `basket_details` (
  `id_basket_details` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `quantity_reserved` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id_basket_details`),
  KEY `product_idx` (`product_id`),
  KEY `user_idx` (`user_id`),
  CONSTRAINT `fk_basket_detail_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id_product`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_basket_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` varchar(255) NOT NULL,
  `comment_date` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `prod_com_idx` (`product_id`),
  KEY `user_idx` (`username`),
  CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id_product`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`username`) REFERENCES `users` (`user_name`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` varchar(15) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `fk_order_user_idx` (`user_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `order_details` (
  `id_order_details` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id_order_details`),
  KEY `order_idx` (`order_id`),
  KEY `product_idx` (`product_id`),
  CONSTRAINT `fk_order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id_order`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id_product`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `admins` (`admin_name`, `password`) VALUES ('superadmin', 'superadmin');
INSERT INTO `images` (`image_url`) VALUES ('D:/pictures/no_image.jpg');