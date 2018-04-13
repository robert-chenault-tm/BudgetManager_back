CREATE TABLE `users` (
  `id` varchar(36) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
)

CREATE TABLE `authorities` (
  `userid` varchar(36) NOT NULL,
  `username` varchar(45) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `authority_user_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

CREATE TABLE `monthly_budget` (
  `id` varchar(36) NOT NULL,
  `username` varchar(45) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `monthly_budget_user_username_idx` (`username`),
  CONSTRAINT `monthly_budget_user_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

CREATE TABLE `expense_category` (
  `id` varchar(36) NOT NULL,
  `username` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `expected_amount` int(11) NOT NULL,
  `income` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `expense_category_user_username_idx` (`username`),
  CONSTRAINT `expense_category_user_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

CREATE TABLE `recurring_expense` (
  `id` varchar(36) NOT NULL,
  `category_id` varchar(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `recurring_expense_category_id_idx` (`category_id`),
  CONSTRAINT `recurring_expense_category_id` FOREIGN KEY (`category_id`) REFERENCES `expense_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

CREATE TABLE `created_expense` (
  `id` varchar(36) NOT NULL,
  `category_id` varchar(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `amount` float NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `created_expense_category_id_idx` (`category_id`),
  CONSTRAINT `created_expense_category_id` FOREIGN KEY (`category_id`) REFERENCES `expense_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
