-- 创建第一个数据库和表
CREATE DATABASE`test`  DEFAULT CHARACTER SET utf8 ;
USE `test`;

CREATE TABLE `demo` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `administrator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `demo2`
(
  `id`            int(11) NOT NULL,
  `username`      varchar(32)  NOT NULL,
  `password`      varchar(128) NOT NULL,
  `administrator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建第二个数据库和表
CREATE DATABASE`test2`  DEFAULT CHARACTER SET utf8 ;
USE `test2`;

CREATE TABLE `demo`
(
  `id`            int(11) NOT NULL,
  `username`      varchar(32)  NOT NULL,
  `password`      varchar(128) NOT NULL,
  `administrator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `demo2` (
  `id` INT(11) NOT NULL,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `administrator` TINYINT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



