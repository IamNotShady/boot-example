CREATE TABLE `test`.`t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登陆用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  `pwdsalt` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Hash密码用的盐',
  `last_login_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `test`.`t_pro` (
	`id` int(255) NOT NULL AUTO_INCREMENT,
	`pro_name` varchar(64) NOT NULL,
	`pro_code` varchar(64) NOT NULL,
	`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`last_modified_time` timestamp NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` AUTO_INCREMENT=3 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;