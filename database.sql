CREATE TABLE `test`.`t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登陆用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  `pwdsalt` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Hash密码用的盐',
  `last_login_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE `test`.`t_pro` (
	`id` int(255) NOT NULL AUTO_INCREMENT,
	`pro_name` varchar(64) NOT NULL,
	`pro_code` varchar(64) NOT NULL,
	`create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品信息表';

CREATE TABLE `test`.`t_user_role` (
  `id` bigint(20) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `roleid` bigint(20)  NOT NULL COMMENT '角色ID',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0为可用，1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户-角色关系表';

CREATE TABLE `test`.`t_role_permission` (
  `id` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL COMMENT '角色ID',
  `permid` bigint(20) NOT NULL COMMENT '权限ID',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0位可用，1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色-权限关系表';

CREATE TABLE `test`.`t_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `descinfo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0为可用，1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

CREATE TABLE `test`.`t_permission` (
  `id` bigint(20) NOT NULL,
  `perm_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `descinfo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0可用, 1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';