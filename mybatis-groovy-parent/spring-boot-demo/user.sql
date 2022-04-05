CREATE DATABASE IF NOT EXISTS `test`;

-- test.`user` definition

CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `age` smallint DEFAULT '0',
  `addr` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (id, name, age, addr)
VALUES
(1, 'a', 0, NULL),
(2, 'b', 0, NULL),
(3, 'c', 0, NULL),
(4, 'd', 0, NULL);
