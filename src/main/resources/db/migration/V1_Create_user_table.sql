CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `accountId` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `token` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `gmtCreate` bigint(255) DEFAULT NULL,
  `gmtModified` bigint(255) DEFAULT NULL,
  `bio` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;