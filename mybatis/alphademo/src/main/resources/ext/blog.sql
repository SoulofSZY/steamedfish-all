CREATE TABLE `t_blog`  (
	`id` bigint(11) NOT NULL,
	`title` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
	 PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客信息表' ROW_FORMAT = Compact;

insert into  t_blog values (1, "test01"),(2, "test02")

CREATE TABLE `t_author`  (
	`id` bigint(11) NOT NULL,
	`name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
	`age`
	 PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作者表' ROW_FORMAT = Compact;