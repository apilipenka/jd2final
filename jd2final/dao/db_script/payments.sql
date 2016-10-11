-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.7.12 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных payments
CREATE DATABASE IF NOT EXISTS `payments` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `payments`;


-- Дамп структуры для таблица payments.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `currency_id` int(11) NOT NULL,
  `agreement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_Accounts_Currencies1_idx` (`currency_id`),
  KEY `fk_Accounts_Agreements1_idx` (`agreement_id`),
  CONSTRAINT `fk_Accounts_Agreements1` FOREIGN KEY (`agreement_id`) REFERENCES `agreements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Accounts_Currencies1` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.accounts: ~1 rows (приблизительно)
DELETE FROM `accounts`;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`id`, `number`, `amount`, `currency_id`, `agreement_id`) VALUES
	(1, '13', 12, 3, 1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;


-- Дамп структуры для таблица payments.agreements
CREATE TABLE IF NOT EXISTS `agreements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `valid_from_date` date DEFAULT NULL,
  `valid_to_date` date DEFAULT NULL,
  `bank_id` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_agreements` (`bank_id`,`number`),
  KEY `fk_Agreements_Banks1_idx` (`bank_id`),
  KEY `fk_Agreements_Users1_idx` (`user_ID`),
  CONSTRAINT `fk_Agreements_Banks1` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Agreements_Users1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.agreements: ~1 rows (приблизительно)
DELETE FROM `agreements`;
/*!40000 ALTER TABLE `agreements` DISABLE KEYS */;
INSERT INTO `agreements` (`id`, `number`, `valid_from_date`, `valid_to_date`, `bank_id`, `user_ID`) VALUES
	(1, '12', '2016-08-15', '2016-12-15', 1, 1);
/*!40000 ALTER TABLE `agreements` ENABLE KEYS */;


-- Дамп структуры для таблица payments.banks
CREATE TABLE IF NOT EXISTS `banks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `unn` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNN_UNIQUE` (`unn`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.banks: ~2 rows (приблизительно)
DELETE FROM `banks`;
/*!40000 ALTER TABLE `banks` DISABLE KEYS */;
INSERT INTO `banks` (`id`, `name`, `unn`) VALUES
	(1, 'Agorprom', '123341'),
	(2, 'belarus', '123456');
/*!40000 ALTER TABLE `banks` ENABLE KEYS */;


-- Дамп структуры для таблица payments.cards
CREATE TABLE IF NOT EXISTS `cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `valid_to_date` date NOT NULL,
  `accounts_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_Cards_Accounts1_idx` (`accounts_id`),
  CONSTRAINT `fk_Cards_Accounts1` FOREIGN KEY (`accounts_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.cards: ~0 rows (приблизительно)
DELETE FROM `cards`;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;


-- Дамп структуры для таблица payments.commands
CREATE TABLE IF NOT EXISTS `commands` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command` varchar(45) NOT NULL,
  `url` varchar(200) NOT NULL,
  `label` varchar(45) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `COMMAND_UNIQUE` (`command`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.commands: ~3 rows (приблизительно)
DELETE FROM `commands`;
/*!40000 ALTER TABLE `commands` DISABLE KEYS */;
INSERT INTO `commands` (`id`, `command`, `url`, `label`, `comment`) VALUES
	(3, 'USER_ROLE', 'USER_ROLE', 'USER_ROLE', 'USER_ROLE'),
	(4, 'ACCOUNT_ROLE', 'ACCOUNT_ROLE', 'ACCOUNT_ROLE', 'ACCOUNT_ROLE'),
	(5, 'CURRENCY_ROLE', 'CURRENCY_ROLE', 'CURRENCY_ROLE', 'CURRENCY_ROLE');
/*!40000 ALTER TABLE `commands` ENABLE KEYS */;


-- Дамп структуры для таблица payments.currencies
CREATE TABLE IF NOT EXISTS `currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `mnemo_code` varchar(45) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `currency_uk` (`mnemo_code`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.currencies: ~1 rows (приблизительно)
DELETE FROM `currencies`;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` (`id`, `code`, `mnemo_code`, `name`) VALUES
	(3, '933', 'BYN', '&#1053;&#1086;&#1074;&#1099;&#1081; &#1073;&#1077;&#1083;&#1086;&#1088;&#1091;&#1089;&#1089;&#1082;&#1080;&#1081; &#1088;&#1091;&#1073;&#1083;&#1100;');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;


-- Дамп структуры для таблица payments.exchange_rates
CREATE TABLE IF NOT EXISTS `exchange_rates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate_date` date NOT NULL,
  `rate` float NOT NULL DEFAULT '0',
  `currency_id` int(11) NOT NULL,
  `target_currency_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `exchange_rate_uk` (`currency_id`,`target_currency_id`,`rate_date`),
  KEY `fk_exchange_rates_Curremcies1_idx` (`currency_id`),
  CONSTRAINT `fk_exchange_rates_Curremcies1` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.exchange_rates: ~1 rows (приблизительно)
DELETE FROM `exchange_rates`;
/*!40000 ALTER TABLE `exchange_rates` DISABLE KEYS */;
INSERT INTO `exchange_rates` (`id`, `rate_date`, `rate`, `currency_id`, `target_currency_id`) VALUES
	(1, '1977-01-10', 1, 3, 3);
/*!40000 ALTER TABLE `exchange_rates` ENABLE KEYS */;


-- Дамп структуры для таблица payments.transfer
CREATE TABLE IF NOT EXISTS `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_account_id` int(11) NOT NULL,
  `debit_account_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `transfer_date` date NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__accounts` (`credit_account_id`),
  KEY `FK__accounts_2` (`debit_account_id`),
  CONSTRAINT `FK__accounts` FOREIGN KEY (`credit_account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__accounts_2` FOREIGN KEY (`debit_account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.transfer: ~0 rows (приблизительно)
DELETE FROM `transfer`;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;


-- Дамп структуры для таблица payments.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personal_number` varchar(45) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personal_number_UNIQUE` (`personal_number`),
  KEY `FK_users_user_role_commands` (`user_role_id`),
  CONSTRAINT `FK_users_user_role_commands` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.users: ~2 rows (приблизительно)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `personal_number`, `first_name`, `last_name`, `birth_date`, `login`, `password`, `user_role_id`) VALUES
	(1, 'admin', 'admin', 'admin', '1977-01-10', 'admin', 'admin', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Дамп структуры для таблица payments.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.user_roles: ~2 rows (приблизительно)
DELETE FROM `user_roles`;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`id`, `name`, `description`) VALUES
	(1, 'ADMIN', 'ADMIN'),
	(2, 'MANAGER', 'Manager');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;


-- Дамп структуры для таблица payments.user_role_commands
CREATE TABLE IF NOT EXISTS `user_role_commands` (
  `user_role_id` int(11) NOT NULL,
  `command_id` int(11) NOT NULL,
  PRIMARY KEY (`user_role_id`,`command_id`),
  UNIQUE KEY `user_role_id` (`user_role_id`,`command_id`),
  KEY `FK_user_role_commands_commands` (`command_id`),
  CONSTRAINT `FK_user_role_commands_commands` FOREIGN KEY (`command_id`) REFERENCES `commands` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_commands_user_roles` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.user_role_commands: ~0 rows (приблизительно)
DELETE FROM `user_role_commands`;
/*!40000 ALTER TABLE `user_role_commands` DISABLE KEYS */;
INSERT INTO `user_role_commands` (`user_role_id`, `command_id`) VALUES
	(1, 3),
	(1, 4),
	(2, 4),
	(1, 5);
/*!40000 ALTER TABLE `user_role_commands` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
