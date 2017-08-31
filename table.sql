CREATE TABLE `test`.`t_sb_user` (
	`login_name` varchar(32),
	`real_name` varchar(32),
	`pass_word` varchar(16)
) COMMENT='';

CREATE TABLE `test`.`t_sb_pro` (
	`id` int(255) NOT NULL AUTO_INCREMENT,
	`pro_name` varchar(64) NOT NULL,
	`pro_code` varchar(64) NOT NULL,
	`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`last_modified_time` timestamp NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` AUTO_INCREMENT=3 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;