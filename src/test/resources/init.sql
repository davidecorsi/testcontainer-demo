DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `serial_number` varchar(250) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO `item` VALUES (1, '85164118', 'T-SHIRT TECH 2.0');
INSERT INTO `item` VALUES (2, '85465416', 'T-SHIRT HEAVYWEIGHT TERRY PROJECT ROCK');
INSERT INTO `item` VALUES (3, '18515613', 'T-SHIRT SEAMLESS GRID');
INSERT INTO `item` VALUES (4, '94871651', 'T-SHIRT HEATGEAR COMPRESSION');
INSERT INTO `item` VALUES (5, '98732131', 'T-SHIRT TECH 2.0 NOVELTY');
