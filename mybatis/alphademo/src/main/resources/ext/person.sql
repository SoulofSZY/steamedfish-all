CREATE TABLE IF NOT EXISTS `t_person` (
	`id` bigint(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(36)  NOT NULL COMMENT '姓名',
	`age` TINYINT(4) COMMENT '年龄',
	`gender` TINYINT(2) COMMENT '性别 1-男 2-女',
	 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人员信息表' ROW_FORMAT = Compact;